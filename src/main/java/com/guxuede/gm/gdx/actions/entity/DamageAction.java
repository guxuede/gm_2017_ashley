//package com.guxuede.gm.gdx.actions.entity;
//
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.Array;
//import com.guxuede.gm.gdx.Mappers;
//import com.guxuede.gm.gdx.actions.Action;
//import com.guxuede.gm.gdx.component.ActorComponent;
//import com.guxuede.gm.gdx.component.ActorStateComponent;
//
//import java.util.Iterator;
//
///**
// * Created by guxuede on 2017/6/30 .
// */
//public class DamageAction extends Action {
//
//    private final Vector2 point = new Vector2();
//    private float radius;
//    private float hurtPoint;
//
//    @Override
//    public boolean act(float delta) {
//        Array<AnimationEntity> animationEntities = MathUtils.findClosestPointEntry(actor.getStage().getActors(),point,radius);
//        Iterator<AnimationEntity> iterator = animationEntities.iterator();
//        while (iterator.hasNext()){
//            AnimationEntity animationEntity = iterator.next();
//            animationEntity.currentHitPoint = animationEntity.currentHitPoint - hurtPoint;
//        }
//
//        ActorStateComponent actorComponent = Mappers.actorStateCM.get(actor);
//        if(actorComponent!=null){
//            actorComponent.currentHitPoint -=
//        }
//        return true;
//    }
//
//
//    @Override
//    public void reset() {
//        super.reset();
//        point.set(0,0);
//        radius = 0;
//        hurtPoint = 0;
//    }
//
//    public DamageAction setHurtPoint(float hurtPoint) {
//        this.hurtPoint = hurtPoint;
//        return this;
//    }
//
//    public DamageAction setPoint(Vector2 point) {
//        this.point.set(point);
//        return this;
//    }
//
//    public DamageAction setRadius(float radius) {
//        this.radius = radius;
//        return this;
//    }
//}
