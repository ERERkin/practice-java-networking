package kg.erkin.networking.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger LOG = Logger.getLogger(NettyServerHandler.class.getName());

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.log(Level.SEVERE, null, cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        String messageFromClient = new String(byteBuf.array(), StandardCharsets.UTF_8);
        LOG.info("Got message from " + channelHandlerContext.channel().remoteAddress() + " ---> " +
                messageFromClient);
        channelHandlerContext.writeAndFlush(byteBuf.copy());
    }
}
