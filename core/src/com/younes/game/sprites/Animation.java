package com.younes.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by younes on 12/05/2018.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private TextureRegion region;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation (Texture texture,int frameCount ,float cycleTime){
        frames = new Array<TextureRegion>();
        region = new TextureRegion(texture);
        this.frameCount = frameCount;
        int frameWidth = region.getRegionWidth()/frameCount;
        maxFrameTime = cycleTime/frameCount;
        for(int i=0;i<frameCount;i++){
            frames.add(new TextureRegion(region,i*frameWidth,0,frameWidth,region.getRegionHeight()));
        }
        frame = 0;
    }
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime>maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame>=frameCount){
            frame = 0;
        }
    }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
