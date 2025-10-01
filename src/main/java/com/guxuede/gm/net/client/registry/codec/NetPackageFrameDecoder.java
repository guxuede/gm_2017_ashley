package com.guxuede.gm.net.client.registry.codec;

import com.guxuede.gm.net.client.registry.NetPack;
import com.guxuede.gm.net.client.registry.PacketRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by guxuede on 2017/5/21 .
 */
public class NetPackageFrameDecoder extends ByteToMessageDecoder {

    private int status = -1;
    private int packageType = 0;
    private int packageLength = 0;

    @Override
    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Object decoded = decode(ctx, in);
        if (decoded != null) {
            out.add(decoded);
        }
    }

    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if(status==-1){
            if(in.readableBytes() < 4){
                packageType = in.readInt();
                status = 0;
            }else {
                return null;
            }
        }
        if(status == 0){
            if(in.readableBytes() < 4){
                packageLength = in.readInt();
                status = 1;
            }else {
                return null;
            }
        }

        if(status == 1){
            if(in.readableBytes() < packageLength){
                NetPack packet = PacketRegistry.createPacket(packageType, in);
                status = -1;
                return packet;
            } else {
                return null;
            }
        }
        return null;
    }
}
