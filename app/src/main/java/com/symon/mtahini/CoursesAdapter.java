package com.symon.mtahini;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.firestore.FirebaseFirestore;
import com.symon.mtahini.authentication.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    private ArrayList<HashMap<String, Integer>> courses;
    FirebaseFirestore fStore;


    public CoursesAdapter(ArrayList<HashMap<String, Integer>> courses) {
        this.courses = courses;

        fetchCourses();
    }

    // fetch the courses from the database
    public void fetchCourses() {
        fStore = FirebaseFirestore.getInstance();
        fStore.collection("course").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                    HashMap<String, Integer> course = new HashMap<>();
                    course.put("course", Integer.parseInt(task.getResult().getDocuments().get(i).get("course").toString()));
                    courses.add(course);
                    Log.d("Courses", "fetchCourses: " + course.get("course"));
                }
            }
            notifyDataSetChanged();
        });
    }

    @NonNull
    @Override
    public CoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student_home_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.ViewHolder holder, int position) {
        holder.course.setText(courses.get(position).get("course"));
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
