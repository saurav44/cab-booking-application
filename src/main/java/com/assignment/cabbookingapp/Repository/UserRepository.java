package com.assignment.cabbookingapp.Repository;

import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> users = new HashMap<>();

    public User add_user(User user) {
        if(users.containsKey(user.getName())) {
            return null;
        }
        return users.put(user.getName(), user);
    }

    public User update_user(String username, String phone) {
        if(!users.containsKey(username)) {
            return null;
        }
        users.get(username).setPhone(phone);
        return users.get(username);
    }

    public User updateLocation(String username, Location location) {
        if(!users.containsKey(username)) {
            return null;
        }
        users.get(username).setLocation(location);
        return users.get(username);
    }

    public User getUser(String username) {
        if(!users.containsKey(username))
            return null;

        return users.get(username);
    }
}
