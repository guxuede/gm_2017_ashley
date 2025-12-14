package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.guxuede.gm.net.component.PlayerDataComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class ActorPositionPack extends NetPack implements PlayerPack {

    private int id;
    private final float directionInDegrees;
    public Vector2 acceleration = new Vector2();
    private final Vector2 position = new Vector2();

    public ActorPositionPack(int id, float directionInDegrees, Vector2 acceleration, Vector2 position) {
        this.id = id;
        this.directionInDegrees = directionInDegrees;
        this.acceleration.set(acceleration);
        this.position.set(position);
    }

    public ActorPositionPack(ByteBuf data) {
        id = data.readInt();
        directionInDegrees = data.readFloat();
        acceleration.set(data.readFloat(), data.readFloat());
        position.set(data.readFloat(), data.readFloat());
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        data.writeFloat(directionInDegrees);

        data.writeFloat(acceleration.x);
        data.writeFloat(acceleration.y);

        data.writeFloat(position.x);
        data.writeFloat(position.y);
    }


    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public void action(Engine engine, Entity entity) {
        PlayerDataComponent playerDataComponent = Mappers.netPackCM.get(entity);
        playerDataComponent.directionInDegrees = directionInDegrees;
        playerDataComponent.position.set(position);
        playerDataComponent.acceleration.set(acceleration);
    }

    @Override
    public String toString() {
        return "ActorPositionPack{" +
                "id=" + id +
                ", directionInDegrees=" + directionInDegrees +
                ", acceleration=" + acceleration +
                ", position=" + position +
                '}';
    }
}
