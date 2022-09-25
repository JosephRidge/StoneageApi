package stoneage.demo.user;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class UserService {

    // Also works as user details updater
    
    public String addNewUser(User user) {
        // get firestore instance 
        Firestore dbFireStore = FirestoreClient.getFirestore();
        
        //get firestore collectionA
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user").
        document(user.getUsername()).set(user);
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
}
