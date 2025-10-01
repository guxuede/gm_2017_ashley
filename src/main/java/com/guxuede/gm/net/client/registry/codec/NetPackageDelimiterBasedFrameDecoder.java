package com.guxuede.gm.net.client.registry.codec;

import com.guxuede.gm.net.client.registry.PacketRegistry;
import io.netty.buffer.ByteBuf;

public class NetPackageDelimiterBasedFrameDecoder  extends DelimiterBasedFrameDecoder{


    public NetPackageDelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf delimiter) {
        super(maxFrameLength, delimiter);
    }

    public NetPackageDelimiterBasedFrameDecoder(int maxFrameLength, boolean stripDelimiter, ByteBuf delimiter) {
        super(maxFrameLength, stripDelimiter, delimiter);
    }

    public NetPackageDelimiterBasedFrameDecoder(int maxFrameLength, ByteBuf... delimiters) {
        super(maxFrameLength, delimiters);
    }

    public NetPackageDelimiterBasedFrameDecoder(int maxFrameLength, boolean stripDelimiter, ByteBuf... delimiters) {
        super(maxFrameLength, stripDelimiter, delimiters);
    }


    @Override
    protected Object parse(ByteBuf buffer) {
        int packageType = buffer.readInt();
        return PacketRegistry.createPacket(packageType, buffer);
    }
}
