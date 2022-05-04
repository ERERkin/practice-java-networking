package kg.erkin.networking.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClient {
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(new NioEventLoopGroup())
                .handler(new ChannelInitializer<SocketChannel>(){

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect(new InetSocketAddress(11111));
        Channel channel = future.sync().channel();
        channel.writeAndFlush("Test-test-test");

        Thread.sleep(1000L);

        channel.close().await();
    }
}
