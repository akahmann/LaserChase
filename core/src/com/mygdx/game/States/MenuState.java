package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Rectangle btnBounds;



    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("spr_cat.png");
        playBtn = new Texture("PlayBtn.png");
        btnBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - (playBtn.getWidth() / 2), Gdx.graphics.getHeight() / 2, 600, 260);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() -  Gdx.input.getY(), 0);
            Rectangle touchPosRec = new Rectangle(touchPosition.x, touchPosition.y, 1, 1);

           // laser.teleport(laserPosition);
            if(touchPosRec.overlaps(btnBounds))
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
        sb.draw(playBtn, Gdx.graphics.getWidth() / 2 - (playBtn.getWidth() / 2), Gdx.graphics.getHeight() / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
