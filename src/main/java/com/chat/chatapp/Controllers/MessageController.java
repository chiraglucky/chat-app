package com.chat.chatapp.Controllers;

import com.chat.chatapp.Models.Message;
import com.chat.chatapp.Models.User;
import com.chat.chatapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.user.SimpSubscriptionMatcher;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class MessageController {

    @Autowired
    UserService userService;

    //to get the message and send to all other participants
    @MessageMapping("app/user-all") //client send message to this url
    @SendTo("/topic/{room}") //send message to only those client who subscribed to this url
    public Message sendToAll(@Payload User user,@PathVariable String room) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        User user=new User(123,"chirag",room);
        this.userService.addUser(user);
        return new Message();
    }

    @MessageMapping("/user")
    public void addUsers(@Payload User user){
        System.out.println("hello ji kese ho");
        if(user.getName()!=null && user.getRoom()!=null) {
            this.userService.addUser(user);
        }
    }

    @GetMapping("/user/{room}")
    public List<User> getAllUser(@PathVariable String room){
        return this.userService.getUserList(room);
    }

    @DeleteMapping("/user/{name}")
    public void deleteUser(@PathVariable Integer id){
//        this.userService.removeUser(name);
        System.out.println("ID is "+id);
    }
}
