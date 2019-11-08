package com.telran.springbootdemo2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController //@Controller
public class UserController { //30 requests/MIN

    private static List<User> users = new ArrayList<>();

    //localhost:9000/api/cars/1/...

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName() {
        return "This is some string";
    }

    @GetMapping("/user")
    public User getUser() {
        return User.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
    }

    @GetMapping("/user-with-params") //localhost:8080/user-with-params?firstName=<YOUR NAME>&lastName=<YOUR LAST NAME>
    public User getUserWithParams(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    //NEVER DO THIS. EVER!!!!!!
    @GetMapping("Yuser_Weeth_Path-Varible/{firstName}/{lastName}")
    public User getUserWithPathVariable(@PathVariable("firstName") String firstName,
                                        @PathVariable("lastName") String lastName) {

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setCreatedDate(LocalDate.now());
        user.setId(UUID.randomUUID().toString());
        users.add(user);
        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return users;
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
class User {

    private String id;
    private String firstName;
    private String lastName;
    private LocalDate createdDate;
}
