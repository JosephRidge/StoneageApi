package stoneage.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/users") // sets url path to follow

public class UserController {

    // dependency injection
    @Autowired
    public UserService userService;

    /**
     * Handles CRUD Operations
     * GET | POST | DELETE | PUT - note that put the method attached to it is makred
     * with Transactional
     * 
     */
    @GetMapping // set the first GET verb
    public User getUser() {
        return new User();
    }

    @PostMapping
    public void saveNewUser(@RequestBody User user) { // map the user request body
        userService.addNewUser(user);
    }
}
