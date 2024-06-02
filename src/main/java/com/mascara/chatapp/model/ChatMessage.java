package com.mascara.chatapp.model;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 02/06/2024
 * Time      : 5:57 CH
 * Filename  : ChatMessage
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType messageType;
}
