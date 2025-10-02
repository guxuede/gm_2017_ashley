package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.net.component.PlayerDataComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.gdx.entityEdit.EntityEditor;
import com.guxuede.gm.gdx.system.StageSystem;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.samplegame.DesktopLauncher;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.StringUtils;


public class ActorLandingPack extends NetPack {
    private String userName;
    private int id;
    private float x, y;
    private String character;
    private int direction;

    public ActorLandingPack(ByteBuf data) {
        super(data);
        this.id = data.readInt();
        this.userName = PackageUtils.readString(data);
        this.character = PackageUtils.readString(data);
        this.x = data.readFloat();
        this.y = data.readFloat();
        this.direction = data.readInt();
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(this.id);
        PackageUtils.writeString(userName, data);
        PackageUtils.writeString(character, data);
        data.writeFloat(this.x);
        data.writeFloat(this.y);
        data.writeInt(this.direction);
    }

    @Override
    public void action(Engine engine, Entity entity) {
        if(StringUtils.equals(DesktopLauncher.currentUserName, userName)){
            Entity userEntity = buildActor(E.create()).sensor().buildToWorld();
            engine.getSystem(StageSystem.class).setViewActor(userEntity);
        }else{
            buildActor(E.create()).buildToWorld();
        }
    }

    private EntityEditor buildActor(E target){
        return target.with(PlayerDataComponent.class, e -> {
            e.userName = userName;
            e.setCharacter(character);
            e.setId(id);
            e.position.set(x, y);
        })
                .actorState(direction, false, 0,0,0,0)
                .actorAnimation(character).pos(x, y)
                .actions().bounds(48, 48).blood(100, 65);
    }

}
