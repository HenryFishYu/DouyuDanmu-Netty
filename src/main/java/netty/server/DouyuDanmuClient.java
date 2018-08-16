package netty.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import netty.initializer.DouyuChannelInitializer;

/**
 * @Author: yuhl
 * @Date: 2018/8/16 13:43
 * @Description:
 */
public class DouyuDanmuClient {
    private static final String host = "openbarrage.douyutv.com";
    private static final int port = 8601;
    private String roomId;

    public DouyuDanmuClient(String roomId) {
        this.roomId = roomId;
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new DouyuChannelInitializer(roomId));
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
           e.printStackTrace();
        } finally{
            group.shutdownGracefully().sync();
        }
    }
}
