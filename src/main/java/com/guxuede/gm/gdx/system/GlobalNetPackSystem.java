package com.guxuede.gm.gdx.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import com.guxuede.gm.gdx.component.NetClientComponent;
import com.guxuede.gm.gdx.entityEdit.Mappers;
import com.guxuede.gm.net.client.PackageChannelInitializer;
import com.guxuede.gm.net.client.pack.utils.PackQueue;
import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PacketRegistry;
import com.guxuede.gm.net.client.registry.PlayerPack;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class GlobalNetPackSystem extends IteratingSystem {
    private static final Family netIdfamily = Family.all(NetClientComponent.class).get();

    public static final String HOST = System.getProperty("host", "127.0.0.1");
    public static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));

    private final Engine engine;
    private final Channel channel;
    private final PackQueue<NetPack> inboundNetPacks = new PackQueue<>();

    public GlobalNetPackSystem(Engine engine) {
        super(netIdfamily);
        this.engine = engine;
        PacketRegistry.registerCore();
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).handler(new PackageChannelInitializer(this));
        try {
            channel = b.connect(HOST, PORT).channel();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void inboundNetPack(NetPack msg){
        inboundNetPacks.add(msg);
    }

    public synchronized void outboundNetPack(NetPack msg){
        channel.writeAndFlush(msg);
    }

    @Override
    public synchronized void update(float deltaTime) {
        //process inbound message
        inboundNetPacks.consumerAll(this::processMessage);

        //process entities package
        super.update(deltaTime);
        channel.flush();
    }


    @Override
    protected void processEntity(Entity entity, float v) {
        NetClientComponent netClientComponent = Mappers.netPackCM.get(entity);
        netClientComponent.outboundNetPacks.consumerAll(p-> channel.write(p));
    }


    private void processMessage(NetPack msg){
        if(msg instanceof PlayerPack){
            PlayerPack playerPack = (PlayerPack) msg;
            NetClientComponent netClientComponent = getNetClientComponent(playerPack.getPlayerId());
            if(netClientComponent!=null){
                netClientComponent.inBoundPack(msg);
            }
        }else {
            try {
                msg.action(null);
            }catch (RuntimeException e){
                e.printStackTrace();
            }
        }
    }

    public NetClientComponent getNetClientComponent(long id) {
        for (Entity e : engine.getEntitiesFor(netIdfamily)) {
            NetClientComponent netClientComponent = Mappers.netPackCM.get(e);
            if(id == netClientComponent.getId()){
                return netClientComponent;
            }
        }
        return null;
    }
//
//    public Entity getEntity(long id) {
//        for (Entity e : engine.getEntitiesFor(netIdfamily)) {
//            if(id == e.getComponent(NetClientComponent.class).getId()){
//                return e;
//            }
//        }
//        return null;
//    }
}
