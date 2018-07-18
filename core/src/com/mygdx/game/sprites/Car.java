package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Car {
    protected Vector3 position;
    protected Boolean alive;
    protected boolean animateRight;
    protected boolean animateLeft;
    protected Animation carAnimation; //create an animation
    protected int scale = (int)(Gdx.graphics.getWidth() * .20);
    protected Rectangle bounds;
    protected Texture carTexture;
    protected boolean goLeft;

    /**
     * Sets Cons
     * @param x
     * @param y
     * @param goLeft
     * @param texturePath
     */

    public Car(int x, int y, boolean goLeft, String texturePath){
        position = new Vector3(x, y, 0);
        alive = true;
        bounds = new Rectangle(x, y, getWidth(), getHeight());
        animateLeft = false;
        animateRight = true;
        carTexture = new Texture(texturePath); //put animation in texture
        carAnimation = new Animation(new TextureRegion(carTexture), 5, 0.7f);
        this.goLeft = goLeft;
    }

    /**
     * Checks if the Car is still on the screen
     * @return
     */

    public boolean isOnScreen(){
        if(position.x < -400 || position.x > Gdx.graphics.getWidth() + 300){
            return false;
        }

        else
            return true;
    }

    /**
     *
     * @param dt Used to update car's position at a given time
     */
    public void update(float dt){
        isOnScreen();
        carAnimation.update(dt);
        if(!isOnScreen()){
            if(goLeft)
                position.x = Gdx.graphics.getWidth() + 99;

            else
                position.x = -399;
        }
        if(goLeft == false)
            position.x += 10; //moves right

        else
            position.x -= 10; // moves left

        bounds.setPosition(position.x, position.y);
    }

    /**
     * Let us know when the car is dead
     */
    public void kill(){
        alive = false;
    }

    /**
     *
     * @return returns that Car is alive
     */
    public Boolean isAlive(){
        return alive;
    }

    /**
     *
     * @return Gets the bounds of the car which helps
     * with Collision detection
     */
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     *
     * @param object the object used to check collision
     * @return returns if collision happens with the given object
     */
    public boolean collides(Rectangle object) {

        return object.overlaps(bounds);
    }

    /**
     *
     * @return Get's the car's position
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     *
     * @return Getting the car's animation specific frame
     */
    public TextureRegion getCarTexture() { //before returned texture, now returns textureRegion
        return carAnimation.getFrame(); //changed this from car to carAnimation.getFrame() to make it animate
    }

    /**
     *
     * @return Returns the width of the Car which helps with Collision Detection
     */
    public int getWidth() {
        return scale;
    }

    /**
     *
     * @return Returns the height of the Car which helps with Collision Detection
     */
    public int getHeight() {
        return scale;
    }

    /**
     *
     * @param position Sets Car position
     */
    public void setPosition(Vector3 position) {
        this.position = position;
    }

    /**
     *
     * @param car Returns a Car Texture
     */
    public void setCarTexture(Texture car) {
        this.carTexture = car;
    }

    /**
     *
     * @param position Using position to test Car's position
     * with user touch input
     */
    public void teleport(Vector3 position){
        this.position.y = position.y;
        this.position.x = position.x;

    }

    /**
     * Prevents memory leaks by deleting the car when no longer needed
     */
    public void dispose(){ carTexture.dispose();}

}
