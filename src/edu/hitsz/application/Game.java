package edu.hitsz.application;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.factory.enemy_factory.BossFactory;
import edu.hitsz.factory.enemy_factory.EliteFactory;
import edu.hitsz.factory.enemy_factory.EnemyFactory;
import edu.hitsz.factory.enemy_factory.MobFactory;
import edu.hitsz.music.MusicController;
import edu.hitsz.supply.AbstractFlyingSupply;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;


/**
 * Main game panel, responsible for the game's initialization and running.
 */
public class Game extends JPanel {

    /**
     * Vertical offset for background image scrolling.
     * Used to create an infinite scrolling effect by drawing the background twice:
     * one at current position and one above it. When the top image reaches the screen top,
     * it resets to the bottom.
     */
    private int backGroundTop = 0;

    private EnemyFactory factory_enemy;

    /**
     * Thread pool for scheduling periodic game tasks (e.g., updates, rendering).
     * Uses a single-threaded scheduled executor to ensure thread safety and avoid race conditions.
     */
    private final ScheduledExecutorService executorService;

    //Time interval in milliseconds between each game update cycle.
    private int timeInterval = 40;

    private boolean boss_time = false;
    private final int MAX_BOSS_THRESHOLD = 200;
    private int boss_threshold = MAX_BOSS_THRESHOLD;

    //The player's hero aircraft. Controlled by mouse input.
    private final HeroAircraft heroAircraft;

    //List of active enemy aircraft
    private final List<AbstractEnemyAircraft> enemyAircrafts;
    //List of bullets fired by the hero aircraft
    private final List<BaseBullet> heroBullets;
    //List of bullets fired by the enemy aircraft
    private final List<BaseBullet> enemyBullets;
    //List of active supply items
    private final List<AbstractFlyingSupply> flyingSupplies;

    // Music controller for managing background music and sound effects
    private MusicController musicController;

    // Maximum number of enemy aircraft allowed on screen at once
    private int enemyMaxNumber = 5;

    //Current player score. Increased when enemies are destroyed.
    private int score = 0;

    //Current game time in milliseconds.
    private int time = 0;

    /**
     * Duration (in milliseconds) of one game cycle.
     * Determines how often enemies spawn and bullets are fired.
     * For example, a value of 600 means every 600ms, a new enemy may spawn or hero fires.
     */
    private int cycleDuration = 600;
    // Elapsed time within the current cycle. Reset when reaching cycleDuration.
    private int cycleTime = 0;

    /**
     * Flag indicating whether the game has ended (e.g., hero destroyed).
     * When true, the game loop stops and no further updates occur.
     */
    private boolean gameOverFlag = false;

    /**
     * Constructs a new game instance, initializing all game entities and starting the input controller.
     */
    public Game() {
        // Initialize hero at bottom center of screen
        heroAircraft = HeroAircraft.getInstance(
                Main.WINDOW_WIDTH / 2,
                Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                0, 0, 200);
        // Initialize collections for game objects
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        flyingSupplies = new LinkedList<>();

        //Create a scheduled thread pool with one dedicated daemon thread.
        this.executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("game-action-%d").daemon(true).build());

        // Initialize music controller and start background music
        musicController = new MusicController();
        musicController.startBgmMusic();

