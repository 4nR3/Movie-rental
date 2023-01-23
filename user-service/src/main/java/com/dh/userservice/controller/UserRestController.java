package com.dh.userservice.controller;

import com.dh.userservice.model.User;
import com.dh.userservice.model.UserDTO;
import com.dh.userservice.service.UserService;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('GROUP_admin')")
    public List<UserDTO> getAllNonAdminUsers(){
        return userService.findAllNonAdminUsers();
    }

    @GetMapping("/admin/{username}")
    @PreAuthorize("hasAuthority('GROUP_admin')")
    public User getUserByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }

    @GetMapping("/findUsername/{username}")
    public User getByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);

    }

}
