/**
 * 
 */
package com.github.lhrb.nemo.actors.powerups;

import java.util.HashMap;

/**
 * @author exa
 *
 */
public class Collectibles {
    
   private static Collectibles collectables;
   private HashMap<CType, String> collectibles;
   private HashMap<CType, Integer> powerupTime;
   
   private Collectibles() {
       collectibles = new HashMap<CType, String>();
       powerupTime = new HashMap<CType, Integer>();
       init();
   }
   
   public static Collectibles get() {
       if(collectables == null) {
           collectables = new Collectibles();
       }
       return collectables;
   }
   
   private void init() {
       //Powerups
       collectibles.put(CType.Bomb, "powerup_bombe.png");
       collectibles.put(CType.Heart, "powerup_heart.png");
       collectibles.put(CType.Multiplicator, "powerup_multiplicator.png");
       collectibles.put(CType.Shield, "powerup_shield.png");
       collectibles.put(CType.Star, "powerup_star.png");

       powerupTime.put(CType.Multiplicator, 15);
       powerupTime.put(CType.Shield, 12);
       powerupTime.put(CType.Star, 10);

       //Weapons
       collectibles.put(CType.Laser, "weapon_laser.png");
       collectibles.put(CType.Spread, "weapon_spread.png");
       collectibles.put(CType.Normal, "weapon_normal.png");
   }
   
   /**
    * 
    * @param key
    * @return
    */
   public String getPath(CType key) {
       return collectibles.get(key);
   }

   public int getPowerupTime(CType key) {
       return powerupTime.getOrDefault(key, 0);
   }

   
}
