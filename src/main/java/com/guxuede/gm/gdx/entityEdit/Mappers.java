package com.guxuede.gm.gdx.entityEdit;

import com.badlogic.ashley.core.ComponentMapper;
import com.guxuede.gm.gdx.component.*;
import com.guxuede.gm.gdx.component.ai.AiComponent;
import com.guxuede.gm.net.component.PlayerDataComponent;

/**
 * Created by guxuede on 2017/5/31 .
 */
public class Mappers {
    public static final ComponentMapper<TiledMapDataComponent> tiledMapDataCM = ComponentMapper.getFor(TiledMapDataComponent.class);

    public static final ComponentMapper<PositionComponent> positionCM = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<ActorStateComponent> actorStateCM = ComponentMapper.getFor(ActorStateComponent.class);
    public static final ComponentMapper<AnimationComponent> animationCM = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<ActorComponent> actorCM = ComponentMapper.getFor(ActorComponent.class);
    public static final ComponentMapper<ShadowComponent> shadowCM = ComponentMapper.getFor(ShadowComponent.class);
    public static final ComponentMapper<PresentableComponent> presentableCM = ComponentMapper.getFor(PresentableComponent.class);
    public static final ComponentMapper<ActorAnimationComponent> animationHolderCM = ComponentMapper.getFor(ActorAnimationComponent.class);
    public static final ComponentMapper<ActionsComponent> actionCM = ComponentMapper.getFor(ActionsComponent.class);
    public static final ComponentMapper<AttributeModifferComponent> attrModCM = ComponentMapper.getFor(AttributeModifferComponent.class);
    public static final ComponentMapper<SkillComponent> skillCM = ComponentMapper.getFor(SkillComponent.class);
    public static final ComponentMapper<BoundsComponent> boundsCM = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<SoundComponent> soundCM = ComponentMapper.getFor(SoundComponent.class);
    public static final ComponentMapper<BloodComponent> bloodCM = ComponentMapper.getFor(BloodComponent.class);
    public static final ComponentMapper<SensorComponent> sensorCM = ComponentMapper.getFor(SensorComponent.class);

    public static final ComponentMapper<PlayerDataComponent> netPackCM = ComponentMapper.getFor(PlayerDataComponent.class);
    public static final ComponentMapper<HurtComponent> hurtCM = ComponentMapper.getFor(HurtComponent.class);

    public static final ComponentMapper<AiComponent> aiCM = ComponentMapper.getFor(AiComponent.class);

}
