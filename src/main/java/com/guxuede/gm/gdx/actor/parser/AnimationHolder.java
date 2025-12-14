package com.guxuede.gm.gdx.actor.parser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

import java.util.Optional;

/**
 * Created by guxuede on 2016/9/16 .
 */
public class AnimationHolder {
    public static final int DIRECTION_DOWN=1,DIRECTION_LEFT=2,DIRECTION_RIGHT=3,DIRECTION_UP=4;

    public static final int WALK = "walk".hashCode();
    public static final int IDLE = "idle".hashCode();
    public static final int SLASH = "slash".hashCode();
    public static final int DEATH = "death".hashCode();
    public static final int SPELLCAST = "spellcast".hashCode();
    public static final int SIT = "sit".hashCode();


    public static final int[] allAnimations = new int[]{WALK,IDLE,DEATH,SPELLCAST};

    /*********************************************/
    private IntMap<IntMap<Animation>> animationMap = new IntMap<>();
    public int width,height;
    public String name;
    /*********************************************/


    public AnimationHolder(){

    }

    public AnimationHolder(Animation allInOneAnimation){
        for(int i=0;i< allAnimations.length;i++){
            IntMap<Animation> directionAnimationMap = new IntMap<>();
            directionAnimationMap.put(DIRECTION_DOWN, allInOneAnimation);
            directionAnimationMap.put(DIRECTION_LEFT, allInOneAnimation);
            directionAnimationMap.put(DIRECTION_RIGHT, allInOneAnimation);
            directionAnimationMap.put(DIRECTION_UP, allInOneAnimation);
            animationMap.put(allAnimations[0], directionAnimationMap);
        }
    }

    public void addAnimation(int name, Animation animation){
        IntMap<Animation> directionAnimationMap = new IntMap<>();
        animationMap.put(name,directionAnimationMap);
        directionAnimationMap.put(DIRECTION_DOWN, animation);
        directionAnimationMap.put(DIRECTION_LEFT, animation);
        directionAnimationMap.put(DIRECTION_RIGHT, animation);
        directionAnimationMap.put(DIRECTION_UP, animation);
    }

    public void addAnimation(int name, int direction, Animation animation){
        IntMap<Animation> directionAnimationMap;
        if(animationMap.containsKey(name)){
            directionAnimationMap = animationMap.get(name);
        }else{
            directionAnimationMap = new IntMap<>();
            animationMap.put(name,directionAnimationMap);
        }
        directionAnimationMap.put(direction,animation);
    }

    @Deprecated
    public Animation getAnimation(int name){
        return getAnimation(name, DIRECTION_DOWN);
    }

    public Animation getAnimation(int name, int direction){
        return Optional.ofNullable(animationMap.get(name)).orElse(animationMap.get(IDLE)).get(direction);
    }


}
