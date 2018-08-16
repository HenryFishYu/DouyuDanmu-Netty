package netty.Handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import utils.Singleton;

import java.util.Map;

/**
 * @Author: yuhl
 * @Date: 2018/8/16 14:15
 * @Description:
 */
public class DouyuDanmuHandler extends SimpleChannelInboundHandler<Map<String,Object>> {
    private String roomId;

    public DouyuDanmuHandler(String roomId) {
        this.roomId = roomId;
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Map<String,Object> map) throws Exception {
        Channel channel=channelHandlerContext.channel();
        if("loginres".equals(map.get("type"))){
            Singleton.INSTANCE.sendHeartBeatToKeepAlive(channel,45000);
            return;
        }
        if("pingreq".equals(map.get("type"))){
            channel.writeAndFlush("type@=joingroup/rid@="+roomId+"/gid@=-9999/");
            return;
        }
        System.out.println(map.toString());
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        incoming.writeAndFlush("type@=loginreq/roomid@="+roomId+"/");
    }
}
