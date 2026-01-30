package edu.hitsz.application;


import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.supply.FirePowerSupply;
import edu.hitsz.supply.HealthSupply;
import edu.hitsz.supply.SuperFireSupply;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the loading and access of all game images.
 * Provides static methods to retrieve images by class name or object instance.
 * Ensures images are loaded only once and reused throughout the game.
 */
public class ImageManager {

    /**
     * A map that associates class names with their corresponding images.
     * Can be used as: CLASSNAME_IMAGE_MAP.get(obj.getClass().getName()) to retrieve
     * the image associated with the object's class.
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    // Static references to commonly used images
    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage HEALTH_SUPPLY_IMAGE;
    public static BufferedImage FIRE_POWER_SUPPLY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage SUPER_FIRE_POWER_SUPPLY_IMAGE;

    /**
     * Static initializer block that loads all images from the file system when the class is first used.
     * If any image fails to load, the program terminates with an error.
     */

    static {
        try {
            // TODO: Add images for EliteEnemy, BossEnemy, and power-up supplies
            // Load image files from the specified paths
            BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            HEALTH_SUPPLY_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            FIRE_POWER_SUPPLY_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            SUPER_FIRE_POWER_SUPPLY_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bulletPlus.png"));

            // Map each aircraft/bullet class to its corresponding image
            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(FirePowerSupply.class.getName(), FIRE_POWER_SUPPLY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HealthSupply.class.getName(), HEALTH_SUPPLY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName() , BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(SuperFireSupply.class.getName(), SUPER_FIRE_POWER_SUPPLY_IMAGE);


        } catch (IOException e) {
            // Print error and terminate the game if images cannot be loaded
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Retrieves the image associated with the given class name.
     *
     * @param className the fully qualified name of the class (e.g., "edu.hitsz.aircraft.HeroAircraft")
     * @return the corresponding BufferedImage, or null if not found
     */
    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    /**
     * Retrieves the image associated with the given object's class.
     * A convenient method that avoids manually calling getClass().getName().
     *
     * @param obj the object whose class image is to be retrieved
     * @return the corresponding BufferedImage, or null if the object is null or no image is mapped
     */
    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }

}
