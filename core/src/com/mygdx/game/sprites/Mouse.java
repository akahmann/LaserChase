package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Mouse extends Animal {

    public Mouse(int x, int y){
        super(x,y);
        animalTexture = new Texture("spr_mouseLeft_strip2.png"); //put animation in texture
        animalAnimation = new Animation(new TextureRegion(animalTexture), 2, 0.5f);
    }

    public void setChaseVelocity(Vector3 chasePoint){

        Vector3 chaseDirection = new Vector3(0,0,0);
        //Final - initial.................
        chaseDirection.x = (chasePoint.x - position.x);
        chaseDirection.y = (chasePoint.y - position.y);

        //find chase distance...
        double distanceOfChaser = Math.sqrt(Math.pow(chaseDirection.x,2) + Math.pow(chaseDirection.y,2));
       // System.out.println("DISTANCE TO CAT" + distanceOfChaser);
        if (distanceOfChaser < 550)
        {
            //-1 in order to run away
            chaseDirection.x *= -1;
            chaseDirection.y *= -1;
        }

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

        if (finalVelocity.x > 0) { //right
            if (!animateRight) {
                animalTexture = new Texture("spr_mouseRight_strip2.png");
                animalAnimation = new Animation(new TextureRegion(animalTexture), 2, 1f);
                animateRight = true;
                animateLeft = false;
                animateUp = false;
                animateDown = false;
            }
        } else { //left
            if (!animateLeft) {
                animalTexture = new Texture("spr_mouseLeft_strip2.png");
                animalAnimation = new Animation(new TextureRegion(animalTexture), 2, 1f);
                animateRight = false;
                animateLeft = true;
                animateUp = false;
                animateDown = false;
            }
        }

    }

    public void accelerate(){
        //how straight           //how much the mouse slides

        finalVelocity.x = (float)((chaseVelocity.x * .22) + (finalVelocity.x * .95));
        finalVelocity.y = (float)((chaseVelocity.y * .22) + (finalVelocity.y * .95));
    }
}
