package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.gdx.component.NetClientComponent;
import com.guxuede.gm.gdx.entityEdit.E;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import io.netty.buffer.ByteBuf;


public class PlayerLandingPack extends NetPack  {
    private int id, x, y;
    private String character;

    public PlayerLandingPack(ByteBuf data) {
        super(data);
        this.id = data.readInt();
        this.character = PackageUtils.readString(data);
        this.x = data.readInt();
        this.y = data.readInt();
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(this.id);
        PackageUtils.writeString(character, data);
        data.writeInt(this.x);
        data.writeInt(this.y);
    }

    @Override
    public void action(Entity entity) {
        E.create().with(NetClientComponent.class, e -> {
                    e.setCharacter(character);
                    e.setId(id);
                    e.position.set(x, y);
                })
                .actorState()
                .actorAnimation(character).asActor().pos(x, y)
                .actions().bounds(48, 48).blood(100, 65).sensor().buildToWorld();
    }


}
