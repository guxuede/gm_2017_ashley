package com.guxuede.gm.net.client.registry;

import com.badlogic.ashley.core.Entity;
import io.netty.buffer.ByteBuf;

public abstract class NetPack {

    public NetPack(){

    }

    public NetPack(ByteBuf data) {
    }

    public void write(ByteBuf data){

    }

    public void action(Entity entity){

    }


}
