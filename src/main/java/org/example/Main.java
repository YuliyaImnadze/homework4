package org.example;

import org.example.config.AppConfig;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var userService = context.getBean(UserService.class);

        System.out.println("Creating users");
        List<User> createdUsers = new ArrayList<>();
        User user1 = userService.createUser("Inna");
        User user2 = userService.createUser("Anna");
        createdUsers.add(user1);
        createdUsers.add(user2);
        System.out.println("Created users " + createdUsers);

        System.out.println("------------------------------------");

        System.out.println("All users");
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);

        System.out.println("------------------------------------");

        System.out.println("Get user by ID");
        User user = allUsers.get(0);
        System.out.println(userService.findById(user.getId()));

        System.out.println("------------------------------------");

        System.out.println("Deleting user");
        userService.deleteUserById(user.getId());

        System.out.println("All users after deleting");
        List<User> allUsersAfterDeleting = userService.getAllUsers();
        allUsersAfterDeleting.forEach(System.out::println);

        System.out.println("------------------------------------");

        System.out.println("Updating user");
        User userForUpdate = allUsersAfterDeleting.get(0);
        userForUpdate.setUsername("Elena");
        User updatedUser = userService.updateUser(userForUpdate);
        System.out.println(updatedUser);

        context.close();
    }
}
