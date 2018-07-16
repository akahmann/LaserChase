package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.swing.text.StyledEditorKit;

/**
 * This is the animal Class everything that pertains
 * to the animal is here including its movement,
 * animation, drawing, and chase behavior
 */
public class Animal {
    protected Vector3 position;
    protected Vector3 chaseVelocity;//according to laser pointer
    protected Vector3 finalVelocity;//according to acceleration and past velocities
    protected Boolean alive;
    protected boolean animateRight;
    protected boolean animateLeft;
    protected boolean animateUp;
    protected boolean animateDown;
    protected double maxSpeed = 7;
    protected Animation animalAnimation; //create an animation
    protected int scale = (int)(Gdx.graphics.getWidth() * .10);
    protected Rectangle bounds;
    protected Texture animalTexture;

    /**
     *
     * @param x Used for animal's position and bounds in the x direction
     * @param y Used for animal's position and bounds in the y direction
     */
    public Animal(int x, int y){
        position = new Vector3(x, y, 0);
        chaseVelocity = new Vector3(0, 0, 0);
        finalVelocity = new Vector3(0, 0, 0);
        alive = true;
        bounds = new Rectangle(x, y, getWidth(), getHeight());
        animateDown = false;
        animateLeft = false;
        animateRight = true;
        animateUp = false;


    }

    /**
     *
     * @param dt Used to update animal's position at a given time
     */
    public void update(float dt){
        animalAnimation.update(dt);


        //current position + velocity
        position.x = position.x + finalVelocity.x;
        position.y = position.y + finalVelocity.y;
        bounds.setPosition(position.x, position.y);
    }

    /**
     * kill the animal
     */
    public void kill(){
        alive = false;
    }

    /**
     * Let us know when the animal is dead
     */
    public void revive(){
        alive = true;
    }

    /**
     *
     * @return returns that animal is alive
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
    public TextureRegion getAnimalTexture() { //before returned texture, now returns textureRegion
        return animalAnimation.getFrame(); //changed this from cat to catAnimation.getFrame() to make it animate
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
     * @param animal Returns a Cat Texture
     */
    public void setAnimalTexture(Texture animal) {
        this.animalTexture = animal;
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
     *
     * @param chasePoint Used to have the animal chase the given Point
     *
     */
    public void setChaseVelocity(Vector3 chasePoint){
        //animals behave differently... so this will be taken care of in their
        //classes
    }

    /**
     * Determines how fast the animal speeds up
     */
    public void accelerate(){

    }

    /**
     * Prevents memory leaks by deleting animal when no longer needed
     */
    public void dispose(){ animalTexture.dispose();}

}