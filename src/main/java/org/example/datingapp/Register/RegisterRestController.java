package org.example.datingapp.Register;

import org.example.datingapp.model.User;
import org.example.datingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "email") String email) {

        User newUser = new User(username, password, email);


        User registeredUser = userService.register(newUser);

        return "Registration Successful for user: " + registeredUser.getUsername();
    }
}