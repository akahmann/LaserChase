package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Mouse {
    private Vector3 position;
    private Vector3 chaseVelocity;//according to laser pointer
    private Vector3 finalVelocity;//according to acceleration and past velocities
    private Boolean alive;
    private double maxSpeed = 7;
    private Animation mouseAnimation; //create an animation
    int scale = (int)(Gdx.graphics.getWidth() * .10);
    private Rectangle bounds;

    private Texture mouse;

    public Mouse(int x, int y){
        position = new Vector3(x, y, 0);
        chaseVelocity = new Vector3(0, 0, 0);
        finalVelocity = new Vector3(0, 0, 0);
        alive = true;
        bounds = new Rectangle(x, y, getWidth(), getHeight());


        // Put Dog picture path here in string
        mouse = new Texture("spr_cat.png");
        Texture texture = new Texture("spr_catLeft_strip11.png"); //put animation in texture
        mouseAnimation = new Animation(new TextureRegion(texture), 11, 0.5f); //create new animation 11 frames 0.5 cycle time
    }

    public void update(float dt){
        // velocity.add adds to velocity needs three parameters
        mouseAnimation.update(dt);


        //current position + velocity
        position.x = position.x + finalVelocity.x;
        position.y = position.y + finalVelocity.y;
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getMouse() { //before returned texture, now returns textureRegion
        return mouseAnimation.getFrame(); //changed this from dog to dogAnimation.getFrame() to make it animate
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

    public void setMouse(Texture mouse) {
        this.mouse = mouse;
    }


    public void teleport(Vector3 position){
        this.position.y = position.y;
        this.position.x = position.x;

    }

    public void setChaseVelocity(Vector3 chasePoint){

        Vector3 chaseDirection = new Vector3(0,0,0);
        //Final - initial.................-1 in order to run away
        chaseDirection.x = (chasePoint.x - position.x);
        chaseDirection.y = (chasePoint.y - position.y);

        //find chase distance...
        double distanceOfChaser = Math.sqrt(Math.pow(chaseDirection.x,2) + Math.pow(chaseDirection.y,2));
       // System.out.println("DISTANCE TO CAT" + distanceOfChaser);
        if (distanceOfChaser < 550)
        {
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
    public void kill(){
        alive = false;
    }

    public Boolean isAlive(){
        return alive;
    }
    public Rectangle getBounds(){
        return bounds;
    }

    public void accelerate(){
        //how straight           //how much the dog slides

        finalVelocity.x = (float)((chaseVelocity.x * .25) + (finalVelocity.x * .95));
        finalVelocity.y = (float)((chaseVelocity.y * .25) + (finalVelocity.y * .95));
    }

    public void dispose(){ mouse.dispose();}

}
