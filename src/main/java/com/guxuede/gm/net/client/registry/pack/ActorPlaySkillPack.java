package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.ResourceManager;
import com.guxuede.gm.gdx.actions.GdxSequenceAction;
import com.guxuede.gm.gdx.component.skill.ScriptSkill;
import com.guxuede.gm.gdx.component.skill.Skill;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class ActorPlaySkillPack extends NetPack implements PlayerPack {
    private int id;
    public String skillId;
    public float targetX;
    public float targetY;


    public ActorPlaySkillPack(int id, String skillId, float targetX, float targetY) {
        this.id = id;
        this.skillId = skillId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public ActorPlaySkillPack(ByteBuf data) {
        id = data.readInt();
        skillId = PackageUtils.readString(data);
        targetX = data.readFloat();
        targetY = data.readFloat();
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        PackageUtils.writeString(skillId,data);
        data.writeFloat(targetX);
        data.writeFloat(targetY);
    }


    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void action(Engine engine, Entity entity) {
        ScriptSkill skillById = (ScriptSkill) ResourceManager.getSkillById(skillId);
        ScriptEngine scriptEngine = ResourceManager.getScriptEngine();
        SimpleBindings simpleBindings = new SimpleBindings();
        simpleBindings.put("targetEntry",entity);
        simpleBindings.put("targetPos", new Vector2(targetX, targetY));
        simpleBindings.put("owner",entity);
        try {
            GdxSequenceAction action = (GdxSequenceAction) scriptEngine.eval(skillById.getScript(),simpleBindings);
            Mappers.actionCM.get(entity).addAction(entity,action);
        } catch (ScriptException e) {
            throw new RuntimeException("Skill error",e);
        }
    }

}
