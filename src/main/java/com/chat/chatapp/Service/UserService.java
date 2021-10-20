package com.chat.chatapp.Service;

import com.chat.chatapp.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static int count=0;
    private List<User> users=new ArrayList<>();

    public void addUser(User user){
        user.setId(++count);
        users.add(user);
        System.out.println(users);
    }

    public User getUser(int id){
        for (User user:users) {
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public List<User> getUserList(String room){
        List<User> userList=new ArrayList<>();
        for (User user:users) {
            if(user.getRoom().equals(room)){
                userList.add(user);
            }
        }
        return userList;
    }

    public User removeUser(int id){
        User user=getUser(id);
        if(user!=null){
            users.remove(user);
        }
        return user;
    }
}
