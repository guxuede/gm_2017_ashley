package com.guxuede.gm.gdx.entityEdit;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.actions.animation.AnimationAction;
import com.guxuede.gm.gdx.actions.appearance.ScaleByAction;
import com.guxuede.gm.gdx.actor.parser.ActorHolder;
import com.guxuede.gm.gdx.actor.parser.AnimationHolder;
import com.guxuede.gm.gdx.component.*;
import com.guxuede.gm.net.component.PlayerDataComponent;

import java.util.function.Consumer;

/**
 * Created by guxuede on 2017/6/10 .
 */
public abstract class EntityEditor<T extends EntityEditor>{
    private static final Family netIdfamily = Family.all(PlayerDataComponent.class).get();

    private EntityEdit edit = new EntityEdit();
    private Entity entity;

    public T editEntity(Entity entity) {
        this.entity = entity;
        return (T) this;
    }



    public final T createEntity() {
        editEntity(edit.createEntity());
        return (T) this;
    }

    public final T removeEntity(Entity entity) {
        edit.engine.removeEntity(entity);
        return (T) this;
    }

    public EntityEditor actorState() {
        actorState(ActorAnimationComponent.DOWN,false,0,0,0,0);
        return this;
    }

    public EntityEditor actorState(float speed) {
        actorState(ActorAnimationComponent.DOWN,false,0,0,0,0);
        return this;
    }

    public EntityEditor actorState(int direction,boolean isMoving, float vx, float vy, float acx ,float acy, float speed) {
        ActorStateComponent actorStateComponent = edit.create(ActorStateComponent.class);
        actorStateComponent.isMoving = isMoving;
        actorStateComponent.direction = direction;
        actorStateComponent.velocity.set(vx,vy);
        actorStateComponent.acceleration.set(acx,acy);
        actorStateComponent.speed = speed;
        entity.add(actorStateComponent);
        return this;
    }

    public EntityEditor actorState(int direction,boolean isMoving, float vx, float vy, float acx ,float acy) {
        actorState(direction, isMoving, vx, vy, acx, acy, 100);
        return this;
    }

    public EntityEditor blood(int hitPoint, int currentHitPoint) {
        BloodComponent bloodComponent = edit.create(BloodComponent.class);
        bloodComponent.hitPoint = hitPoint;
        bloodComponent.currentHitPoint = currentHitPoint;
        entity.add(bloodComponent);
        return this;
    }

    //----------------------------------as actor---------------------------------------------------------------------------
    public EntityEditor actor(String actorName) {
        return actor(actorName, ActorAnimationComponent.DOWN ,false,0);
    }

    public EntityEditor actor(String actorName, int direction, boolean isMoving, int zIndex) {
        ActorHolder actorHolder = ResourceManager.getActor(actorName);
        actor(actorHolder,direction,isMoving,zIndex);
        return this;
    }

    public EntityEditor actor(ActorHolder actorHolder) {
        return actor(actorHolder, ActorAnimationComponent.DOWN ,false,0);
    }

    public EntityEditor actor(ActorHolder actorHolder, int direction, boolean isMoving, int zIndex) {
        ActorAnimationComponent actorAnimationComponent = edit.create(ActorAnimationComponent.class);
        actorAnimationComponent.animationHolder = actorHolder.animationHolder;
        actorAnimationComponent.direction = direction;
        actorAnimationComponent.isMoving = isMoving;
        AnimationComponent animationComponent = edit.create(AnimationComponent.class);
        PresentableComponent presentableComponent = edit.create(PresentableComponent.class);
        presentableComponent.zIndex = zIndex;

        if(actorHolder.shadowWidth!= 0){
            ShadowComponent shadowComponent = edit.create(ShadowComponent.class);
            shadowComponent.width = actorHolder.shadowWidth;
            entity.add(shadowComponent);
        }

        entity.add(actorAnimationComponent);
        entity.add(animationComponent);
        entity.add(presentableComponent);
        return this;
    }

    //-------------------------------play actor animation(stop down)------------------------------------------------------
    public EntityEditor animation(String animationName, float rotation) {
        return animation(animationName, 0, rotation);
    }

    public EntityEditor animation(String animationName, int zIndex, float rotation) {
        AnimationComponent animationComponent = edit.create(AnimationComponent.class);
        animationComponent.animation = ResourceManager.getActorAnimation(animationName).getAnimation(AnimationHolder.STOP_DOWN_ANIMATION);
        animationComponent.maxStateTime = animationComponent.animation.getAnimationDuration();
        animationComponent.animationName = animationName;
        animationComponent.stateTime = 0;
        PresentableComponent presentableComponent = edit.create(PresentableComponent.class);
        presentableComponent.zIndex = zIndex;
        presentableComponent.rotation = rotation;
        entity.add(animationComponent);
        entity.add(presentableComponent);
        return this;
    }

