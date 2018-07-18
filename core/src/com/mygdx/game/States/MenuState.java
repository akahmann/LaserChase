package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.Animation;

public class MenuState extends State {

    private Texture background;
    private Animation backgroundAnimation;
    private Texture playBtn;
    private Rectangle btnBounds;

    BitmapFont currentScoreFont;
    BitmapFont topScore;
    BitmapFont secondScore;
    BitmapFont thirdScore;


    public MenuState(GameStateManager gsm) {
        super(gsm);
        //resetScores();
        background = new Texture("spr_menuScreen_strip11.png");
        //animalTexture = new Texture("spr_catRight_strip11.png"); //put animation in texture
        backgroundAnimation = new Animation(new TextureRegion(background), 11, 0.5f);
        playBtn = new Texture("PlayBtn.png");
        btnBounds = new Rectangle(Gdx.graphics.getWidth() / 2 - (playBtn.getWidth() / 2), Gdx.graphics.getHeight() / 8, 600, 260);
         currentScoreFont = new BitmapFont();
         topScore = new BitmapFont();;
         secondScore = new BitmapFont();
         thirdScore = new BitmapFont();

        int currentScore = prefs.getInteger("score");
        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^CURRENT" + currentScore / 30);
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

        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^TOP1  " + prefs.getInteger("score1") / 30);
        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^TOP2  " + prefs.getInteger("score2") / 30);
        //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^TOP3  " + prefs.getInteger("score3") / 30);
    }

    public void resetScores(){
        prefs.putInteger("score",0);
        prefs.putInteger("score1", 0);
        prefs.putInteger("score2", 0);
        prefs.putInteger("score3", 0);
        prefs.flush();
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
        backgroundAnimation.update(dt);
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        //sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playBtn, Gdx.graphics.getWidth() / 2 - (playBtn.getWidth() / 2), Gdx.graphics.getHeight() / 8);
        //sb.draw(getBackgroundTexture());

        topScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        topScore.getData().setScale(10, 10);
        topScore.draw(sb,  "First " + prefs.getInteger("score1")/30, Gdx.graphics.getWidth() /3, Gdx.graphics.getHeight() -300);

        secondScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        secondScore.getData().setScale(9, 9);
        secondScore.draw(sb,  "Second " + prefs.getInteger("score2")/30, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() -500);

        thirdScore.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        thirdScore.getData().setScale(8, 8);
        thirdScore.draw(sb,  "Third " + prefs.getInteger("score3")/30, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() -700);

        currentScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        currentScoreFont.getData().setScale(5, 5);
        currentScoreFont.draw(sb,  "Last Score " + prefs.getInteger("score")/30, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 2);
        sb.end();
    }


    /**
     *
     * @return Getting the animal's animation specific frame
     */
    public TextureRegion getBackgroundTexture() { //before returned texture, now returns textureRegion
        return backgroundAnimation.getFrame(); //changed this from cat to catAnimation.getFrame() to make it animate
    }

    public TextureRegion getAnimalTexture() { //before returned texture, now returns textureRegion
        return backgroundAnimation.getFrame(); //changed this from cat to catAnimation.getFrame() to make it animate
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
