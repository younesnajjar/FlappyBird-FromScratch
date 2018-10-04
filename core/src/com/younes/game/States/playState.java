package com.younes.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.younes.game.FlappyDemo;
import com.younes.game.sprites.Animation;
import com.younes.game.sprites.Bird;
import com.younes.game.sprites.Tube;

/**
 * Created by younes on 09/05/2018.
 */

public class playState extends State {
    private  static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_OFFSET = -30;

    private Bird bird;
    private Texture ground;
    Vector2 groundPos1 ;
    Vector2 groundPos2 ;
    private Animation birdAnimation;
    private Texture birdStates;
    private Array<Tube> tubes;

    private Texture bg;
    public playState(GameStateManager gsm) {
        super(gsm);

        ground = new Texture("ground.png");
        bird = new Bird(50,300);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_OFFSET);
        groundPos2 = new Vector2(cam.position.x - cam.viewportWidth/2 + ground.getWidth(),GROUND_OFFSET);
        cam.setToOrtho(false, FlappyDemo.WIDTH/2,FlappyDemo.HEIGHT/2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        for (int i = 1;i<=TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;
        for (int i = 0;i < tubes.size;i++){
            Tube tube = tubes.get(i);
            if(cam.position.x - cam.viewportWidth/2 > tube.getPosTopTube().x+tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosBotTube().x+((Tube.TUBE_WIDTH +TUBE_SPACING)*TUBE_COUNT));
            }
            if (tube.collides(bird.getBoundsBird())){
                gsm.set(new playState(gsm));
            }
        }
        cam.update();
        if(bird.getPosition().y < ground.getHeight() + GROUND_OFFSET)
            gsm.set (new playState(gsm));


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x-(cam.viewportWidth)/2,0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        for(Tube tube: tubes){
        sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
        sb.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes){
            tube.dispose();
        }
    }
    public void updateGround(){
        if(cam.position.x-cam.viewportWidth/2>groundPos1.x+ground.getWidth())
            groundPos1.add(ground.getWidth()*2,0);
        if(cam.position.x-cam.viewportWidth/2>groundPos2.x+ground.getWidth())
            groundPos2.add(ground.getWidth()*2,0);
    }
}
