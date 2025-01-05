package models;

import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserMain {

    public static void main(String args[])
    {
        List<User> users = new ArrayList<>();

        users.add(new User("aman","abcd"));
        users.add(new User("kumar","defg"));
        users.add(new User("raja","high"));
        users.add(new User("userName","1234"));

        for(User user: users)
        {
            System.out.println("UserName :" +user.getUsername());
            System.out.println("Password: " + user.getPassword());
        }


    }



}

