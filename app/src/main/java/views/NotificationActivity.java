package views;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynews.R;

public class NotificationActivity extends AppCompatActivity {

    Switch mSwitch;
    LinearLayout mCheckboxContainer;
    EditText mSearchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        mSwitch = findViewById(R.id.activity_notifications_switch);
        mCheckboxContainer=findViewById(R.id.checkbox_container);
        mSearchQuery=findViewById(R.id.search_editText);

    }
}
