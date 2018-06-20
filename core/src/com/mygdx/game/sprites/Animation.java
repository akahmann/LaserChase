package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames; //store all frames
    private float maxFrameTime; //how long a frame will stay in view
    private float currentFrameTime; //time the animation has been in the current frame
    private int frameCount; //number of frames in animation
    private int frame; //current frame we are currently on

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; //to get the width of each frame
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++; //move to next image in animation
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0; //restart the animation
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame); //return current animation
    }
}
