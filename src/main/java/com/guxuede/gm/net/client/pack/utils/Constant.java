package com.guxuede.gm.net.client.pack.utils;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

import static io.netty.buffer.Unpooled.wrappedBuffer;

public class Constant {
    public static final Charset CHARSET = Charset.defaultCharset();

    //Choose a delimiter that will not appear in your actual message data. If the delimiter itself is part of the message, the frame will be broken incorrectly.
    public static final ByteBuf PACKAGE_DELIMITER = wrappedBuffer(new byte[]{'\t','\b','\f', 'g' , 'x', 'd'});
    public static final ByteBuf STRING_DELIMITER = wrappedBuffer(new byte[]{'\f','\b', '\t', 'g' , 'x', 'd'});
}
