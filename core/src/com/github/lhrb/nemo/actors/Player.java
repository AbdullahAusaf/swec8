/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponLaser;
import com.github.lhrb.nemo.actors.weapons.WeaponNormal;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * Simple player Implementation
 * @author exa
 * 
 */
public class Player extends PhysicalActor {

    private Weapon weapon;

    public Player(float x, float y, Stage stage) {
        super(x,y,stage);
        setAnimation(AnimationLoader.loadTexture("player.png"));

        setAcceleration(3600);
        setSpeedMax(800);
        setDeceleration(100000);

        weapon = new WeaponNormal(getStage());
        setShapePolygon(8);
        
        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        setWorldDimension(this.getStage().getWidth(), this.getStage().getHeight());
        setBoundToWorld();

    }
    
 
    
    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            accelerationAtAngle(180);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            accelerationAtAngle(0);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            accelerationAtAngle(90);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            accelerationAtAngle(270);
        }

        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            weapon.fire(getX()+(getWidth()/2), getY()+(getHeight()), 90);
        }

        // Zum Testen der Waffen! Sollte später über das gleiche System wie Power-Ups geregelt werden können
        if(Gdx.input.isKeyPressed(Keys.F1)) {
            weapon.remove();
            weapon = new WeaponNormal(getStage());
        }
        if(Gdx.input.isKeyPressed(Keys.F2)) {
            weapon.remove();
            weapon = new WeaponSpread(getStage());
        }
        if(Gdx.input.isKeyPressed(Keys.F3)) {
            weapon.remove();
            weapon = new WeaponLaser(getStage());
        }
        
        applyPhysics(delta);
        
      
        
        
    }



    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision() {
        System.out.println("Player collision");
    }

}
