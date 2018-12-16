package com.github.lhrb.nemo.actors.weapons;

import com.github.lhrb.nemo.actors.shots.SingleShot;
import com.github.lhrb.nemo.util.SoundManager;

public class WeaponSpread extends Weapon {
    private float angleModifier;

    public WeaponSpread() {
        super(0.6f);
        angleModifier = 25;
    }

    public WeaponSpread(float cooldown) {
        super(cooldown);
    }

    public WeaponSpread(float cooldown,float angleModifier) {
        super(cooldown);
        this.angleModifier = angleModifier;
    }

    @Override
    public void fire(float x, float y, float angle) {
        if (isReady()) {
            new SingleShot(x, y, angle - angleModifier);
            new SingleShot(x, y, angle);
            new SingleShot(x, y, angle + angleModifier);
            resetCooldownTimer();

            SoundManager.getInstance().playSound("laser");
        }
    }
}
