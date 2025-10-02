package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.component.NetClientComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class PlayerMovePack extends NetPack implements PlayerPack {

    private final int id;
    private final Vector2 acceleration = new Vector2();

    public PlayerMovePack(int id, Vector2 acceleration, Vector2 position) {
        this.id = id;
        this.acceleration.set(acceleration);
    }

    public PlayerMovePack(int id, Vector2 acceleration) {
        this.id = id;
        this.acceleration.set(acceleration);
    }

    public PlayerMovePack(ByteBuf data) {
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
    public void action(Entity entity) {
        NetClientComponent netClientComponent = Mappers.netPackCM.get(entity);
        netClientComponent.acceleration.set(acceleration);
    }
}
