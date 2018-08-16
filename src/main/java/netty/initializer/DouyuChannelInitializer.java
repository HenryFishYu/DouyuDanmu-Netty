package netty.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import netty.Decoder.DouyuDanmuDecoder;
import netty.Encoder.DouyuDanmuEncoder;
import netty.Handler.DouyuDanmuHandler;

import java.nio.ByteOrder;

/**
 * @Author: yuhl
 * @Date: 2018/8/16 13:53
 * @Description:
 */
public class DouyuChannelInitializer extends ChannelInitializer<SocketChannel> {
    private String roomId;

    public DouyuChannelInitializer(String roomId) {
        this.roomId = roomId;
    }

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN,20480,0,4,0,0,true));
        channelPipeline.addLast(new DouyuDanmuEncoder());
        channelPipeline.addLast(new DouyuDanmuDecoder());
        channelPipeline.addLast(new DouyuDanmuHandler(roomId));
    }
}
