package org.example.runner;

import org.example.entity.UserEntity;
import org.example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final UserService userService;

    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        System.out.println("Creating users");
        List<UserEntity> createdUsers = new ArrayList<>();
        UserEntity user1 = userService.createUser("Inna");
        UserEntity user2 = userService.createUser("Anna");
        createdUsers.add(user1);
        createdUsers.add(user2);
        System.out.println("Created users " + createdUsers);

        System.out.println("------------------------------------");

        System.out.println("All users");
        List<UserEntity> allUsers = userService.getAllUsers();
        System.out.println(allUsers);

        System.out.println("------------------------------------");

        System.out.println("Get user by ID");
        UserEntity user = allUsers.get(0);
        System.out.println(userService.findById(user.getId()));

        System.out.println("------------------------------------");

        System.out.println("Deleting user");
        userService.deleteUserById(user.getId());

        System.out.println("All users after deleting");
        List<UserEntity> allUsersAfterDeleting = userService.getAllUsers();
        allUsersAfterDeleting.forEach(System.out::println);

        System.out.println("------------------------------------");

        System.out.println("Updating user");
        UserEntity userForUpdate = allUsersAfterDeleting.get(0);
        userForUpdate.setUsername("Elena");
        UserEntity updatedUser = userService.updateUser(userForUpdate);
        System.out.println(updatedUser);

    }
}
