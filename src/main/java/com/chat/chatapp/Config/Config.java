package com.chat.chatapp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Collections;

//TO connect with server :- /websocket-chat
//To send message :-  /app/user-all
//                     {message body}
//To subscribe so that we can receive messages :- /topic/user

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         registry.enableSimpleBroker("/topic"); //subscribe
         registry.setApplicationDestinationPrefixes("/app"); //sending message
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //before sending/receiving message we have to create the connectivity using this url
         registry.addEndpoint("/websocket-chat")
                 .setAllowedOrigins("http://localhost:3001","http://localhost:3000")
                 .withSockJS(); //to connect server
    }
}
