package com.guxuede.gm.gdx;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.guxuede.gm.gdx.component.*;

/**
 * Created by guxuede on 2017/5/31 .
 */
public class Mappers {
    public static final ComponentMapper<PositionComponent> positionCM = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<ActorStateComponent> actorStateCM = ComponentMapper.getFor(ActorStateComponent.class);
    public static final ComponentMapper<AnimationComponent> animationCM = ComponentMapper.getFor(AnimationComponent.class);
    public static final ComponentMapper<ActorComponent> actorCM = ComponentMapper.getFor(ActorComponent.class);
    public static final ComponentMapper<PresentableComponent> presentableCM = ComponentMapper.getFor(PresentableComponent.class);
    public static final ComponentMapper<ActorAnimationComponent> animationHolderCM = ComponentMapper.getFor(ActorAnimationComponent.class);
    public static final ComponentMapper<ActionsComponent> actionCM = ComponentMapper.getFor(ActionsComponent.class);
    public static final ComponentMapper<SkillComponent> skillCM = ComponentMapper.getFor(SkillComponent.class);
    public static final ComponentMapper<BoundsComponent> boundsCM = ComponentMapper.getFor(BoundsComponent.class);
    public static final ComponentMapper<SoundComponent> soundCM = ComponentMapper.getFor(SoundComponent.class);
    public static final ComponentMapper<BloodComponent> bloodCM = ComponentMapper.getFor(BloodComponent.class);


}
