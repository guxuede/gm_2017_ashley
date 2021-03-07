package com.guxuede.gm.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.guxuede.gm.gdx.actions.*;
import com.guxuede.gm.gdx.actions.appearance.ScaleByAction;
import com.guxuede.gm.gdx.actions.entity.EffectsActorAction;
import com.guxuede.gm.gdx.actions.entity.RemoveEntityAction;
import com.guxuede.gm.gdx.actions.movement.ActorMoveAsSnackToPointAction;
import com.guxuede.gm.gdx.actions.movement.BezierMoveAction;
import com.guxuede.gm.gdx.actions.movement.BlinkAction;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;

/**
 * Created by guxuede on 2017/6/8 .
 */
public final class A {
    private A(){

    }
    /** Returns a new or pooled action of the specified type. */
    static public <T extends Action> T action (Class<T> type) {
        Pool<T> pool = Pools.get(type);
        T action = pool.obtain();
        action.setPool(pool);
        return action;
    }

    static public DelayAction delay (float duration) {
        DelayAction action = action(DelayAction.class);
        action.setDuration(duration);
        return action;
    }

    static public DelayAction delay (float duration, Action delayedAction) {
        DelayAction action = action(DelayAction.class);
        action.setDuration(duration);
        action.setAction(delayedAction);
        return action;
    }

    static public SequenceAction sequence (Action action1) {
        SequenceAction action = action(SequenceAction.class);
        action.addAction(action1);
        return action;
    }

    static public SequenceAction sequence (Action action1, Action action2) {
        SequenceAction action = action(SequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        return action;
    }

    static public SequenceAction sequence (Action action1, Action action2, Action action3) {
        SequenceAction action = action(SequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        return action;
    }

    static public SequenceAction sequence (Action action1, Action action2, Action action3, Action action4) {
        SequenceAction action = action(SequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        return action;
    }

    static public SequenceAction sequence (Action action1, Action action2, Action action3, Action action4, Action action5) {
        SequenceAction action = action(SequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        action.addAction(action5);
        return action;
    }

    static public SequenceAction sequence (Action... actions) {
        SequenceAction action = action(SequenceAction.class);
        for (int i = 0, n = actions.length; i < n; i++)
            action.addAction(actions[i]);
        return action;
    }

    static public SequenceAction sequence () {
        return action(SequenceAction.class);
    }

    static public ParallelAction parallel (Action action1) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        return action;
    }

    static public ParallelAction parallel (Action action1, Action action2) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        return action;
    }

    static public ParallelAction parallel (Action action1, Action action2, Action action3) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        return action;
    }

    static public ParallelAction parallel (Action action1, Action action2, Action action3, Action action4) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        return action;
    }

