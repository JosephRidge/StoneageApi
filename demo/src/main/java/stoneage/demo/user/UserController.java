package stoneage.demo.user;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/create/{username}")
    public ResponseEntity<Object> saveNewUser(@RequestBody User user) { // map the user request body
        userService.addNewUser(user);
        try {
            return UserResponseHandler.generateResponse(
                    HttpStatus.OK, true, "Success", "User Successfully added");

        } catch (Exception e) {
            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
        }
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<Object> createNewUser(@RequestBody UserProfile userProfile) { // map the user request body
        HashMap<String, Object> response = userService.createUserProfile(userProfile);
        try {
            if (response.get("status") != HttpStatus.OK || response.get("status") == "400 BAD_REQUEST") {
                return UserResponseHandler.generateResponse(
                        HttpStatus.BAD_REQUEST, false, "Failed", response.get("message"));

            } else {

                // Add user once authorized add user to table
                User user = new User();
                user.setUsername(userProfile.getUsername());
                user.setPassword(userProfile.getPassword());
                user.setPassword(userProfile.getPassword());
                user.setCreatedDate(userProfile.getCreatedAt());
                userService.addNewUser(user);

                return UserResponseHandler.generateResponse(
                        HttpStatus.OK, true, "Success", response.get("message"));
            }
        } catch (Exception e) {

            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            return UserResponseHandler.generateResponse(
                    HttpStatus.OK, true, "Success", userService.getAllUsers());

        } catch (InterruptedException e) {
            e.printStackTrace();
            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());

            // return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
            // return null;
        }
    }

    @GetMapping("/user_details/{username}")
    public ResponseEntity<Object> getUserDetails(@PathVariable String username) { // map the user request body
        try {
            HashMap<String, Object> response = userService.getUser(username);
            if (response.get("status") != HttpStatus.OK || response.get("status") == "400 BAD_REQUEST") {
                return UserResponseHandler.generateResponse(
                        HttpStatus.BAD_REQUEST, false, "Failed", response.get("message"));

            }else{
            return UserResponseHandler.generateResponse(
                    HttpStatus.OK, true, "Success", response.get("message"));

            }
        } catch (InterruptedException | ExecutionException e) {
            return UserResponseHandler.generateResponse(
                HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
        }

    }

    @PutMapping("/update_user/{username}")
    public ResponseEntity<Object> updateUserDetails(@RequestBody User user) throws InterruptedException {
        try {
            userService.updateUser(user);
            return UserResponseHandler.generateResponse(
                    HttpStatus.OK, true, "Success", "user Updated !");
        } catch (InterruptedException e) {
            // throw new InterruptedException();
            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
        }
    }

    @DeleteMapping("/delete_user/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        try {
            return UserResponseHandler.generateResponse(
                    HttpStatus.OK, true, "Success", "user profile removed !");

        } catch (Exception e) {
            return UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
        }

    }

}
