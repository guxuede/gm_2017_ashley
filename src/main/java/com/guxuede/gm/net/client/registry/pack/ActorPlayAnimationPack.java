package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.actions.animation.AnimationAction;
import com.guxuede.gm.gdx.component.ActionsComponent;
import com.guxuede.gm.gdx.component.ActorAnimationComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class ActorPlayAnimationPack extends NetPack implements PlayerPack {
    private int id;
    public String animationName;
    public int duration;


    public ActorPlayAnimationPack(int id, String animationName, int duration) {
        this.id = id;
        this.animationName = animationName;
        this.duration = duration;
    }

    public ActorPlayAnimationPack(ByteBuf data) {
        id = data.readInt();
        animationName = PackageUtils.readString(data);
        duration = data.readInt();
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        PackageUtils.writeString(animationName,data);
        data.writeInt(duration);
    }


    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void action(Engine engine, Entity entity) {
//        ActorAnimationComponent animationComponent = Mappers.animationHolderCM.get(entity);
//        animationComponent.setCurrentAnimation(animationName, duration);

        ActionsComponent actionsComponent = Mappers.actionCM.get(entity);
        actionsComponent.addAction(entity, new AnimationAction(animationName, 3f));
    }

}
