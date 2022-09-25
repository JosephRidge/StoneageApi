package stoneage.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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
    public String getUser(String username)
            throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore();// get firestore instance
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user")
                .document(username).set(username);// get firestore collection
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

    public void deleteUser(User user) {
        Firestore dbFireStore = FirestoreClient.getFirestore(); // get firestore instance

        ApiFuture<WriteResult> writeResult = dbFireStore.collection("user").document(user.getUsername()).delete();// asynchronously
                                                                                                                  // delete
                                                                                                                  // a
                                                                                                                  // document
        try {
            System.out.println("Update time : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}