    static public ParallelAction parallel (Action action1, Action action2, Action action3, Action action4, Action action5) {
        ParallelAction action = action(ParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        action.addAction(action5);
        return action;
    }

    static public ParallelAction parallel (Action... actions) {
        ParallelAction action = action(ParallelAction.class);
        for (int i = 0, n = actions.length; i < n; i++)
            action.addAction(actions[i]);
        return action;
    }

    static public ParallelAction parallel () {
        return action(ParallelAction.class);
    }

    static public RepeatAction repeat (int count, Action repeatedAction) {
        RepeatAction action = action(RepeatAction.class);
        action.setCount(count);
        action.setAction(repeatedAction);
        return action;
    }

    static public RepeatAction forever (Action repeatedAction) {
        RepeatAction action = action(RepeatAction.class);
        action.setCount(RepeatAction.FOREVER);
        action.setAction(repeatedAction);
        return action;
    }

    static public RunnableAction run (Runnable runnable) {
        RunnableAction action = action(RunnableAction.class);
        action.setRunnable(runnable);
        return action;
    }

    static public AfterAction after (Action action) {
        AfterAction afterAction = action(AfterAction.class);
        afterAction.setAction(action);
        return afterAction;
    }

    static public RemoveEntityAction removeEntityAction () {
        RemoveEntityAction removeEntityAction = action(RemoveEntityAction.class);
        return removeEntityAction;
    }
    //////////////////////////////////////////////////////////////工具类 START///////////////////////////////////////////////////////////////////////
    static public GdxSequenceAction gdxSequence (Action action1) {
        GdxSequenceAction action = action(GdxSequenceAction.class);
        action.addAction(action1);
        return action;
    }


    static public GdxSequenceAction gdxSequence (Action action1, Action action2) {
        GdxSequenceAction action = action(GdxSequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        return action;
    }

    static public GdxSequenceAction gdxSequence (Action action1, Action action2, Action action3) {
        GdxSequenceAction action = action(GdxSequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        return action;
    }

    static public GdxSequenceAction gdxSequence (Action action1, Action action2, Action action3, Action action4) {
        GdxSequenceAction action = action(GdxSequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        return action;
    }

    static public GdxSequenceAction gdxSequence (Action action1, Action action2, Action action3, Action action4, Action action5) {
        GdxSequenceAction action = action(GdxSequenceAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        action.addAction(action5);
        return action;
    }

    static public GdxSequenceAction gdxSequence (Action... actions) {
        GdxSequenceAction action = action(GdxSequenceAction.class);
        for (int i = 0, n = actions.length; i < n; i++)
            action.addAction(actions[i]);
        return action;
    }

    static public GdxSequenceAction gdxSequence () {
        return action(GdxSequenceAction.class);
    }

    static public GdxParallelAction gdxParallel (Action action1) {
        GdxParallelAction action = action(GdxParallelAction.class);
        action.addAction(action1);
        return action;
    }

    static public GdxParallelAction gdxParallel (Action action1, Action action2) {
        GdxParallelAction action = action(GdxParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        return action;
    }

    static public GdxParallelAction gdxParallel (Action action1, Action action2, Action action3) {
        GdxParallelAction action = action(GdxParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        return action;
    }

    static public GdxParallelAction gdxParallel (Action action1, Action action2, Action action3, Action action4) {
        GdxParallelAction action = action(GdxParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        return action;
    }

    static public GdxParallelAction gdxParallel (Action action1, Action action2, Action action3, Action action4, Action action5) {
        GdxParallelAction action = action(GdxParallelAction.class);
        action.addAction(action1);
        action.addAction(action2);
        action.addAction(action3);
        action.addAction(action4);
        action.addAction(action5);
        return action;
    }

    static public GdxParallelAction gdxParallel (Action... actions) {
        GdxParallelAction action = action(GdxParallelAction.class);
        for (int i = 0, n = actions.length; i < n; i++)
            action.addAction(actions[i]);
        return action;
    }

    static public GdxParallelAction gdxParallel () {
        return action(GdxParallelAction.class);
    }
    //////////////////////////////////////////////////////////////工具类 END///////////////////////////////////////////////////////////////////////

    public static  BlinkAction blink(Entity owner, Vector2 pos, String effectName){
        BlinkAction blinkAction = action(BlinkAction.class);
        blinkAction.setTargetPosition(pos);
        return blinkAction;
    }

    public  static Action effectsActorOnActorPosAction(final String effectName){
        Action runnableAction = new Action() {
            @Override
            public boolean act(float delta) {
                Mappers.actionCM.get(actor).addAction(actor,effectsActorAction(effectName,Mappers.positionCM.get(actor).position));
                return true;
            }
        };
        return runnableAction;
    }

    /**
     * 创建单位的action
     * @param effectName
     * @param pos
     * @return
     */
    public  static EffectsActorAction effectsActorAction(String effectName, Vector2 pos){
        float duration = ResourceManager.getAnimationHolder(effectName).getAnimation(AnimationHolder.STOP_DOWN_ANIMATION).getAnimationDuration();
        EffectsActorAction action = action(EffectsActorAction.class);
        action.setPos(pos);
        action.setActorName(effectName);
        action.setActions(sequence(delay(duration),removeEntityAction()));
        return action;
    }

    /**
     * 创建单位的action
     * @param effectName
     * @param pos
     * @return
     */
    public  static EffectsActorAction effectsActorActionWithDeathAction(String effectName, Vector2 pos, Action actions,Action deathAction){
        EffectsActorAction action = action(EffectsActorAction.class);
        action.setPos(pos);
        action.setActorName(effectName);
        action.setActions(actions);

        return action;
    }

    /**
     * 在一点上创建特效
     * @param owner             特效施放者
     * @param pos               释放位置
     * @param effectName       特效名字
     * @return
     */
    public static Action createEffectAction(Entity owner, Vector2 pos, String effectName, float intervalTime){
        SequenceAction sequenceAction = sequence();
        Vector2 ownerPos = Mappers.positionCM.get(owner).position;
        sequenceAction.addAction(delay(intervalTime));
        sequenceAction.addAction(parallel(effectsActorAction(effectName,pos)));
        return sequenceAction;
    }

    /**
     * 在一条线上创建特效
     * @param owner             特效施放者
     * @param pos               释放位置
     * @param effectName       特效名字
     * @param intervalTime      间隔多久产生特效
     * @param intervalDistance 间隔多远产生单位
     * @param unit              产生多少个
     * @return
     */
    public static Action createLineEffectEntriesAction(Entity owner, Vector2 pos, String effectName, float intervalTime, float intervalDistance, int unit){
        SequenceAction sequenceAction = sequence();
        Vector2 ownerPos = Mappers.positionCM.get(owner).position;
        Vector2 directionNor = TempObjects.temp1Vector2.set(pos).sub(Mappers.positionCM.get(owner).position).nor();
        for (int i = 0; i < unit; i++) {
            sequenceAction.addAction(delay(intervalTime));
            Vector2 effectPoint = TempObjects.temp2Vector2.set(directionNor).scl(i*intervalDistance).add(ownerPos);
            sequenceAction.addAction(parallel(effectsActorAction(effectName,effectPoint)));
        }
        return sequenceAction;
    }

    public static Action deleteSelf(){
        return new Action() {
            @Override
            public boolean act(float delta) {
                E.remove(getActor());
                return true;
            }
        };
    }

    /**
     * 在一条线上创建特效
     * @param owner             特效施放者
     * @param pos               释放位置
     * @param effectName       特效名字
     * @param intervalTime      间隔多久产生特效
     * @param intervalDistance 间隔多远产生单位
     * @param unit              产生多少个
     * @param hurtPoint         伤害多少
     * @param hurtIntervalTime  间隔多久伤害一次
     * @param hurtTimes           伤害多少次
     * @return
     */
    public static Action createLineEffectEntriesAction(Entity owner, Vector2 pos, String effectName, float intervalTime, float intervalDistance, int unit, float hurtPoint, float hurtIntervalTime, int hurtTimes){
        SequenceAction sequenceAction = sequence();
        Vector2 ownerPos = Mappers.positionCM.get(owner).position;
        Vector2 directionNor = TempObjects.temp1Vector2.set(pos).sub(Mappers.positionCM.get(owner).position).nor();
        for (int i = 0; i < unit; i++) {
            Vector2 effectPoint = TempObjects.temp2Vector2.set(directionNor).scl(i*intervalDistance).add(ownerPos);
            sequenceAction.addAction(
                    delay(intervalTime,
                            parallel(
                                    effectsActorAction(effectName, effectPoint
                                    )
                            )
                    )
            );
        }
        return sequenceAction;
    }

    public static Action createFireBall(Entity owner, Vector2 pos, String effectName){
        Vector2 ownerPos = Mappers.positionCM.get(owner).position;
        E.create().actions(sequence(new ActorMoveAsSnackToPointAction(pos.x,pos.y),effectsActorOnActorPosAction("special10"),deleteSelf()))
                .actorState()
                .dynamicDirection()
                .actorAnimation("fireBall")
                .pos(ownerPos.x,ownerPos.y)
                .buildToWorld();
        SequenceAction sequenceAction = sequence();
        return sequenceAction;
    }
}
