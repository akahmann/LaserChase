package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * This is the Dog Class everything that pertains
 * to the dog is here including its movement,
 * animation, drawing, and chase behavior
 */


public class Dog {
    private static final String TAG = "Dog.java";
    private Vector3 position;
    private Vector3 chaseVelocity;//according to laser pointer
    private Vector3 finalVelocity;//according to acceleration and past velocities
    private Boolean alive;
    boolean animateRight;
    boolean animateLeft;
    boolean animateUp;
    boolean animateDown;
    private double maxSpeed = 7;
    private Animation dogAnimation; //create an animation
    int scale = (int)(Gdx.graphics.getWidth() * .15);
    private Rectangle bounds;

    private Texture dog;

    public Dog(int x, int y){
        position = new Vector3(x, y, 0);
        chaseVelocity = new Vector3(0, 0, 0);
        finalVelocity = new Vector3(0, 0, 0);
        alive = true;
        Gdx.app.log(TAG, "dog is created");

        // Setting Dogs default facing direction
        animateDown = false;
        animateLeft = false;
        animateRight = true;
        animateUp = false;

        // Put Dog picture path here in string
        dog = new Texture("spr_cat.png");
        Texture texture = new Texture("spr_dogLeft_strip11.png"); //put animation in texture
        dogAnimation = new Animation(new TextureRegion(texture), 11, 0.5f); //create new animation 11 frames 0.5 cycle time
        bounds = new Rectangle(x, y, getWidth(), getHeight()); //this is probably for collision detection. Divide by per frame (11)
    }


    public void update(float dt){
        // velocity.add adds to velocity needs three parameters
        dogAnimation.update(dt);


        //current position + velocity
        position.x = position.x + finalVelocity.x;
        position.y = position.y + finalVelocity.y;
        bounds.setPosition(position.x, position.y);
    }



    public Rectangle getBounds(){
        return bounds;
    }

    public boolean collides(Rectangle cat){

        if(cat.overlaps(bounds)) {
            //System.out.println("Cat has been eaten!");
        }
        return cat.overlaps(bounds);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getDog() { //before returned texture, now returns textureRegion
        return dogAnimation.getFrame(); //changed this from dog to dogAnimation.getFrame() to make it animate
    }

    public int getWidth() {
        return scale;
    }

    public int getHeight() {
        return scale;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }



    public void setDog(Texture dog) {
        this.dog = dog;
    }


    public void teleport(Vector3 position){
        this.position.y = position.y;
        this.position.x = position.x;

    }

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
        double angle = 0;
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
                    texture = new Texture("spr_dogRight_strip11.png");
                    dogAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = true;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = false;
                }
            }
            else{ //left
                if(!animateLeft) {
                    texture = new Texture("spr_dogLeft_strip11.png");
                    dogAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = true;
                    animateUp = false;
                    animateDown = false;
                }
            }
        }
        else{
            if(finalVelocity.y > 0){//up

                /*if(!animateUp) {
                    texture = new Texture("spr_catUp_strip11.png");
                    dogAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = true;
                    animateDown = false;
                    System.out.println("IsUP");
                }*/
            }
            else{//down
                /*if(!animateDown) {
                    texture = new Texture("spr_catDown_strip11.png");
                    dogAnimation = new Animation(new TextureRegion(texture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = true;
                    System.out.println("IsDown");
                }*/
            }
        }

    }

    public void accelerate(){
        //how straight           //how much the dog slides
        finalVelocity.x = (float)((chaseVelocity.x * .3) + (finalVelocity.x * .95));
        finalVelocity.y = (float)((chaseVelocity.y * .3) + (finalVelocity.y * .95));
    }

    public void kill(){
        alive = false;
        //Gdx.app.log(TAG, "dog is dead");
    }

    public void dispose(){ dog.dispose(); }
}
