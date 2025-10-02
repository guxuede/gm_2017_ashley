package com.guxuede.gm.net.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.guxuede.gm.net.client.pack.utils.PackQueue;
import com.guxuede.gm.net.client.registry.NetPack;

import static com.guxuede.gm.gdx.component.ActorStateComponent.DOWN;


public class PlayerDataComponent implements Component, Pool.Poolable{
    public String userName;
    public int id;
    public String character;
    public Vector2 acceleration = new Vector2();
    public Vector2 position = new Vector2();
    public int direction= DOWN;

    public PackQueue<NetPack> inboundNetPacks = new PackQueue<>();
    public PackQueue<NetPack> outboundNetPacks = new PackQueue<>();


    public long lastTimePositionReported =0;

    public void inBoundPack(NetPack netPack){
        inboundNetPacks.add(netPack);
    }

    public void outBoundPack(NetPack netPack){
        outboundNetPacks.add(netPack);
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void reset() {

    }
}
