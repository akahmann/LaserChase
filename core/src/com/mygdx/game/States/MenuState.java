package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

/**
 * creates a menu for the game that contains highscores and
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Rectangle btnBounds;

    BitmapFont currentScoreFont;
    BitmapFont topScore;
    BitmapFont secondScore;
    BitmapFont thirdScore;

    /**
     * creates a menuState
     * @param gsm
     */
    public MenuState(GameStateManager gsm) {
        super(gsm);
        //resetScores();
        background = new Texture("spr_cat.png");
        playBtn = new Texture("PlayBtn.png");
        btnBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - (playBtn.getWidth() / 2), Gdx.graphics.getHeight() / 8, 600, 260);
        currentScoreFont = new BitmapFont();
        topScore = new BitmapFont();;
        secondScore = new BitmapFont();
        thirdScore = new BitmapFont();

        int currentScore = prefs.getInteger("score");
        for(int i = 0; i < 3; i++){
            if(currentScore > prefs.getInteger("score1")){
                prefs.putInteger("score3", prefs.getInteger("score2"));
                prefs.putInteger("score2", prefs.getInteger("score1"));
                prefs.putInteger("score1", currentScore);

            }
            else if(currentScore > prefs.getInteger("score2")){
                prefs.putInteger("score3", prefs.getInteger("score2"));
                prefs.putInteger("score2", currentScore);
            }
            else if(currentScore > prefs.getInteger("score3")){
                prefs.putInteger("score3", currentScore);
            }
        }
        prefs.flush();
    }

    /**
     * resets the scores... used mainly for the programmer
     *  as the function can never be called by the user of the app
     *  at this point in time.
     */
    public void resetScores(){
        prefs.putInteger("score",0);
        prefs.putInteger("score1", 0);
        prefs.putInteger("score2", 0);
        prefs.putInteger("score3", 0);
        prefs.flush();
    }

    /**
     * waits for the user to touch the "play button"
     * then creates a play state
     */
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() -  Gdx.input.getY(), 0);
            Rectangle touchPosRec = new Rectangle(touchPosition.x, touchPosition.y, 1, 1);

            if(touchPosRec.overlaps(btnBounds))
                gsm.set(new PlayState(gsm));
        }

    }

    /**
     * updates the menu state
     * @param dt
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * renders the visuals for the game
     * @param sb - a sprite batch
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playBtn, Gdx.graphics.getWidth() / 2 - (playBtn.getWidth() / 2), Gdx.graphics.getHeight() / 8);

        topScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        topScore.getData().setScale(10, 10);
        topScore.draw(sb,  "First " + prefs.getInteger("score1"), Gdx.graphics.getWidth() /3, Gdx.graphics.getHeight() -300);

        secondScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        secondScore.getData().setScale(9, 9);
        secondScore.draw(sb,  "Second " + prefs.getInteger("score2"), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() -500);

        thirdScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        thirdScore.getData().setScale(8, 8);
        thirdScore.draw(sb,  "Third " + prefs.getInteger("score3"), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() -700);

        currentScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        currentScoreFont.getData().setScale(5, 5);
        currentScoreFont.draw(sb,  "Last Score " + prefs.getInteger("score"), Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 2);
        sb.end();
    }

    /**
     * clears images for memory leaks
     */
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
