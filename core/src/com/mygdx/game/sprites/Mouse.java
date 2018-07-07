package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Mouse extends Animal {

    public Mouse(int x, int y){
        super(x,y);
        animalTexture = new Texture("spr_catLeft_strip11.png"); //put animation in texture
        animalAnimation = new Animation(new TextureRegion(animalTexture), 11, 0.5f); //create new animation 11 frames 0.5 cycle time
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

    }
    public void accelerate(){
        //how straight           //how much the dog slides

        finalVelocity.x = (float)((chaseVelocity.x * .25) + (finalVelocity.x * .95));
        finalVelocity.y = (float)((chaseVelocity.y * .25) + (finalVelocity.y * .95));
    }
}
