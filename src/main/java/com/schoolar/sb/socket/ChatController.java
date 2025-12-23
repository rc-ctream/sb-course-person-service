package com.schoolar.sb.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class ChatController {

    @MessageMapping( "/greeting" )
    @SendTo( "/topic/greetings" )
    public ChatMessage greeting( ChatMessage message ) {
        log.info( "Received message: {}", message );
        return new ChatMessage( "SERVER", "Hi from server" );
    }
}
