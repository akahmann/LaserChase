package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.Cat;
import com.mygdx.game.sprites.Laser;

import javax.xml.soap.Text;
// Next time start at Video 6 and 4:30 :D
public class PlayState extends State {

    private Cat cat;
    private Laser laser;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        // Put cat picture path here!
        //bg = new Texture("LaserDot2.png");
        cat = new Cat(300 ,1000);
        laser = new Laser(100, 200);
        // Refer to FlappyBird Guide Video 5 @ 6min
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            Vector3 catPosition = new Vector3();
            catPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() -  Gdx.input.getY(), 0);
            cat.teleport(catPosition);
            Vector3 laserPosition = new Vector3();
            laserPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() -  Gdx.input.getY(), 0);
            laser.teleport(laserPosition);

        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        cat.update(dt);
        laser.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix();
        sb.begin();
        //sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(cat.getCat(), cat.getPosition().x, cat.getPosition().y);
        sb.draw(laser.getLaser(), laser.getPosition().x, laser.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
