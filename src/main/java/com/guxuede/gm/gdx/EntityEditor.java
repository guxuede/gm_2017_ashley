package com.guxuede.gm.gdx;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.component.*;

/**
 * Created by guxuede on 2017/6/10 .
 */
public abstract class EntityEditor<T extends EntityEditor>{

    private EntityEdit edit;
    private Entity entity;

    protected T editEntity(PooledEngine engine, Entity entity) {
        this.entity = entity;
        this.edit = new EntityEdit(engine);
        return (T) this;
    }

    protected final T createEntity(PooledEngine engine) {
        editEntity(engine,engine.createEntity());
        return (T) this;
    }

    public EntityEditor actorState() {
        actorState(ActorAnimationComponent.DOWN,false,0,0,0,0);
        return this;
    }
    public EntityEditor actorState(int direction,boolean isMoving, float vx, float vy, float acx ,float acy) {
        ActorStateComponent actorStateComponent = edit.create(ActorStateComponent.class);
        actorStateComponent.isMoving = isMoving;
        actorStateComponent.direction = direction;
        actorStateComponent.velocity.set(vx,vy);
        actorStateComponent.acceleration.set(acx,acy);
        entity.add(actorStateComponent);
        return this;
    }

    public EntityEditor actorAnimation(String animationName) {
        return actorAnimation(animationName, ActorAnimationComponent.DOWN ,false,0);
    }

    public EntityEditor actorAnimation(String animationName,int direction,boolean isMoving, int zIndex) {
        AnimationHolder animationHolder = ResourceManager.getAnimationHolder(animationName);
        ActorAnimationComponent actorAnimationComponent = edit.create(ActorAnimationComponent.class);
        actorAnimationComponent.animationHolder = animationHolder;
        actorAnimationComponent.direction = direction;
        actorAnimationComponent.isMoving = isMoving;
        AnimationComponent animationComponent = edit.create(AnimationComponent.class);
        PresentableComponent presentableComponent = edit.create(PresentableComponent.class);
        presentableComponent.zIndex = zIndex;
        entity.add(actorAnimationComponent);
        entity.add(animationComponent);
        entity.add(presentableComponent);
        return this;
    }

    public EntityEditor animation(String animationName) {
        return animation(animationName, 0);
    }

    public EntityEditor animation(String animationName, int zIndex) {
        AnimationComponent animationComponent = edit.create(AnimationComponent.class);
        animationComponent.animation = ResourceManager.getAnimationHolder(animationName).getAnimation(AnimationHolder.STOP_DOWN_ANIMATION);
        PresentableComponent presentableComponent = edit.create(PresentableComponent.class);
        presentableComponent.zIndex = zIndex;
        entity.add(animationComponent);
        entity.add(presentableComponent);
        return this;
    }

    public EntityEditor presentable(String presentableName) {
        return presentable(presentableName, 0);
    }

    public EntityEditor presentable(String presentableName, int zIndex) {
        PresentableComponent presentableComponent = edit.create(PresentableComponent.class);
        presentableComponent.region = ResourceManager.getTextureRegion(presentableName);
        presentableComponent.zIndex = zIndex;
        entity.add(presentableComponent);
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

    public EntityEditor bounds(){
        entity.add(edit.create(BoundsComponent.class));
        return this;
    }



    public Entity build() {
        final Entity tmp = this.entity;
        entity = null;
        edit = null;
        return tmp;
    }

    public Entity buildToWorld() {
        edit.engine.addEntity(this.entity);
        final Entity tmp = this.entity;
        entity = null;
        edit = null;
        return tmp;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final T with(Class<? extends Component> component) {
        edit.create(component);
        return (T) this;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final T with(Class<? extends Component> component1, Class<? extends Component> component2) {
        edit.create(component1);
        edit.create(component2);
        return (T) this;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final T with(Class<? extends Component> component1, Class<? extends Component> component2, Class<? extends Component> component3) {
        edit.create(component1);
        edit.create(component2);
        edit.create(component3);
        return (T) this;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final T with(Class<? extends Component> component1, Class<? extends Component> component2, Class<? extends Component> component3, Class<? extends Component> component4) {
        edit.create(component1);
        edit.create(component2);
        edit.create(component3);
        edit.create(component4);
        return (T) this;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final T with(Class<? extends Component> component1, Class<? extends Component> component2, Class<? extends Component> component3, Class<? extends Component> component4, Class<? extends Component> component5) {
        edit.create(component1);
        edit.create(component2);
        edit.create(component3);
        edit.create(component4);
        edit.create(component5);
        return (T) this;
    }

    /**
     * Add artemis managed components to entity.
     */
    public final T with(Class<? extends Component>... components) {
        for (int i = 0, n = components.length; i < n; i++) {
            edit.create(components[i]);
        }
        return (T) this;
    }

}
