package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.swing.text.StyledEditorKit;

/**
 * This is the Cat Class everything that pertains
 * to the cat is here including its movement,
 * animation, drawing, and chase behavior
 */

public class Cat {
    private Vector3 position;
    private Vector3 chaseVelocity;//according to laser pointer
    private Vector3 finalVelocity;//according to acceleration and past velocities
    private Boolean alive;
    private boolean animateRight;
    private boolean animateLeft;
    private boolean animateUp;
    private boolean animateDown;
    private double maxSpeed = 7;
    private Animation catAnimation; //create an animation
    private int scale = (int)(Gdx.graphics.getWidth() * .10);
    private Rectangle bounds;
    private Texture cat;

    /**
     *
     * @param x Used for Cat's position and bounds in the x direction
     * @param y Used for Cat's position and bounds in the y direction
     */

    public Cat(int x, int y){
        position = new Vector3(x, y, 0);
        chaseVelocity = new Vector3(0, 0, 0);
        finalVelocity = new Vector3(0, 0, 0);
        alive = true;
        bounds = new Rectangle(x, y, getWidth(), getHeight());
        //System.out.println("This is the cats width" + getWidth());
        animateDown = false;
        animateLeft = false;
        animateRight = true;
        animateUp = false;
        // Put Cat picture path here in string
        cat = new Texture("spr_cat.png");
        Texture texture = new Texture("spr_catRight_strip11.png"); //put animation in texture
        catAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);

    }

    /**
     *
     * @param dt Used to update Cat's position at a given time
     */

    public void update(float dt){
        catAnimation.update(dt);


        //current position + velocity
        position.x = position.x + finalVelocity.x;
        position.y = position.y + finalVelocity.y;
        bounds.setPosition(position.x, position.y);
    }

    /**
     * Let's us know when the Cat is dead
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
     * @return Gets the bounds of the cat which helps
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
     * @return Get's the Cat's position
     */

    public Vector3 getPosition() {
        return position;
    }

    /**
     *
     * @return Getting the Cat's animation specific frame
     */

    public TextureRegion getCat() { //before returned texture, now returns textureRegion
        return catAnimation.getFrame(); //changed this from cat to catAnimation.getFrame() to make it animate
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
     * @param cat Returns a Cat Texture
     */

    public void setCat(Texture cat) {
        this.cat = cat;
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
     * @param chasePoint Used to chase laser and physics of cat movement
     * Rest of the class helps determine which way cat should be facing
     * at given velocity and direction
     */

    public void setChaseVelocity(Vector3 chasePoint){

        Vector3 chaseDirection = new Vector3(0,0,0);
        //Final - initial.................
        chaseDirection.x = chasePoint.x - position.x;
        chaseDirection.y = chasePoint.y - position.y;

        //remember direction..............
        Boolean xPositive = true;
        Boolean yPositive = true;
        if(chaseDirection.x < 0){
            xPositive = false;
            chaseDirection.x = chaseDirection.x * -1;
        }
        if(chaseDirection.y < 0){
            yPositive = false;
            chaseDirection.y = chaseDirection.y * -1;
        }

        //get chase angle... in radians
        double angle;
        angle = Math.atan(chaseDirection.y / chaseDirection.x);

        //get new chase velocity with the angle and hypotenuse(max speed)
        chaseVelocity.x =  (float)(maxSpeed * Math.cos(angle));
        chaseVelocity.y =  (float)(maxSpeed * Math.sin(angle));

        //assign direction
        if (!xPositive){
            chaseVelocity.x = chaseVelocity.x * -1;
        }
        if (!yPositive){
            chaseVelocity.y = chaseVelocity.y * -1;
        }

        Texture texture;
        //create new animation 11 frames 0.5 cycle time

        Boolean isLeftOrRight;

       // System.out.println("Cat Direction angle is " + angle);
        if(angle <= (Math.PI/4)){
            isLeftOrRight = true;
        }
        else{
            isLeftOrRight = false;
        }
        if(isLeftOrRight){
            if (finalVelocity.x > 0){ //right
                if(!animateRight) {
                    texture = new Texture("spr_catRight_strip11.png");
                    catAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = true;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = false;
                }
            }
            else{ //left
                if(!animateLeft) {
                    texture = new Texture("spr_catLeft_strip11.png");
                    catAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = true;
                    animateUp = false;
                    animateDown = false;
                }
            }
        }
        else{
            if(finalVelocity.y > 0){//up

                if(!animateUp) {
                    texture = new Texture("spr_catUp_strip11.png");
                    catAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = true;
                    animateDown = false;
                    System.out.println("IsUP");
                }
            }
            else{//down
                if(!animateDown) {
                    texture = new Texture("spr_catDown_strip11.png");
                    catAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = true;
                    System.out.println("IsDown");
                }
            }
        }

    }

    /**
     * Determines how fast the cat speeds up
     */

    public void accelerate(){
                                   //how straight           //how much the cat slides
        finalVelocity.x = (float)((chaseVelocity.x * .4) + (finalVelocity.x * .9));
        finalVelocity.y = (float)((chaseVelocity.y * .4) + (finalVelocity.y * .9));
    }

    /**
     * Prevenets memory leaks by deleting cat when no longer needed
     */

    public void dispose(){ cat.dispose();}

}
