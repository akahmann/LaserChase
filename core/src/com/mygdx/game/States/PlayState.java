package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.sprites.Cat;

import javax.xml.soap.Text;
// Next time start at Video 6 and 4:30 :D
public class PlayState extends State {

    private Cat cat;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        // Put cat picture path here!
        //cat = new Texture("cat picture");
        // Refer to FlappyBird Guide Video 5 @ 6min
        //cam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);


    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {


    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix();
        sb.begin();
        //sb.draw(cat);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
