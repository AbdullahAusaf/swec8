package com.github.lhrb.nemo.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;
import com.github.lhrb.nemo.actors.Background;
import com.github.lhrb.nemo.actors.CollisionManager;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.SpawnFactory.EnemyFactory;


public class FirstLevelScreen extends AbstractScreen {
    EnemyFactory factory = new EnemyFactory(1, gameStage);
    float gameTime;
    Player player;
    ActorPrefab explosion;
    private Music soundtrack;
    private Label score, life;

    @Override
    public void init() {
        gameTime = 0F;
        Background bg = new Background(0, 0, gameStage, 1);
        Background bg2 = new Background(0, 1200, gameStage, 1);
        
        score = new Label(GameManager.getInstance().getScoreAsString(),
                                 GuiManager.getInstance().getLabelStyle());
        score.setPosition(740, 550);
        guiStage.addActor(score);

        player = new Player(20, 20, gameStage);

        new ActorPrefab(750, 30, guiStage)
                .setAnimation(AnimationLoader.loadTexture("heart.png"));
        life = new Label(player.getLife(),
                GuiManager.getInstance().getLabelStyle());
        life.setPosition(740, 60);
        guiStage.addActor(life);

        soundtrack = Gdx.audio.newMusic(Gdx.files.internal("sound/soundTrack_1.ogg"));
        soundtrack.setLooping(true);
        soundtrack.setVolume(0.2f);
        soundtrack.play();
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub
        gameTime += delta;

        /* Once we define an abstract class for gameScreens, we can define a variable
           for how long the level shall take and replace the hardcorded 3*6
         */
        CollisionManager.checkCollision(getPhysicalActors());

        if (gameTime < 3 * 60) {
            factory.continueManufacture(delta);
        } else {
            // we have to initialize the bossScreen
            System.out.println("The Boss level should start here");
        }
        
        //needs rework since String is immutable (memory performance) 
        score.setText(GameManager.getInstance().getScoreAsString());
        life.setText(player.getLife());
    }
}
