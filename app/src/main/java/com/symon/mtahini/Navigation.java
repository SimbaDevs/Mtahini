package com.symon.mtahini;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Navigation {

    private final Context currentClass;
    private final Activity currentActivity;


    public Navigation(Context currentClass, Activity currentActivity) {
        this.currentClass = currentClass;
        this.currentActivity = currentActivity;
    }
    /**
     * reload - changes the activity to the home activity
     */

    public void moveToHomeActivity() {
        Intent homeActivity = new Intent(currentClass, home.class);
        homeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        currentActivity.startActivity(homeActivity);
    }

}
