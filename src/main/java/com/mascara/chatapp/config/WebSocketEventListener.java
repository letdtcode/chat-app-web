package com.mascara.chatapp.config;

import com.mascara.chatapp.model.ChatMessage;
import com.mascara.chatapp.model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 02/06/2024
 * Time      : 5:49 CH
 * Filename  : WebSocketEventListener
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("username");
        if (userName != null) {
            log.info("User disconnected: {}", userName);
            var chatMessage = ChatMessage.builder()
                    .messageType(MessageType.LEAVE)
                    .sender(userName)
                    .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
