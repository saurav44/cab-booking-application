package com.assignment.cabbookingapp.Repository;

import com.assignment.cabbookingapp.Exceptions.UserAlreadyExistsException;
import com.assignment.cabbookingapp.Exceptions.UserNotFoundException;
import com.assignment.cabbookingapp.Model.Location;
import com.assignment.cabbookingapp.Model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> users = new HashMap<>();

    public User add_user(User user) {
        if(users.containsKey(user.getName())) {
            throw new UserAlreadyExistsException();
        }
        return users.put(user.getName(), user);
    }

    public User update_user(String username, String phone) {
        if(!users.containsKey(username)) {
            throw new UserNotFoundException();
        }
        users.get(username).setPhone(phone);
        return users.get(username);
    }

    public User updateLocation(String username, Location location) {
        if(!users.containsKey(username)) {
            throw new UserNotFoundException();
        }
        users.get(username).setLocation(location);
        return users.get(username);
    }

    public User getUser(String username) {
        if(!users.containsKey(username))
            throw new UserNotFoundException();
        return users.get(username);
    }
}
