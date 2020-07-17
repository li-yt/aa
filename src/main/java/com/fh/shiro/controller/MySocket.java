package com.fh.shiro.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;



/**
 * 检测实时在线人数
 *
 * @Author MiChong
 * @Email: 1564666023@qq.com
 * @Create 2018-03-06 16:43
 * @Version: V1.0
 */

@Component
@ServerEndpoint("/websocket")  //该注解表示该类被声明为一个webSocket终端
public class MySocket {
    /**
     * 初始在线人数
     */
    private static int online_num = 0;
    /**
     * 线程安全的socket集合
     */
    private static CopyOnWriteArraySet<MySocket> webSocketSet = new CopyOnWriteArraySet<MySocket>();
    /**
     * 会话
     */
    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        this.session.getAsyncRemote().sendText("有链接加入，当前人数为:"+getOnline_num());

    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        for(MySocket item:webSocketSet){
            this.session.getAsyncRemote().sendText(message);

        }
    }
    public synchronized int getOnline_num(){
        return MySocket.online_num;
    }
    public synchronized int subOnlineCount(){
        return MySocket.online_num--;
    }
    public synchronized int addOnlineCount(){
        return MySocket.online_num++;
    }

}
