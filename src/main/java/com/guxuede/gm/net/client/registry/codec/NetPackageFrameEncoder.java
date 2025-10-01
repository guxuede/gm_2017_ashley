package com.guxuede.gm.net.client.registry.codec;

import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by guxuede on 2017/5/21 .
 */
public class NetPackageFrameEncoder extends MessageToByteEncoder<NetPack> {


    @Override
    protected void encode(ChannelHandlerContext ctx, NetPack msg, ByteBuf out) throws Exception {
        out.writeInt(PacketRegistry.getPacketID(msg.getClass()));
        msg.write(out);
    }
}
