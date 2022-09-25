package stoneage.demo.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

    // Also works as user details update
    public String addNewUser(User user) {
        Firestore dbFireStore = FirestoreClient.getFirestore(); // get firestore instance
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user").document(user.getUsername())
                .set(user);// get firestore collectionA
        try {
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    // get praticular user
    public HashMap<String, Object> getUser(String username) throws InterruptedException, ExecutionException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        User user = null;

        Firestore dbFireStore = FirestoreClient.getFirestore();// get firestore instance
        DocumentReference documentReference = dbFireStore.collection("user").document(username);
        ApiFuture<DocumentSnapshot> collectionApiFuture = documentReference.get();// get firestore collection
        DocumentSnapshot documentSnapshot = collectionApiFuture.get();
        if (documentSnapshot.exists()) {
            user = documentSnapshot.toObject(User.class);
            map.put("status", HttpStatus.OK);
            map.put("message", user);
            return map;
        } else {
            map.put("status", HttpStatus.NOT_FOUND);
            map.put("message", "User Does not exist");
            return map;
        } 

    }

    /**
     * Get All documents
     * 
     */
    public List<User> getAllUsers()
            throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore();// get firestore instance
        ApiFuture<QuerySnapshot> future = dbFireStore.collection("user").get(); // asynchronously retrieve all documents
        List<QueryDocumentSnapshot> documents = future.get().getDocuments(); // future.get() blocks on response

        List<User> users = new ArrayList<User>();
        User user = null;
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(User.class));
            user = document.toObject(User.class);
            users.add(user);
        }
        return users;
    }

    public String updateUser(User user)
            throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore(); // get firestore instance
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user").document(user.getUsername())
                .set(user);// get firestore collection and set new collection details
        try {
            return collectionApiFuture.get().getUpdateTime().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void deleteUser(String username) {
        Firestore dbFireStore = FirestoreClient.getFirestore(); // get firestore instance
        // asynchronously delete a document
        ApiFuture<WriteResult> writeResult = dbFireStore.collection("user").document(username).delete();

        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> createUserProfile(UserProfile userProfile) {
        CreateRequest request = new CreateRequest()
                .setEmail(userProfile.getEmail())
                .setEmailVerified(userProfile.getEmailVerified())
                .setPassword(userProfile.getPassword())
                .setDisplayName(userProfile.getUsername()) 
                .setDisabled(userProfile.getAcntDisabled());

        UserRecord userRecord;
        try {
            userRecord = FirebaseAuth.getInstance().createUser(request);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("status", HttpStatus.OK);
            map.put("message", "Successfully created new user: " + userRecord.getUid());
            return map;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            UserResponseHandler.generateResponse(
                    HttpStatus.BAD_REQUEST, false, "Failed", e.getMessage());
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("status", HttpStatus.BAD_REQUEST);
            map.put("message", e.getMessage());
            return map;
        }
    }
}