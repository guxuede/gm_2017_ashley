package com.guxuede.gm.net.client.pack.utils;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

import static io.netty.buffer.Unpooled.wrappedBuffer;

public class Constant {
    public static final Charset CHARSET = Charset.defaultCharset();

    public static final ByteBuf PACKAGE_DELIMITER = wrappedBuffer(new byte[]{(byte) '\t'});
    public static final ByteBuf STRING_DELIMITER = wrappedBuffer(new byte[]{(byte) '\b'});

}
