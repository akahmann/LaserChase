package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Cat {
    private Vector3 position;
    private Vector3 velocity;
    private Animation catAnimation; //create an animation
    int scale = (int)(Gdx.graphics.getWidth() * .15);

    private Texture cat;

    public Cat(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        // Put Cat picture path here in string
        cat = new Texture("spr_cat.png");
        Texture texture = new Texture("spr_catRight_strip11.png"); //put animation in texture
        catAnimation = new Animation(new TextureRegion(texture), 11, 0.5f); //create new animation 11 frames 0.5 cycle time
        //bounds = new Rectangle(x, y, texture.getWidth() / 11, texture getHeight()); //this is probably for collision detection. Divide by per frame (11)
    }

    public void update(float dt){
        // velocity.add adds to velocity needs three parameters
        catAnimation.update(dt);
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);

        // Reverses what was scaled previously
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getCat() { //before returned texture, now returns textureRegion
        return catAnimation.getFrame(); //changed this from cat to catAnimation.getFrame() to make it animate
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

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public void setCat(Texture cat) {
        this.cat = cat;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void teleport(Vector3 position){
        //this is a change to a cat class x
        this.position.y = position.y;
        // 2710 is y when i click the bottom of the screen and the cat shows up top...
        // so this converts (0,2710) to be (0,0)...it would be clean to set to have screen scale
        this.position.x = position.x;
        //

        System.out.println("++++++++++++++++++++" + this.position);
    }
}
