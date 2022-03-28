package in.exploreit.slc.fragments.Lists;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import in.exploreit.slc.data.models.Event;
import in.exploreit.slc.data.models.ListItem;
import in.exploreit.slc.data.models.ListResultCallback;
import in.exploreit.slc.data.models.NewProject;
import in.exploreit.slc.data.models.OldProject;
import in.exploreit.slc.utils.Constants;

public class ListsViewModel extends ViewModel {
    final private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    List<ListItem> getListItems() {
        List<ListItem> list = new ArrayList<>();
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        list.add(new Event("Title", "Desc", "https://i.imgur.com/KoRBWXM.jpeg", 500));
        return list;
    }

    void getAllEvents(ListResultCallback callback) {
        firestore.collection("events").get()
                .addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<ListItem> list = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Event event = document.toObject(Event.class);
                    list.add(event);
                }
                callback.responseReturned(list);
            } else {
                Log.d(Constants.TAG, "Error getting documents: ", task.getException());
                callback.responseReturned(null);
            }
        });
    }

    void getAllOldProjects(ListResultCallback callback) {
        firestore.collection("old_projects").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<ListItem> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            OldProject oldProject = document.toObject(OldProject.class);
                            list.add(oldProject);
                        }
                        callback.responseReturned(list);
                    } else {
                        Log.d(Constants.TAG, "Error getting documents: ", task.getException());
                        callback.responseReturned(null);
                    }
                });
    }

    void getAllNewProjects(ListResultCallback callback) {
        firestore.collection("new_projects").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<ListItem> list = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            NewProject newProject = document.toObject(NewProject.class);
                            list.add(newProject);
                        }
                        callback.responseReturned(list);
                    } else {
                        Log.d(Constants.TAG, "Error getting documents: ", task.getException());
                        callback.responseReturned(null);
                    }
                });
    }
}