package com.guxuede.gm.net.client.registry.codec;

import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import static com.guxuede.gm.net.client.pack.utils.Constant.PACKAGE_DELIMITER;

public class NetPackageDelimiterBasedFrameEncoder extends MessageToByteEncoder<NetPack> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NetPack msg, ByteBuf out) throws Exception {
        out.writeInt(PacketRegistry.getPacketID(msg.getClass()));
        msg.write(out);
        out.writeBytes(PACKAGE_DELIMITER, 0 , PACKAGE_DELIMITER.capacity());
    }
}
