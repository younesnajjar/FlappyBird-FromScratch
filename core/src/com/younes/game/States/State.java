package com.younes.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by younes on 09/05/2018.
 */

public abstract class State {
    OrthographicCamera cam;
    Vector3 mouse;
    GameStateManager gsm;

    State(GameStateManager gsm) {
        this.gsm = gsm;
        mouse = new Vector3();
        cam = new OrthographicCamera();
    }
    protected  abstract void handleInput();
    public abstract void update(float dt);
    public abstract  void render(SpriteBatch sb);
    public abstract  void dispose();
}
