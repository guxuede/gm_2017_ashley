package com.guxuede.gm.net.client.pack.utils;

import io.netty.buffer.ByteBuf;

import static com.guxuede.gm.net.client.pack.utils.Constant.CHARSET;
import static com.guxuede.gm.net.client.pack.utils.Constant.STRING_DELIMITER;


public class PackageUtils {

    public static void writeString(String s, ByteBuf data){
        data.writeCharSequence(s, CHARSET);
        data.writeBytes(STRING_DELIMITER,  0 , STRING_DELIMITER.capacity());
    }

    public static String readString(ByteBuf data){
        int stringLength = indexOf(data, STRING_DELIMITER);
        if(stringLength > -1){
            String string = data.slice(data.readerIndex(), stringLength).toString(CHARSET);
            data.skipBytes(stringLength + STRING_DELIMITER.capacity());
            return string;
        }
        return null;
    }


    private static int indexOf(ByteBuf haystack, ByteBuf needle) {
        for (int i = haystack.readerIndex(); i < haystack.writerIndex(); i ++) {
            int haystackIndex = i;
            int needleIndex;
            for (needleIndex = 0; needleIndex < needle.capacity(); needleIndex ++) {
                if (haystack.getByte(haystackIndex) != needle.getByte(needleIndex)) {
                    break;
                } else {
                    haystackIndex ++;
                    if (haystackIndex == haystack.writerIndex() &&
                            needleIndex != needle.capacity() - 1) {
                        return -1;
                    }
                }
            }

            if (needleIndex == needle.capacity()) {
                // Found the needle from the haystack!
                return i - haystack.readerIndex();
            }
        }
        return -1;
    }
}
