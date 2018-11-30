package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.EnemyOne;
import java.util.Random;

public class EnemyFactory {

    private int level;
    private boolean enemyOne, enemyTwo, enemyThree;
    private float gameTime, timeLastSpawn;
    private Stage gameStage;

    private float spawnRate;

    public EnemyFactory(int level, Stage gameStage) {
        this.level = level;
        gameTime = 0;
        enemyOne = true;
        enemyTwo = false;
        enemyThree = false;
        this.gameStage = gameStage;

        // first spawn is delayed + 1 second
        timeLastSpawn = 1;
    }

    public void spawnEnemy(ActorPrefab actor, float x, float y, Stage gameStage) {
        if (spawnRate - (gameTime - timeLastSpawn) < 0) {
            actor = new ActorPrefab(x, y, gameStage);
            timeLastSpawn = gameTime;
        }
    }

    public void levelOneSpawner() {
        Random rand = new Random();
        EnemyOne enemy = new EnemyOne(rand.nextInt((int) gameStage.getWidth()) + 1, gameStage.getHeight(), gameStage);
    }
    public void levelTwoSpawner() {
        Random rand = new Random();
        EnemyOne enemy = new EnemyOne(rand.nextInt((int) gameStage.getWidth()) + 1, gameStage.getHeight(), gameStage);
    }
    public void levelThreeSpawner() {
        Random rand = new Random();
        EnemyOne enemy = new EnemyOne(rand.nextInt((int) gameStage.getWidth()) + 1, gameStage.getHeight(), gameStage);
    }

    public void spawnLevel() {
        switch(level) {
            case 1:
                levelOneSpawner();
                break;
            case 2:
                levelTwoSpawner();
                break;
            case 3:
                levelThreeSpawner();
                break;
            default:
                levelOneSpawner();
                break;
        }
    }


    public void modifySpawnRate() {
        //-(atan(0.8x-3))+1.9
        // x = 9 equals 3 min of game
        // x = 0.05 equals 1 second

        //here we get the x value for atan function for time passed
        float x = (gameTime /2 / 10);
        //System.out.println("X: " + x);

        // here we get the y value which will modify the spawn rate!
        // we can just modify this function to change the spawnrate
        double y = -Math.atan((0.8F * x) -3)+1.9;
        //System.out.println("Y: " + y);
        spawnRate = (float)y;
        //System.out.println("Spawnrate:" + spawnRate);
        //System.out.println("spawnrate: " + y * spawnRate);
    }

    // This is the method called by level screens to spawn enemies
    public void continueManufacture(float delta) {
        spawnLevel();
        gameTime +=delta;
        modifySpawnRate();
    }




}
