package com.younes.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by younes on 10/05/2018.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOUVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle boundsBird;
    private Animation birdAnimation;
    private Texture birdStates;
    public Bird(int x,int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        birdStates = new Texture("birdanimation.png");
        birdAnimation = new Animation(birdStates,3,0.5f);
        boundsBird = new Rectangle(x,y,birdStates.getWidth()/3,birdStates.getHeight());
    }
    public void update (float dt){
        birdAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOUVEMENT*dt,velocity.y,0);
        if(position.y < 0)
            position.y = 0;
        velocity.scl(1/dt);
        boundsBird.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }
    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }
    public void jump(){
        velocity.y = 250;
    }
    public Rectangle getBoundsBird(){
        return boundsBird;
    }

    public void dispose() {
        birdStates.dispose();
    }
}
