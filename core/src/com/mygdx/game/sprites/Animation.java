package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames; //store all frames
    private float maxFrameTime; //how long a frame will stay in view
    private float currentFrameTime; //time the animation has been in the current frame
    private int frameCount; //number of frames in animation
    private int frame; //current frame we are currently on

    /**
     * Constructs an animation class. Each animation (located in the assets folder) contains multiple images in
     * one png file. Each image has the same width and represents a frame in the animation. This class enables one
     * image in the png file to appear at a time looping through each frame.
     *
     * @author Adam Kahmann
     * @param region The actual image itself containing all the frames in the animation (located in assets folder)
     * @param frameCount The number of frames in the animation (strip## indicates number of frames)
     * @param cycleTime How fast the animation loops through the frames
     * @see assets
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; //divide by frameCount to get the width of each frame
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * Updates the frame to frame in order to make the image animate.
     *
     * @author Adam Kahmann
     * @param dt Delta Time, the change in time
     */
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

    /**
     * Returns the current frame in the animation so that one frame appears once at a time
     *
     * @author Adam Kahmann
     * @return The current frame in the animation
     */
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
