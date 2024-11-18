package com.venux.wx.controller;

import com.venux.wx.handler.WxChatMsgFactory;
import com.venux.wx.handler.WxChatMsgHandler;
import com.venux.wx.utils.MessageUtil;
import com.venux.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.*;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@Slf4j
public class CallBackController {

    // 定义微信公众号的令牌
    private static final String token = "143099842ba7564f";

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

    // 测试方法，返回"hello world"
    @RequestMapping("/test")
    public String test() {
        return "hello world";
    }

    /**
     * 回调消息校验
     */
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        //
        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        // 根据传入的参数和令牌生成 SHA1 签名
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        // 如果生成的签名与传入的签名一致，返回 echostr，完成验证
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }

    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        // 解析微信消息的 XML 内容为 Map
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        // 获取消息类型和事件类型
        String msgType = messageMap.get("MsgType");
        String event = Optional.ofNullable(messageMap.get("Event")).orElse("");
        log.info("msgType:{},event:{}", msgType, event);

        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if (!StringUtils.isEmpty(event)) {
            sb.append(".");
            sb.append(event);
        }
        // 根据消息类型和事件类型生成消息类型键
        String msgTypeKey = sb.toString();
        // 根据消息类型键从工厂获取对应的消息处理器
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        if (Objects.isNull(wxChatMsgHandler)) {
            return "unknown";
        }
        // 使用消息处理器处理消息并返回处理结果
        String replyContent = wxChatMsgHandler.dealMsg(messageMap);
        log.info("replyContent:{}", replyContent);
        return replyContent;
    }
}