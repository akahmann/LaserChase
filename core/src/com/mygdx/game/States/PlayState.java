package com.mygdx.game.States;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.Animal;
import com.mygdx.game.sprites.Cat;
import com.mygdx.game.sprites.Dog;
import com.mygdx.game.sprites.Laser;
import com.mygdx.game.sprites.Mouse;

import javax.xml.soap.Text;
public class PlayState extends State {

    private Animal cat;
    private Mouse mouse;
    private Dog dog;
    private Laser laser;
    private Texture bg;

    public PlayState(GameStateManager gsm) {

        super(gsm);
        cat = new Cat((int)(Gdx.graphics.getWidth() * .5) ,(int)(Gdx.graphics.getHeight() * .5));
        dog = new Dog((int)(Gdx.graphics.getWidth() * .25) ,(int)(Gdx.graphics.getHeight() * .25));
        mouse = new Mouse((int)(Gdx.graphics.getWidth() * .75) ,(int)(Gdx.graphics.getHeight() * .75));
        laser = new Laser(0, 0);
        cam.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        Gdx.app.setLogLevel(Application.LOG_INFO);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            Vector3 laserPosition = new Vector3();
            laserPosition.set(Gdx.input.getX(), Gdx.graphics.getHeight() -  Gdx.input.getY(), 0);
            laser.teleport(laserPosition);

        }

    }

    @Override
    public void update(float dt) {
        //System.out.println("HERE");
        handleInput();
        cat.accelerate();
        cat.setChaseVelocity(laser.getPosition());
        cat.update(dt);

        dog.accelerate();
        dog.setChaseVelocity(cat.getPosition());
        dog.update(dt);

        mouse.accelerate();
        mouse.setChaseVelocity(cat.getPosition());
        mouse.update(dt);
        laser.update(dt);

        if(dog.collides(cat.getBounds())){
            cat.kill();
        }

        if(cat.collides(mouse.getBounds())){
            mouse.kill();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix();
        sb.begin();
        //sb.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(.0f, .206f, 0f, 1);

        if(cat.isAlive()) {
            sb.draw(cat.getAnimal(), cat.getPosition().x, cat.getPosition().y, (float) (Gdx.graphics.getWidth() * .15), (float) (Gdx.graphics.getWidth() * .15));
        }
        else{
            gsm.set(new PlayState(gsm));
        }

        if (mouse.isAlive()){
            sb.draw(mouse.getMouse(), mouse.getPosition().x, mouse.getPosition().y, (float)(Gdx.graphics.getWidth() * .05), (float)(Gdx.graphics.getWidth() * .05));
        }
        sb.draw(dog.getDog(), dog.getPosition().x, dog.getPosition().y, (float)(Gdx.graphics.getWidth() * .22), (float)(Gdx.graphics.getWidth() * .22));

        sb.draw(laser.getLaser(), laser.getPosition().x, laser.getPosition().y, (float)(Gdx.graphics.getWidth() * .05), (float)(Gdx.graphics.getWidth() * .05));
        sb.end();

    }


    @Override
    public void dispose() {
        bg.dispose();
        cat.dispose();
        laser.dispose();
        mouse.dispose();
        dog.dispose();

    }
}
