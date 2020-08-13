package views;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynews.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import models.NotificationPreferences;

public class NotificationActivity extends AppCompatActivity {

    Switch mSwitch;
    LinearLayout mCheckboxContainer;
    EditText mSearchQuery;
    public static final String PREFS = "PREFS";
    public static final String NOTIFICATIONS_STATE = "NOTIFICATIONS_STATE";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        mSwitch = findViewById(R.id.activity_notifications_switch);
        mCheckboxContainer=findViewById(R.id.checkbox_container);
        mSearchQuery=findViewById(R.id.search_editText);


        this.configureSwitchChangeListener();

    }

    @Override
    protected void onPause(){
        super.onPause();
        saveNotificationsPreferences(mSearchQuery.getText().toString(), getSelectedCheckboxes(), mSwitch.isChecked());
        if (mSwitch.isChecked()){
            Log.e("Notifications", "onPause: True" );
            Toast.makeText(NotificationActivity.this, "Notifications preferences saved", Toast.LENGTH_SHORT).show();
        }else{
            Log.e("Notifications", "onPause: False" );
        }

    }


    private void saveNotificationsPreferences(String queryTerm, List<String> categoryList, boolean isEnabled){
        mPreferences = getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE);
        Gson gson = new Gson();
        NotificationPreferences notifPrefs = new NotificationPreferences(queryTerm,categoryList, isEnabled);
        String jsonNotifPrefs = gson.toJson(notifPrefs);
        Log.e("preferencesaved",jsonNotifPrefs);
        mPreferences.edit().putString(NOTIFICATIONS_STATE,jsonNotifPrefs).apply();
    }

    private List<String> getSelectedCheckboxes(){
        List<String> selectedCheckboxes = new ArrayList<>();
        for (int i = 0; i < mCheckboxContainer.getChildCount(); i++) {
            View view = mCheckboxContainer.getChildAt(i);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = ((ViewGroup)view);
                for (int y = 0; y < viewGroup.getChildCount(); y++){
                    View viewChild = viewGroup.getChildAt(y);
                    if (viewChild instanceof CheckBox){
                        CheckBox checkBox = ((CheckBox) viewChild);
                        if (checkBox.isChecked()){
                            selectedCheckboxes.add(checkBox.getTag().toString());
                        }
                    }
                }
            }
        }
        return selectedCheckboxes;
    }

    private void configureSwitchChangeListener(){
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!(mSearchQuery.getText().toString().equals(""))){
                        List<String> selectedCheckboxes = getSelectedCheckboxes();
                        if (selectedCheckboxes.isEmpty()){
                            Toast.makeText(NotificationActivity.this, "You have to select at least one category", Toast.LENGTH_SHORT).show();
                            buttonView.setChecked(false);
                        }
                    }else{
                        Toast.makeText(NotificationActivity.this, "Query term can't be empty to enable notifications", Toast.LENGTH_SHORT).show();
                        buttonView.setChecked(false);
                    }
                }
            }
        });
    }


}
