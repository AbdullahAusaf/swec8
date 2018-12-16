package com.github.lhrb.nemo.actors.enemies;

import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.util.AnimationLoader;

public class EnemyTwo extends EnemyActor {

    /**
     * Simple Enemie Implementation
     * @author Thorsten Rösler
     *
     */

    private Weapon weapon;
    private float fireRate = 3f;

    public void setCharacteristics() {
        
        setAnimation(AnimationLoader.get().texture("gegner2.png"));
        //setRotation(180);
        setAcceleration(30);
        setSpeedMax(30);
        setDeceleration(1000000);
        setShapePolygon(8);

        setHp(4);
        setScoreValue(3);

        weapon = new WeaponSpread(fireRate, 45f);
    }

    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        weapon.act(delta);
        
        accelerationAtAngle(270);
        applyObjectPhysics(delta);
        
        if(getElapsedTime() > 4f) {
            weapon.fire(getX() + (getWidth() / 2), getY() - 40, 270);
        }
        
        /**
        if (getStage() != null && getY() + getHeight() - 10 < getStage().getHeight()) {
            weapon.fire(getX() + (getWidth() / 2), getY() - 40, 270);
        }
        */
    }
}
