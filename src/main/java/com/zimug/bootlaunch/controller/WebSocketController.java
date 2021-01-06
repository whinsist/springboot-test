package com.zimug.bootlaunch.controller;

import com.zimug.bootlaunch.config.exception.AjaxResponse;
import com.zimug.bootlaunch.config.exception.CustomException;
import com.zimug.bootlaunch.config.exception.CustomExceptionType;
import com.zimug.bootlaunch.utils.ws.ServerMsg;
import com.zimug.bootlaunch.utils.ws.ServerMsgPublisher;
import com.zimug.bootlaunch.utils.ws.ServerMsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/ws")
public class WebSocketController {  

//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;

    /** 
     * 群发消息内容 
     * @param message 消息内容
     */
    @RequestMapping(value="/sendAll", method=RequestMethod.GET)
    AjaxResponse sendAllMessage(@RequestParam String message){
        try {  
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"群发消息失败");
        }  
        return AjaxResponse.success();
    }  

    /** 
     * 指定会话ID发消息 
     * @param message 消息内容 
     * @param id 连接会话ID
     */
    @RequestMapping(value="/sendOne", method=RequestMethod.GET)
    AjaxResponse sendOneMessage(@RequestParam String message,@RequestParam String id){
        try {  
            WebSocketServer.SendMessage(id,message);  
        } catch (IOException e) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"指定会话ID发消息失败");
        }

        //ServerMsgPublisher.sendMsgToUser("12333", new ServerMsg(ServerMsgType.VM, "111", "1111"));

        return AjaxResponse.success();
    }  
}  
