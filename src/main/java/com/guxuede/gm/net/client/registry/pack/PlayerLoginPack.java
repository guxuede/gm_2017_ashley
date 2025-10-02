package com.guxuede.gm.net.client.registry.pack;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import io.netty.buffer.ByteBuf;

public class PlayerLoginPack extends NetPack {
    private String userName;
    private String password;

    public PlayerLoginPack(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public PlayerLoginPack() {

    }

    public PlayerLoginPack(ByteBuf data) {
        super(data);
        this.userName = PackageUtils.readString(data);
        this.password = PackageUtils.readString(data);
    }

    @Override
    public void write(ByteBuf data) {
        PackageUtils.writeString(this.userName, data);
        PackageUtils.writeString(this.password, data);
    }

    @Override
    public void action(Engine engine, Entity entity) {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