    public EntityEditor animation(String animationName,Animation animation) {
        AnimationComponent animationComponent = edit.create(AnimationComponent.class);
        animationComponent.animation = animation;
        animationComponent.animationName = animationName;
        animationComponent.stateTime = 0;
        entity.add(animationComponent);
        return this;
    }
    //-------------------------------------------------------------------------------------------

    public EntityEditor presentable(String presentableName) {
        return presentable(presentableName, 0);
    }

    public EntityEditor presentable(String presentableName, float zIndex) {
        textureRegionPresentable(ResourceManager.getTextureRegion(presentableName),zIndex);
        return this;
    }

    public EntityEditor textureRegionPresentable(TextureRegion textureRegion, float zIndex) {
        PresentableComponent presentableComponent = edit.create(PresentableComponent.class);
        presentableComponent.region = textureRegion;
        presentableComponent.zIndex = zIndex;
        entity.add(presentableComponent);
        return this;
    }

    public EntityEditor sensor() {
        SensorComponent sensorComponent = edit.create(SensorComponent.class);
        entity.add(sensorComponent);
        return this;
    }

    public EntityEditor actions(Action... actions) {
        ActionsComponent actionsComponent = edit.create(ActionsComponent.class);
        for (Action action : actions) {
            actionsComponent.addAction(entity, action);
        }
        entity.add(actionsComponent);
        return this;
    }

    public EntityEditor pos(float x, float y) {
        PositionComponent positionComponent = edit.create(PositionComponent.class);
        positionComponent.position.set(x, y);
        entity.add(positionComponent);
        return this;
    }

    public EntityEditor asActor(){
        entity.add(edit.create(ActorComponent.class));
        return this;
    }

    public EntityEditor asRpgMarkerActor(String name, float x, float y){
        ActorHolder actor = ResourceManager.getActor(name);
        this.actorState().actor(actor).asActor().pos(x,y).actions().bounds((int) actor.bounds.getWidth(), (int) actor.bounds.getHeight())
                .blood(actor.blood,actor.blood)
                .sensor()
                .actions(new SequenceAction(
                        new AnimationAction("damage", 3f)
                        ,new AnimationAction("victory", 3f)
                        ,new AnimationAction("escape", 3f)
                        ,new AnimationAction("chant", 3f)
                        ,new AnimationAction("missile", 3f)
                        ,new AnimationAction("dying", 3f)
                        ,new AnimationAction("dead", 10f)
                        ,new ScaleByAction(1,1,10f)));
        SkillComponent skillComponent = edit.create(SkillComponent.class);
        skillComponent.skills.add(ResourceManager.getSkillById("burstFire"));
        skillComponent.skills.add(ResourceManager.getSkillById("huijian"));
        skillComponent.skills.add(ResourceManager.getSkillById("bow"));
        skillComponent.skills.add(ResourceManager.getSkillById("meteorite"));
        skillComponent.skills.add(ResourceManager.getSkillById("fireBall"));
        skillComponent.skills.add(ResourceManager.getSkillById("blink"));
        entity.add(skillComponent);
        return this;
    }

    public EntityEditor bounds(){
        entity.add(edit.create(BoundsComponent.class));
        return this;
    }

    public EntityEditor bounds(int w,int h){
        BoundsComponent component = edit.create(BoundsComponent.class);
        component.minx = 0;
        component.miny = 0;
        component.maxx = w;
        component.maxy = h;
        entity.add(component);
        return this;
    }


    public EntityEditor sound(String sound,boolean loop){
        return sound(ResourceManager.getSoundOrLoad(sound),loop);
    }

    public EntityEditor sound(Sound sound,boolean loop){
        SoundComponent component = edit.create(SoundComponent.class);
        component.soundId = -1;
        component.sound = sound;
        component.isLoop = loop;
        entity.add(component);
        return this;
    }

    public EntityEditor dynamicDirection(){
        DynamicDirectionComponent component = edit.create(DynamicDirectionComponent.class);
        entity.add(component);
        return this;
    }

    public EntityEditor canHurt(float hurtDamage,float hurtRadius){
        HurtComponent component = edit.create(HurtComponent.class);
        component.hurtDamage = hurtDamage;
        component.hurtRadius = hurtRadius;
        entity.add(component);
        return this;
    }

    public Entity build() {
        final Entity tmp = this.entity;
        entity = null;
        return tmp;
    }

    public Entity buildToWorld() {
        edit.addToEngine(this.entity);
        final Entity tmp = this.entity;
        entity = null;
        return tmp;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final <V  extends Component> T with(Class<V> component, Consumer<V> consumer) {
        V c = edit.create(component);
        consumer.accept(c);
        entity.add(c);
        return (T) this;
    }

    /**
     * Add artemis managed components to entity.
     */
    public  <V  extends Component> T with(V component) {
        entity.add(component);
        return (T) this;
    }


}
