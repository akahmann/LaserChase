package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.Animation;

public class Laser {
    private Vector3 position;
    private Vector3 velocity;

    private Texture laser;
    private Rectangle bounds;

    public Laser(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        int scale = (int)(Gdx.graphics.getWidth() * .05);
        // Put laser picture path here in string
        laser = new Texture("spr_laser.png");
        bounds = new Rectangle(x, y, scale, scale);
    }

    public void update(float dt){
        // velocity.add adds to velocity needs three parameters
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);

        // Reverses what was scaled previously
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getLaser() { //before returned texture, now returns textureRegion
        return laser;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public void setLaser(Texture laser) {
        this.laser = laser;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void teleport(Vector3 position){
        //this is a change to a laser class x
        this.position.y = position.y;
        this.position.x = position.x;


    }

    public void dispose(){
        laser.dispose();
    }
}
