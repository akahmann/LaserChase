package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * an abstract class that is the parent of both menu and play states
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    Preferences prefs = Gdx.app.getPreferences("My Preferences");

    /**
     * creates a new state
     * @param gsm
     */
    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    /**
     * handles interaction with the user.
     */
    protected abstract void handleInput();

    /**
     * update. will be overwritten by the state that inherits the class.
     * @param dt
     */
    public abstract void update(float dt);

    /**
     * renders to the screen. will be overwritten by the state that inherits the class.
     * @param sb
     */
    public abstract void render(SpriteBatch sb);

    /**
     * clears the sprite batch image
     */
    public abstract void dispose();

}
