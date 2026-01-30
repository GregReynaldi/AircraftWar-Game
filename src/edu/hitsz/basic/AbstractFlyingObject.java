package edu.hitsz.basic;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import java.awt.image.BufferedImage;

/**
 * Abstract parent class for all flying objects in the game.
 * Defines common properties and behaviors such as position, speed, image, and collision detection.
 */
public abstract class AbstractFlyingObject {

    // locationX and locationY represent the center coordinates of the object's imag
    //X-axis coordinate
    protected int locationX;

    //Y-axis coordinate
    protected int locationY;

     //Speed along the X-axis
    protected int speedX;

    //Speed along the Y-axis
    protected int speedY;

    /**
     * Image of the flying object.
     * null indicates that the image has not been set yet.
     */
    protected BufferedImage image = null;

    /**
     * Width of the object (along X-axis), determined from the image size.
     * -1 indicates that the width has not been initialized.
     */
    protected int width = -1;

    /**
     * Height of the object (along Y-axis), determined from the image size.
     * -1 indicates that the height has not been initialized.
     */
    protected int height = -1;

    /**
     * Validity flag.
     * Objects marked as invalid (isValid = false) will be removed in the next game update cycle.
     */
    protected boolean isValid = true;

    public AbstractFlyingObject() {
    }

    /**
     * Constructor with position and speed parameters.
     *
     * @param locationX Initial X-axis coordinate
     * @param locationY Initial Y-axis coordinate
     * @param speedX    Speed along the X-axis
     * @param speedY    Speed along the Y-axis
     */
    public AbstractFlyingObject(int locationX, int locationY, int speedX, int speedY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * Moves the flying object according to its speed.
     * If the object hits the horizontal window boundary, its X-axis speed reverses direction.
     */
    public void forward() {
        locationX += speedX;
        locationY += speedY;
        if (locationX <= 0 || locationX >= Main.WINDOW_WIDTH) {
            // Reverse X-speed if hitting left or right boundary
            speedX = -speedX;
        }
        // Check if the enemy has moved past the bottom edge of the window
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    /**
     * Collision detection: checks if this object collides with another flying object.
     * A collision is detected if the bounding boxes of the two objects overlap.
     *
     * @param flyingObject The other flying object to check collision against
     * @return true if collision occurs, false otherwise
     */
    public boolean crash(AbstractFlyingObject flyingObject) {
        // Scale factor to adjust collision sensitivity on Y-axis
        // Aircraft have larger hitbox (factor = 2), others use normal (factor = 1)
        int factor = this instanceof AbstractAircraft ? 2 : 1; //我方
        int fFactor = flyingObject instanceof AbstractAircraft ? 2 : 1;//对方

        // Get position and dimensions of the other object
        int x = flyingObject.getLocationX();
        int y = flyingObject.getLocationY();
        int fWidth = flyingObject.getWidth();
        int fHeight = flyingObject.getHeight();

        // Check for overlapping in both X and Y axes
        return x + (fWidth+this.getWidth())/2 > locationX
                && x - (fWidth+this.getWidth())/2 < locationX
                && y + ( fHeight/fFactor+this.getHeight()/factor )/2 > locationY
                && y - ( fHeight/fFactor+this.getHeight()/factor )/2 < locationY;
    }

    // Gets the X-axis coordinate
    public int getLocationX() {
        return locationX;
    }

    //Gets the Y-axis coordinate.
    public int getLocationY() {
        return locationY;
    }

    /**
     * Sets the position of the object.
     *
     * @param locationX New X coordinate
     * @param locationY New Y coordinate
     */
    public void setLocation(double locationX, double locationY){
        this.locationX = (int) locationX;
        this.locationY = (int) locationY;
    }

    //Gets the speed along the Y-axis.
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Gets the image of the object.
     * @return BufferedImage representing the object
     */
    public BufferedImage getImage() {
        if (image == null){
            image = ImageManager.get(this);
        }
        return image;
    }

    //Gets the width of the object.
    public int getWidth() {
        //Initializes width from image if not already set.
        if (width == -1){
            width = ImageManager.get(this).getWidth();
        }
        return width;
    }

    //Gets the height of the object.
    public int getHeight() {
        //Initializes height from image if not already set.
        if (height == -1){
            height = ImageManager.get(this).getHeight();
        }
        return height;
    }

    /**
     * Checks if the object is invalid (marked for removal).
     *
     * @return true if the object is not valid, false otherwise
     */
    public boolean notValid() {
        return !this.isValid;
    }

    /**
     * Marks the object as invalid (vanished).
     * This object will be removed in the next update cycle.
     */
    public void vanish() {
        isValid = false;
    }

}

