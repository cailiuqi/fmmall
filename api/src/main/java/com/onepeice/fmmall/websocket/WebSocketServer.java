package com.onepeice.fmmall.websocket;

import javassist.tools.web.Webserver;
import lombok.var;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{oid}")
@Component
public class WebSocketServer {
    private static  Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();

    @OnOpen
    public void open(@PathParam("oid") String oid , Session session){
        sessionMap.put(oid,session);

    }

    @OnClose
    public void close(@PathParam("oid") String oid ){
        sessionMap.remove(oid);

    }

    public static void sendMsg(String oid,String msg) {
        try {
            sessionMap.get(oid).getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
