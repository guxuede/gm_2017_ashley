package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.actions.animation.AnimationAction;
import com.guxuede.gm.gdx.component.ActionsComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class ActorPlayAnimationPack extends NetPack implements PlayerPack {
    private int id;
    public String animationName;
    public float directionInDegrees = -1;// < 0 not working
    public float duration;


    public ActorPlayAnimationPack(int id, String animationName, float directionInDegrees, float duration) {
        this.id = id;
        this.animationName = animationName;
        this.directionInDegrees = directionInDegrees;
        this.duration = duration;
    }

    public ActorPlayAnimationPack(int id, String animationName, float duration) {
        this.id = id;
        this.animationName = animationName;
        this.directionInDegrees = -1f;
        this.duration = duration;
    }

    public ActorPlayAnimationPack(ByteBuf data) {
        id = data.readInt();
        animationName = PackageUtils.readString(data);
        directionInDegrees = data.readFloat();
        duration = data.readFloat();
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        PackageUtils.writeString(animationName,data);
        data.writeFloat(directionInDegrees);
        data.writeFloat(duration);
    }


    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void action(Engine engine, Entity entity) {
        ActionsComponent actionsComponent = Mappers.actionCM.get(entity);
        actionsComponent.addAction(entity, new AnimationAction(animationName, directionInDegrees, duration));
    }

    @Override
    public String toString() {
        return "ActorPlayAnimationPack{" +
                "id=" + id +
                ", animationName='" + animationName + '\'' +
                ", directionInDegrees=" + directionInDegrees +
                ", duration=" + duration +
                '}';
    }
}
