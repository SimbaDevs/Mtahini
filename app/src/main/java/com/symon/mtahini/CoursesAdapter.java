package com.symon.mtahini;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private ArrayList<Unit> courses;
    FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    public CoursesAdapter(ArrayList<Unit> courses) {
        this.courses = courses;

        // get the current user
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        // fetch the courses from the database
        if (user != null) {
            fetchCourses();
        }
    }

    // fetch the courses from the database
    private void fetchCourses() {
        fStore = FirebaseFirestore.getInstance();
        fStore.collection("Students").document(user.getUid()).get().addOnCompleteListener(task -> {
            ArrayList<Unit> registeredUnits = (ArrayList<Unit>) task.getResult().get("course");
            if (task.isSuccessful()) {
                courses = registeredUnits;
                Log.d("CoursesAdapter", "fetchCourses: " + task.getResult().get("course"));
            } else {
                Log.e("CoursesAdapter", "fetchCourses: " + task.getException());
            }
        });
    }

    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        holder.course.setText(courses.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView course;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course_post_title);
        }
    }
}
