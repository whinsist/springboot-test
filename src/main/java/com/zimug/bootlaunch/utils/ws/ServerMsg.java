package com.zimug.bootlaunch.utils.ws;

import com.zimug.bootlaunch.utils.UuidUtil;
import lombok.Data;


/**
 * The type ServerMsg.
 *
 * Created on 2018/12/11
 *
 * @author ChaoHong.Mao
 */
@Data
public class ServerMsg {
    private ServerMsgType msgType;
    private String refId;
    private Object msgContent;
    private Boolean optResult;
    private String msgId;


    public ServerMsg(ServerMsgType msgType, String refId, Object msgContent, Boolean optResult) {
        this.msgType = msgType;
        this.refId = refId;
        this.msgContent = msgContent;
        this.optResult = optResult;
        this.msgId = UuidUtil.getShortUuid();
    }

    /**
     * Instantiates a new Server msg.
     *
     * @param msgType the msg type
     * @param refId the ref id
     * @param msgContent the msg content
     */
    public ServerMsg(ServerMsgType msgType, String refId, Object msgContent) {
        this.msgType = msgType;
        this.refId = refId;
        this.msgContent = msgContent;
        this.optResult = true;
        this.msgId = UuidUtil.getShortUuid();
    }

    /**
     * Create server msg.
     *
     * @param msgType the msg type
     * @param refId the ref id
     * @param msgContent the msg content
     *
     * @return the server msg
     */
    public static ServerMsg create(ServerMsgType msgType, String refId, Object msgContent) {
        return new ServerMsg(msgType, refId, msgContent);
    }

    /**
     * Create server msg.
     *
     * @param msgType the msg type
     * @param refId the ref id
     * @param msgContent the msg content
     *
     * @return the server msg
     */
    public static ServerMsg create(ServerMsgType msgType, String refId, Object msgContent, Boolean optResult) {
        return new ServerMsg(msgType, refId, msgContent, optResult);
    }



}
