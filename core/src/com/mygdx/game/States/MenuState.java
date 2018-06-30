package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {

    private Texture background;
    //private Texture playBtn;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spr_cat.png");
        //playBtn = new Texture("LaserDot2.jpg");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //sb.draw(playBtn, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        //playBtn.dispose();
    }
}
