package org.example.springdemo.controller;

import org.example.springdemo.model.Users;
import org.example.springdemo.repository.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/db")
public class DBController {

    private final UsersRepository usersRepository;

    public DBController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Users> getUsers() {
        Iterable<Users> user = usersRepository.findAll();
        return user;
    }

    @PostMapping("/add")
    @ResponseBody
    public Users addUsers(@RequestParam String email,
                        @RequestParam String login,
                        @RequestParam String name,
                        @RequestParam String password) {
        return usersRepository.save(new Users(email, login, name, password));
    }

    @PostMapping("/edit")
    @ResponseBody
    public boolean editUsers(@RequestParam long id,
                           @RequestParam String name){
        return usersRepository.updateById(id, name);
    }
}
