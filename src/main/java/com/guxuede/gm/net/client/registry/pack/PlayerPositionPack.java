package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.gdx.component.NetClientComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class PlayerPositionPack extends NetPack implements PlayerPack {

    private int id;
    private final int direction;
    private final Vector2 position = new Vector2();

    public PlayerPositionPack(int id, int direction, Vector2 position) {
        this.id = id;
        this.direction = direction;
        this.position.set(position);
    }

    public PlayerPositionPack(ByteBuf data) {
        id = data.readInt();
        direction = data.readInt();
        position.set(data.readFloat(), data.readFloat());
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        data.writeInt(direction);
        data.writeFloat(position.x);
        data.writeFloat(position.y);
    }


    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void action(Engine engine, Entity entity) {
        NetClientComponent netClientComponent = Mappers.netPackCM.get(entity);
        netClientComponent.position.set(position);
        netClientComponent.direction = direction;
    }

    @Override
    public String toString() {
        return "PlayerPositionPack{" +
                "id=" + id +
                ", direction=" + direction +
                ", position=" + position +
                '}';
    }
}
