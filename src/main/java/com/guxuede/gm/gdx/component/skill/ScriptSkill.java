package com.guxuede.gm.gdx.component.skill;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.guxuede.gm.gdx.actions.A;
import com.guxuede.gm.gdx.actions.Action;
import com.guxuede.gm.gdx.actions.SequenceAction;
import com.guxuede.gm.gdx.basic.libgdx.MathUtils;
import com.guxuede.gm.gdx.component.PositionComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.gdx.actions.GdxSequenceAction;
import com.guxuede.gm.net.client.registry.pack.ActorPlayAnimationPack;
import com.guxuede.gm.net.client.registry.pack.ActorPlaySkillPack;
import com.guxuede.gm.net.component.PlayerDataComponent;

/**
 * Created by guxuede on 2016/9/27 .
 */
public class ScriptSkill extends Skill {

    private String script;
    private GdxSequenceAction action;
    private String description;
    private SkillTargetTypeEnum targetType;
    private int hotKey;

    private String spellcastAnimation;
    private float spellcastAnimationDuration;
    private float actionDelay;

    private float skillCooldownTime = 60;
    private float skillCooldownCounter = 0;
    private Drawable icon;

    public int getHotKey() {
        return hotKey;
    }

    public SkillTargetTypeEnum getTargetType() {
        return targetType;
    }

    @Override
    public Action play() {
        PlayerDataComponent playerDataComponent1 = Mappers.netPackCM.get(owner);
        String skillId = this.getId();
        float targetX = this.targetPos.x;
        float targetY = this.targetPos.y;
        Entity owner = this.owner;

        if(playerDataComponent1!=null){
            SequenceAction spellcast = A.sequence(A.run(new Runnable() {
                @Override
                public void run() {
                    PositionComponent positionComponent = Mappers.positionCM.get(owner);
                    float directionInDegrees = MathUtils.getAngle(positionComponent.position.x, positionComponent.position.y, targetX, targetY);
                    PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(owner);
                    ActorPlayAnimationPack playAnimationPack = new ActorPlayAnimationPack(playerDataComponent.id, spellcastAnimation, directionInDegrees, spellcastAnimationDuration);
                    playerDataComponent.outBoundPack(playAnimationPack);
                }
            }), A.delay(actionDelay), A.run(new Runnable() {
                @Override
                public void run() {
                    PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(owner);
                    ActorPlaySkillPack playSkillPack = new ActorPlaySkillPack(playerDataComponent.id, skillId, targetX, targetY);
                    playerDataComponent.outBoundPack(playSkillPack);
                }
            }));
            Mappers.actionCM.get(owner).addAction(owner,spellcast);
            return spellcast;
        }
        return A.sequence();
    }

    @Override
    public boolean update(float delta) {
        return action.isAllGDXActionEnd();
    }

    @Override
    public void exit() {
        super.exit();
        //如果SkillAction（技能前摇），还有的没结束，直接就结束。如果已经结束，就不要remove了，让其他的继续执行。前摇是可以打断的
        if (action != null && !action.isAllGDXActionEnd()) {
            action.stopToThisAction();
        }
    }

    @Override
    public void reset() {
        super.reset();
        action = null;
        skillCooldownCounter = 0;
    }

    public void setHotKey(int hotKey) {
        this.hotKey = hotKey;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScript() {
        return script;
    }

    public void setTargetType(SkillTargetTypeEnum targetType) {
        this.targetType = targetType;
    }

    public float getSkillCooldownTime() {
        return skillCooldownTime;
    }

    public void setSkillCooldownTime(float skillCooldownTime) {
        this.skillCooldownTime = skillCooldownTime;
    }

    public float getSkillCooldownCounter() {
        return skillCooldownCounter;
    }

    public void setSkillCooldownCounter(float skillCooldownCounter) {
        this.skillCooldownCounter = skillCooldownCounter;
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public ScriptSkill clone(){
        ScriptSkill scriptSkill = new ScriptSkill();
        scriptSkill.setSkillCooldownTime(skillCooldownTime);
        scriptSkill.setSkillCooldownCounter(0);
        scriptSkill.setIcon(icon);
        scriptSkill.setHotKey(hotKey);
        scriptSkill.setTargetType(targetType);
        scriptSkill.setScript(script);
        scriptSkill.isAvailable = true;
        scriptSkill.setDescription(description);
        return scriptSkill;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpellcastAnimation() {
        return spellcastAnimation;
    }

    public void setSpellcastAnimation(String spellcastAnimation) {
        this.spellcastAnimation = spellcastAnimation;
    }

    public float getSpellcastAnimationDuration() {
        return spellcastAnimationDuration;
    }

    public void setSpellcastAnimationDuration(float spellcastAnimationDuration) {
        this.spellcastAnimationDuration = spellcastAnimationDuration;
    }

    public float getActionDelay() {
        return actionDelay;
    }

    public void setActionDelay(float actionDelay) {
        this.actionDelay = actionDelay;
    }
}
