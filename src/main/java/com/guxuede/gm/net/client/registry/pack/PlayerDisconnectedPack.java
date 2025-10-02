package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class PlayerDisconnectedPack extends NetPack  implements PlayerPack {
    private int id;

    public PlayerDisconnectedPack(int id) {
        this.id = id;
    }

    public PlayerDisconnectedPack(ByteBuf data) {
        super(data);
        this.id = data.readInt();
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(this.id);
    }

    @Override
    public void action(Engine engine, Entity entity) {
        engine.removeEntity(entity);
    }


    @Override
    public int getPlayerId() {
        return id;
    }
}
