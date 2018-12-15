package com.github.lhrb.nemo.actors.shots;

import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.actors.Existence;
import com.github.lhrb.nemo.actors.PhysicalActor;

public abstract class Shots extends PhysicalActor implements Existence{

    private final float angle;
    private boolean isPlayerShot;

    public Shots(float x, float y, float angle) {
        super(x, y, KillingNemo.getGameStage());

        this.angle = angle;
        isPlayerShot = (angle > 0 && angle < 180);
        rotateBy(angle-90);
        setX(getX()-(getWidth()/2));

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accelerationAtAngle(angle);

        if (getY() <= getStage().getHeight()) {
            applyPhysics(delta);
        }
        else {
            remove();
        }

        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        //setWorldBounds();

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision(CollisionEvent col) {
        if (!(this instanceof Bomb.Explosion))
            remove();
    }
    
    @Override
    public void perish() {
        
    }

    public boolean isPlayerShot() { return isPlayerShot; }

}
