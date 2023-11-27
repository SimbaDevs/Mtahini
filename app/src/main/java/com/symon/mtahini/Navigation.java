package com.symon.mtahini;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

public class Navigation {

    private final Context currentClass;
    private final Activity currentActivity;


    public Navigation(Context currentClass, Activity currentActivity) {
        this.currentClass = currentClass;
        this.currentActivity = currentActivity;
    }
    /**
     * reload - changes the activity to the Home activity
     */
    public void moveToHomeActivity() {
        Intent homeActivity = new Intent(currentClass, MainActivity.class);
        homeActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        currentActivity.startActivity(homeActivity);
    }

    /**
     * moveTo - change to any activity
     * @param destination - the activity to move to
     */
    public void moveTo(Class destination){
        Intent newIntent = new Intent(currentActivity, destination);
        currentActivity.startActivity(newIntent);
    }

    /**
     * isFieldEmpty - check if an editText field is empty
     * @param editText - the field to be checked
     *  Return false or true
     * */
    public boolean isFieldEmpty(EditText editText){
        String inputText = String.valueOf(editText.getText());

        if (inputText.isEmpty()) {
            editText.requestFocus();
            editText.setError("This field cannot be empty");
            return true;
        }

        return false;
    }
}
