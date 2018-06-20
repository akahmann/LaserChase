package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Cat {
    private Vector3 position;
    private Vector3 velocity;

    private Texture cat;

    public Cat(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        // Put Cat picture path here in string
        cat = new Texture("spr_cat.png");

    }

    public void update(float dt){
        // velocity.add adds to velocity needs three parameters
        velocity.add(0, 0, 0);
        velocity.scl(dt);
        position.add(0, velocity.y, 0);

        // Reverses what was scaled previously
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getCat() {
        return cat;
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
        this.position.y = position.y;
        // 2710 is y when i click the bottom of the screen and the cat shows up top...
        // so this converts (0,2710) to be (0,0)...it would be clean to set to have screen scale
        this.position.x = position.x;
        //

        System.out.println("++++++++++++++++++++" + this.position);
    }
}
