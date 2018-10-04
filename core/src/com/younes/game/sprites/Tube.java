package com.younes.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by younes on 10/05/2018.
 */

public class Tube {
    private static final int FLUCTUATION = 130;
    private static  final int TUBE_GAB = 100;
    private static  final int LOWEST_OPENING = 120;
    public static  final int TUBE_WIDTH = 52;
    private Texture topTube,bottomTube;
    private Vector2 posTopTube,posBotTube;
    private Rectangle boundsTop,boundsBot;

    private Random rand;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAB+LOWEST_OPENING);
        posBotTube = new Vector2(x,posTopTube.y - TUBE_GAB - bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x,posBotTube.y,bottomTube.getWidth(),bottomTube.getHeight());

    }

    public static int getFLUCTUATION() {
        return FLUCTUATION;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }
    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION)+TUBE_GAB+LOWEST_OPENING);
        posBotTube.set(x,posTopTube.y - TUBE_GAB - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBot.setPosition(posBotTube.x,posBotTube.y);
    }
    public Boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
