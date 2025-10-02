package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.net.component.PlayerDataComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class ActorMovePack extends NetPack implements PlayerPack {

    private final int id;
    private final Vector2 acceleration = new Vector2();

    public ActorMovePack(int id, Vector2 acceleration, Vector2 position) {
        this.id = id;
        this.acceleration.set(acceleration);
    }

    public ActorMovePack(int id, Vector2 acceleration) {
        this.id = id;
        this.acceleration.set(acceleration);
    }

    public ActorMovePack(ByteBuf data) {
        id = data.readInt();
        acceleration.set(data.readFloat(), data.readFloat());
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        data.writeFloat(acceleration.x);
        data.writeFloat(acceleration.y);
    }

    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void action(Engine engine, Entity entity) {
        PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(entity);
        playerDataComponent.acceleration.set(acceleration);
    }
}
