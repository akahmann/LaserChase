package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Car {
    protected Vector3 position;
    protected Vector3 finalVelocity;//according to acceleration and past velocities
    protected Boolean alive;
    protected boolean animateRight;
    protected boolean animateLeft;
    protected double maxSpeed = 10;
    protected Animation carAnimation; //create an animation
    protected int scale = (int)(Gdx.graphics.getWidth() * .10);
    protected Rectangle bounds;
    protected Texture carTexture;



    public Car(int x, int y){
        position = new Vector3(x, y, 0);
        finalVelocity = new Vector3(0, 0, 0);
        alive = true;
        bounds = new Rectangle(x, y, getWidth(), getHeight());
        animateLeft = false;
        animateRight = true;
        carTexture = new Texture("spr_catRight_strip11.png"); //put animation in texture
        carAnimation = new Animation(new TextureRegion(carTexture), 11, 0.5f);
    }

    /**
     *
     * @param dt Used to update animal's position at a given time
     */
    public void update(float dt){
        carAnimation.update(dt);

        //current position + velocity
        //position.x = position.x + finalVelocity.x;
        position.x += 10; //moves right
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getFinalVelocity(){
        return finalVelocity;
    }

    public void setFinalVelocity(Vector3 finalVelocity){ this.finalVelocity = finalVelocity; }

    /**
     * Let us know when the animal is dead
     */
    public void kill(){
        alive = false;
    }

    /**
     *
     * @return returns that Cat is alive
     */
    public Boolean isAlive(){
        return alive;
    }

    /**
     *
     * @return Gets the bounds of the animal which helps
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
     * @return Get's the animals's position
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     *
     * @return Getting the animal's animation specific frame
     */
    public TextureRegion getCarTexture() { //before returned texture, now returns textureRegion
        return carAnimation.getFrame(); //changed this from cat to catAnimation.getFrame() to make it animate
    }

    /**
     *
     * @return Returns the width of the Cat which helps with Collision Detection
     */
    public int getWidth() {
        return scale;
    }

    /**
     *
     * @return Returns the height of the Cat which helps with Collision Detection
     */
    public int getHeight() {
        return scale;
    }

    /**
     *
     * @param position Sets Cat's position
     */
    public void setPosition(Vector3 position) {
        this.position = position;
    }

    /**
     *
     * @param car Returns a Cat Texture
     */
    public void setCarTexture(Texture car) {
        this.carTexture = car;
    }

    /**
     *
     * @param position Using position to test Cat's position
     * with user touch input
     */
    public void teleport(Vector3 position){
        this.position.y = position.y;
        this.position.x = position.x;

    }

    /**
     * Determines how fast the car speeds up
     */
    public void accelerate(){

    }

    /**
     * Prevents memory leaks by deleting animal when no longer needed
     */
    public void dispose(){ carTexture.dispose();}

}
