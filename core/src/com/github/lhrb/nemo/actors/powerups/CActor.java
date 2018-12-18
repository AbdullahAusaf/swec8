package com.github.lhrb.nemo.actors.powerups;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.actors.Existence;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public class CActor extends PhysicalActor implements Existence{
    CType type;

    public CActor(float x, float y, Stage stage, CType type) {
        super(x,y,stage);
        this.type = type;
        setAnimation(AnimationLoader.get().texture(Collectibles.get().getPath(type)));
        setAcceleration(1000);
        setSpeedMax(75);
        setShapePolygon(8);
    }
    
    public CType getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision(CollisionEvent col){
        remove();
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.ActorPrefab#act(float)
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        accelerationAtAngle(270);
        applyObjectPhysics(delta);
    }

    @Override
    public void perish() {
        // TODO Auto-generated method stub
        
    }
}