        // Attach mouse listener to control hero aircraft movement
        new HeroController(this, heroAircraft);

    }

    /**
     * Starts the main game loop.
     * This method schedules a periodic task that performs the following actions:
     * 1. Updates game time
     * 2. Spawns enemies and fires bullets (based on cycle timing)
     * 3. Moves all bullets and aircraft
     * 4. Checks for collisions (bullets vs enemies, hero vs enemies, hero vs supplies)
     * 5. Cleans up invalid (destroyed or off-screen) objects
     * 6. Repaints the game panel
     * 7. Checks for game over condition (hero HP <= 0)
     */
    public void action() {
        Runnable task = () -> {
            // Advance game clock
            time += timeInterval;


            // Check if a new cycle should start (used for spawning and shooting)
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // Generate new enemy aircraft if under limit
                enemyAircrafts.addAll(produceEnemy());
                // Trigger shooting action for hero and all enemy aircraft
                shootAction();
            }

            // Move all bullets forward
            bulletsMoveAction();

            // Move all enemy aircraft forward
            aircraftsMoveAction();

            // Move all falling supply items
            suppliesMoveAction();

            // Detect and resolve collisions
            crashCheckAction();

            // Remove any objects marked as invalid (destroyed or out of bounds)
            postProcessAction();

            // Request repaint to reflect updated game state
            repaint();

            // Check for game over condition
            if (heroAircraft.getHp() <= 0) {
                musicController.stopBgmMusic();
                musicController.stopBossMusic();
                musicController.startGameOverMusic();
                executorService.shutdown();  // Stop the game loop
                gameOverFlag = true;
                System.out.println("Game Over!");
            }

        };

        // Schedule the game loop task to run every `timeInterval` ms (with fixed delay)
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    // *****************************************************************
    //                        Action Components
    // *****************************************************************

    /**
     * Increments the cycle timer and checks whether a new action cycle has started.
     * @return true if the current cycle has ended and a new one begins; false otherwise
     */
    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration) {
            cycleTime %= cycleDuration; // Reset within cycle
            return true;
        } else {
            return false;
        }
    }
    /**
     * Generates a list of new enemy aircraft to spawn in the current cycle.
     * @return a list of newly created enemy aircraft (may be empty)
     */
    private List<AbstractEnemyAircraft> produceEnemy() {
        List<AbstractEnemyAircraft> res = new LinkedList<>();

        // Only produce new enemies if current count is below the maximum allowed

        if (enemyAircrafts.size() < enemyMaxNumber) {
            // TODO: Extend to include other enemy types (e.g., EliteEnemy, BossEnemy)
            // Currently spawns a basic mob enemy
            // Spawn a MobEnemy at a random x-position near the top of the screen
            if (!boss_time && boss_threshold <= 0){
                boss_time = true;
                factory_enemy = new BossFactory();
                boss_time = false;
                boss_threshold = MAX_BOSS_THRESHOLD;
            } else {
                Random opponentTypeProbability = new Random();
                double probabilityProduce = opponentTypeProbability.nextDouble();
                double elite_enemy = 0.8;
                if (probabilityProduce < elite_enemy) {
                    factory_enemy = new MobFactory();
                } else {
                    factory_enemy = new EliteFactory();
                }
            }
        }
        AbstractEnemyAircraft newEnemy = factory_enemy.createEnemy();
        res.add(newEnemy);
        if (newEnemy instanceof BossEnemy) {
            musicController.startBossMusic();
        }
        return res;
    }

    /**
     * Triggers shooting action for applicable aircraft.
     * Currently only the hero aircraft shoots.
     */
    private void shootAction() {
        heroBullets.addAll(heroAircraft.shoot());
        // TODO: Implement enemy shooting logic
        for (AbstractEnemyAircraft enemy : enemyAircrafts) {
            enemyBullets.addAll(enemy.shoot());
        }
    }

    // Moves all bullets (hero and enemy) forward according to their speed and direction.
    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }

        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    //Moves all enemy aircraft forward according to their velocity.
    private void aircraftsMoveAction() {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    //Moves all falling supply items downward.
    private void suppliesMoveAction() {
        for (AbstractFlyingSupply flyingSupplie : flyingSupplies){
            flyingSupplie.forward();
        }
    }

    /**
     * Performs collision detection and response between all relevant game objects.
     * This method is a core gameplay logic component that handles:
     * 1. Enemy bullets hitting the hero aircraft
     * 2. Hero bullets hitting enemy aircraft
     * 3. Direct collision between hero and enemy aircraft
     * 4. Hero collecting power-up supplies
     */
    private void crashCheckAction() {
        // 1. Check for enemy bullets hitting the hero aircraft
        for (BaseBullet bullet : enemyBullets) {
            // TODO: Handle enemy bullets hitting hero
            if (heroAircraft.crash(bullet)){
                heroAircraft.decreaseHp(bullet.getPower());
                bullet.vanish();
            }
        }
        // 2. Check for hero bullets hitting enemy aircraft
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;    // Skip bullets that are no longer active
            }
            for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // Skip enemies that are already destroyed or invalid
                    continue;
                }
                // Check if hero bullet hits enemy aircraft
                if (enemyAircraft.crash(bullet)) {
                    // Apply damage to the enemy equal to the bullet's power
                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    musicController.startBulletHitMusic();
                    //Check if the enemy is destroyed after taking damage
                    if (enemyAircraft.notValid()) {
                        // Increase player score based on enemy type
                        score += enemyAircraft.score();

                        // TODO: Implement random drop mechanism
                        flyingSupplies.addAll(enemyAircraft.generateSupplies());
                        boss_threshold-=enemyAircraft.score();
                        if (enemyAircraft instanceof BossEnemy) {
                            musicController.stopBossMusic();
                        }
                    }
                }
                // 3. Check for direct collision between hero and enemy aircraft
                if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                    // If hero collides with any enemy aircraft, both are destroyed.
                    enemyAircraft.vanish();
                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
                }
            }
        }

        //4. Check for hero collecting supply items
        for (AbstractFlyingSupply flyingSupply : flyingSupplies) {
            // Todo: Implement logic for when the hero aircraft collides with a supply item — apply the item's effect
            if (heroAircraft.crash(flyingSupply)) {
                flyingSupply.activate(heroAircraft);
                flyingSupply.vanish();
                musicController.startSupplyMusic();
            }
        }

    }

    /**
     * Cleans up invalid (destroyed or off-screen) game objects to prevent memory leaks and unnecessary processing.
     */
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        flyingSupplies.removeIf(AbstractFlyingObject::notValid);
    }

    // *****************************************************************
    //                          Paint Components
    // *****************************************************************

    /**
     * Overrides the default paint method to render all game elements.
     * Called repeatedly by the AWT painting system to create animation.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw scrolling background
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
        this.backGroundTop += 1;
        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
            this.backGroundTop = 0;  // Reset to create loop effect
        }

        // Draw bullets first , then draw aircraft and supplies
        paintImageWithPositionRevised(g, enemyBullets);
        paintImageWithPositionRevised(g, heroBullets);
        paintImageWithPositionRevised(g, enemyAircrafts);
        paintImageWithPositionRevised(g, flyingSupplies);

        // Draw hero aircraft (drawn separately to ensure correct layering)
        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);

        // Draw UI: score and remaining life (HP)
        paintScoreAndLife(g);

    }

    /**
     * Draws a list of flying objects at their current positions.
     * The image is centered on the object's (x, y) location.
     *
     * @param g       the graphics context
     * @param objects the list of objects to draw
     */
    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (AbstractFlyingObject object : objects) {
            BufferedImage image = object.getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
                    object.getLocationY() - image.getHeight() / 2, null);
        }
    }

    //Draws the current score and hero's remaining HP on the top-left corner of the screen.
    private void paintScoreAndLife(Graphics g) {
        int x = 10;
        int y = 25;
        g.setColor(new Color(16711680));
        g.setFont(new Font("SansSerif", Font.BOLD, 22));
        g.drawString("SCORE:" + this.score, x, y);
        y = y + 20;
        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
    }
}
