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


public class Dog extends Animal {

    public Dog(int x, int y) {
        super(x, y);
        // Put Dog picture path here in string

        animalTexture = new Texture("spr_dogLeft_strip11.png"); //put animation in texture
        animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f); //create new animation 11 frames 0.5 cycle time
        bounds = new Rectangle(x, y, getWidth(), getHeight()); //this is probably for collision detection. Divide by per frame (11)
    }

    public void setChaseVelocity(Vector3 chasePoint) {
        Vector3 chaseDirection = new Vector3(0, 0, 0);
        //Final - initial.................
        chaseDirection.x = chasePoint.x - position.x;
        chaseDirection.y = chasePoint.y - position.y;

        //remember direction..............
        Boolean xPositive = true;
        Boolean yPositive = true;
        if (chaseDirection.x < 0) {
            xPositive = false;
            chaseDirection.x = chaseDirection.x * -1;
        }
        if (chaseDirection.y < 0) {
            yPositive = false;
            chaseDirection.y = chaseDirection.y * -1;
        }

        //get chase angle... in radians
        double angle = 0;
        angle = Math.atan(chaseDirection.y / chaseDirection.x);

        //get new chase velocity with the angle and hypotenuse(max speed)
        chaseVelocity.x = (float) (maxSpeed * Math.cos(angle));
        chaseVelocity.y = (float) (maxSpeed * Math.sin(angle));

        //assign direction
        if (!xPositive) {
            chaseVelocity.x = chaseVelocity.x * -1;
        }
        if (!yPositive) {
            chaseVelocity.y = chaseVelocity.y * -1;
        }




        //create new animation 11 frames 0.5 cycle time

        Boolean isLeftOrRight;

        // System.out.println("Cat Direction angle is " + angle);
        if (angle <= (Math.PI / 4)) {
            isLeftOrRight = true;
        } else {
            isLeftOrRight = false;
        }
        if (isLeftOrRight) {
            if (finalVelocity.x > 0) { //right
                if (!animateRight) {
                    animalTexture = new Texture("spr_dogRight_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = true;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = false;
                }
            } else { //left
                if (!animateLeft) {
                    animalTexture = new Texture("spr_dogLeft_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = true;
                    animateUp = false;
                    animateDown = false;
                }
            }
        } else {
            if (finalVelocity.y > 0) {//up

                if(!animateUp) {
                    animalTexture = new Texture("spr_dogUp_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = true;
                    animateDown = false;
                    System.out.println("IsUP");
                }
            } else {//down
                if(!animateDown) {
                    animalTexture = new Texture("spr_dogDown_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = true;
                    System.out.println("IsDown");
                }
            }
        }

    }

    public void accelerate() {
        //how straight           //how much the dog slides
        finalVelocity.x = (float) ((chaseVelocity.x * .3) + (finalVelocity.x * .95));
        finalVelocity.y = (float) ((chaseVelocity.y * .3) + (finalVelocity.y * .95));
    }
}