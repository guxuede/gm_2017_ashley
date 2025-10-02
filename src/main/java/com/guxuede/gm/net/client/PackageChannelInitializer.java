/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.guxuede.gm.net.client;

import com.guxuede.gm.net.system.GlobalNetPackSystem;
import com.guxuede.gm.net.client.registry.codec.NetPackageDelimiterBasedFrameDecoder;
import com.guxuede.gm.net.client.registry.codec.NetPackageDelimiterBasedFrameEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import static com.guxuede.gm.net.client.pack.utils.Constant.PACKAGE_DELIMITER;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class PackageChannelInitializer extends ChannelInitializer<SocketChannel> {

    public GlobalNetPackSystem clientManager;

    public PackageChannelInitializer(GlobalNetPackSystem clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new NetPackageDelimiterBasedFrameDecoder(1024, PACKAGE_DELIMITER));
        pipeline.addLast(new NetPackageDelimiterBasedFrameEncoder());
        pipeline.addLast(new PackageInboundHandler(clientManager));
    }
}