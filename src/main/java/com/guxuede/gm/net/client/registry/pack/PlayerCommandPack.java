package com.guxuede.gm.net.client.registry.pack;

import com.guxuede.gm.net.client.pack.utils.PackageUtils;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.buffer.ByteBuf;

public class PlayerCommandPack extends NetPack implements PlayerPack {

    private int id;
    private String command;

    public PlayerCommandPack(int id, String command) {
        this.id = id;
        this.command = command;
    }

    public PlayerCommandPack(ByteBuf data) {
        super(data);
        this.id = data.readInt();
        this.command = PackageUtils.readString(data);
    }

    @Override
    public void write(ByteBuf data) {
        data.writeInt(id);
        PackageUtils.writeString(command,data);
    }

    @Override
    public int getPlayerId() {
        return id;
    }
}
