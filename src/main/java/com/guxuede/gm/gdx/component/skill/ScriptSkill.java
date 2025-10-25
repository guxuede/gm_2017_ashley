package com.guxuede.gm.gdx.component.skill;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
    public void enter() {
        PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(owner);
        if(playerDataComponent!=null){
            ActorPlayAnimationPack playAnimationPack = new ActorPlayAnimationPack(playerDataComponent.id,"skill",1);
            playerDataComponent.outBoundPack(playAnimationPack);
            ActorPlaySkillPack playSkillPack = new ActorPlaySkillPack(playerDataComponent.id, this.getId() ,targetPos.x, targetPos.y);
            playerDataComponent.outBoundPack(playSkillPack);
        }
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
}
