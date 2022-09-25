package stoneage.demo.user;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
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
    public User getUser(String name)
            throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore();// get firestore instance
        DocumentReference docRef = dbFireStore.collection("user").document(name);
        ApiFuture<DocumentSnapshot> future = docRef.get(); // asynchronously retrieve the document
        DocumentSnapshot document = future.get();// future.get() blocks on response
        User user = null;
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
            user = document.toObject(User.class);

            return user;
        } else {
            System.out.println("No such document!");
            return null;
        }

    }

    /**
     * Get All documents
     * 
     */
    public List<User> getAllUsers()
            throws InterruptedException, ExecutionException {
        List<User>users = null;
        Firestore dbFireStore = FirestoreClient.getFirestore();// get firestore instance
        ApiFuture<QuerySnapshot> future = dbFireStore.collection("user").get();  // asynchronously retrieve all documents
        List<QueryDocumentSnapshot> documents = future.get().getDocuments(); // future.get() blocks on response
        
        User user = null;
        for (QueryDocumentSnapshot document : documents) {
            System.out.println(document.getId() + " => " + document.toObject(User.class));
            user = document.toObject(User.class);
            users.add(user);
        }
        return users;
    }
}
