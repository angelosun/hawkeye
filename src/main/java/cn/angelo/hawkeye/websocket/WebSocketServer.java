package cn.angelo.hawkeye.websocket;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@ServerEndpoint("/statisticsServer")
@Component
public class WebSocketServer {


    public static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketMap.put(session.getId(), this);
        try {
            sendMessage("web socket connected");
        } catch (IOException e) {
            LOG.error("");
        } catch (Exception e1) {
            LOG.error("");
        }
    }


    @OnClose
    public void onClose(Session session) {
        webSocketMap.remove(session.getId());

    }

    /**
     * 实现服务器主动推送
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * @param statisticsData
     * @throws IOException
     */
    public static void pushDataToWeb(String statisticsData) throws IOException {
        LOG.info("发送消息到报文:{}", statisticsData);

        for (Iterator<WebSocketServer> iterator = webSocketMap.values().iterator(); iterator.hasNext();) {
            WebSocketServer webSocketServer = iterator.next();
            webSocketServer.session.getAsyncRemote().sendText(statisticsData);
        }
    }

}