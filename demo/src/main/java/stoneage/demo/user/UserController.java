package stoneage.demo.user;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/create/{username}")
    public void saveNewUser(@RequestBody User user) { // map the user request body
        userService.addNewUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/user_details/{username}")
    public void getUserDetails(@PathVariable String username) { // map the user request body
        try {
            userService.getUser(username);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/update_user/{username}")
    public void updateUserDetails(@RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete_user/{username}")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);

    }

}
