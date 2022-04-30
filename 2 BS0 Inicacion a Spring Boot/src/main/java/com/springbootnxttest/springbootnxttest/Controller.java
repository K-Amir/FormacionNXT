package com.springbootnxttest.springbootnxttest;

import com.springbootnxttest.springbootnxttest.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController()
public class Controller {

  @GetMapping("/user/{name}")
  public String getHelloWorld(@PathVariable("name") String name) {
    return "Hello " + name;
  }

  @PostMapping("/useradd")
  public ResponseEntity<User> addUser(@RequestBody User user) {
    user.setAge(user.getAge()+1);
   return ResponseEntity.ok(user);
  }
}
