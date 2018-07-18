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
public class Cat extends Animal {

    /**
     * @param x Used for Cat's position and bounds in the x direction
     * @param y Used for Cat's position and bounds in the y direction
     */
    public Cat(int x, int y) {
        super(x, y);

        animalTexture = new Texture("spr_catRight_strip11.png"); //put animation in texture
        animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
    }

    /**
     * @param chasePoint Used to chase laser and physics of cat movement
     *                   Rest of the class helps determine which way cat should be facing
     *                   at given velocity and direction
     */
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
        double angle;
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



        Boolean isLeftOrRight;
        if (angle <= (Math.PI / 4)) {
            isLeftOrRight = true;
        } else {
            isLeftOrRight = false;
        }
        if (isLeftOrRight) {
            if (finalVelocity.x > 0) { //right
                if (!animateRight) {
                    animalTexture = new Texture("spr_catRight_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = true;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = false;
                }
            } else { //left
                if (!animateLeft) {
                    animalTexture = new Texture("spr_catLeft_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = true;
                    animateUp = false;
                    animateDown = false;
                }
            }
        } else {
            if (finalVelocity.y > 0) {//up

                if (!animateUp) {
                    animalTexture = new Texture("spr_catUp_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = true;
                    animateDown = false;
                   // System.out.println("IsUP");
                }
            } else {//down
                if (!animateDown) {
                    animalTexture = new Texture("spr_catDown_strip11.png");
                    animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f);
                    animateRight = false;
                    animateLeft = false;
                    animateUp = false;
                    animateDown = true;
                   // System.out.println("IsDown");
                }
            }
        }

    }

    /**
     * Determines how fast the cat speeds up
     */
    public void accelerate() {
        //how straight           //how much the cat slides
        finalVelocity.x = (float) ((chaseVelocity.x * .4) + (finalVelocity.x * .9));
        finalVelocity.y = (float) ((chaseVelocity.y * .4) + (finalVelocity.y * .9));
    }

}