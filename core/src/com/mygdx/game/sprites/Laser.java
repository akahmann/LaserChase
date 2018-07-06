package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.Animation;

/**
 * Laser class is Used for displaying a laser where the user touches.
 */
public class Laser {
    private Vector3 position;
    private Vector3 velocity;

    private Texture laser;
    private Rectangle bounds;

    /**
     * Constructor
     * @param x used for the x position and the bounds
     * @param y used for the y position and the bounds
     */
    public Laser(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        int scale = (int)(Gdx.graphics.getWidth() * .05);
        // Put laser picture path here in string
        laser = new Texture("spr_laser.png");
        bounds = new Rectangle(x, y, scale, scale);
    }

    /**
     * Everything you want updated about a laser should be in here.
     * @param dt used for delta time. aka time between updates.
     */
    public void update(float dt){
        // velocity.add adds to velocity needs three parameters
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);
        // Reverses what was scaled previously
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    /**
     * getBounds()
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * getPosition()
     * @return Vector3
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * getLaser()
     * @return Texture
     */
    public Texture getLaser() { //before returned texture, now returns textureRegion
        return laser;
    }

    /**
     * set position with a vector3
     * @param position
     */
    public void setPosition(Vector3 position) {
        this.position = position;
    }

    /**
     * set velocity with a vector 3
     * @param velocity
     */
    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    /**
     * set the lasers Texture
     * @param laser
     */
    public void setLaser(Texture laser) {
        this.laser = laser;
    }

    /**
     *
     * @return returns a vector3
     */
    public Vector3 getVelocity() {
        return velocity;
    }

    /**
     * changes the vector3 position
     * @param position
     */
    public void teleport(Vector3 position){
        //this is a change to a laser class x
        this.position.y = position.y;
        this.position.x = position.x;


    }

    /**
     * disposes of the laser texture
     */
    public void dispose(){
        laser.dispose();
    }
}
