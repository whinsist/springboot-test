package com.zimug.bootlaunch.utils.ws;

import com.alibaba.fastjson.JSON;

import com.zimug.bootlaunch.utils.SpringContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;


/**
 * The type ServerMsgPublisher.
 *
 * Created on 2018/12/11
 *
 * @author ChaoHong.Mao
 */
@Slf4j
public class ServerMsgPublisher {

    public static final String WEBSOCKET_CHANNEL = "push-message-channel";
    private static final String BIZ_BROKER = "/topic/";
    private static final String USER_BROKER = "/msg";
    private final static String MSG_ID_KEY = "message-id";



    private static SimpMessagingTemplate MESSAGING_TEMPLATE;





    public static void sendMsgToUser(String userSid, ServerMsg serverMsg) {
        if (Objects.isNull(userSid)) {
            log.warn("UserID is null. Message is {}", serverMsg.toString());
            return;
        }
        sendMsg("/user/" + userSid + USER_BROKER, serverMsg);
    }

    public static void sendMsgToResourceType(String resType, String refId) {
        if (Objects.isNull(resType)) {
            log.warn("ResType is null. Message is {}", refId);
            return;
        }
        sendMsg(BIZ_BROKER + resType, refId);
    }

    public static void sendMsgToDetail(ServerMsgType resType, String refId, Object message) {
        if (Objects.isNull(resType)) {
            log.warn("ServerMsgType is null. Message is {}", JSON.toJSONString(message));
            return;
        }
        String destination = BIZ_BROKER + resType.getTypeFamily() + "/" + refId;
        sendMsg(destination, message);
    }

    public static void sendMsg(String destination, Object serverMsg) {

            sendDirect(destination, serverMsg);

    }

    private static void sendDirect(String destination, Object serverMsg) {
        initTemplate();
        Map<String, Object> map = new HashMap<>();
        map.put(MSG_ID_KEY, "111111111");
        MESSAGING_TEMPLATE.convertAndSend(destination, serverMsg, map);
    }



    /**
     * 延迟初始化
     */
    private static void initTemplate() {
        if (MESSAGING_TEMPLATE == null) {
            synchronized (ServerMsgPublisher.class) {
                if (MESSAGING_TEMPLATE == null) {
                    MESSAGING_TEMPLATE = SpringContextHolder.getBean(SimpMessagingTemplate.class);
                }
            }
        }
    }


}
