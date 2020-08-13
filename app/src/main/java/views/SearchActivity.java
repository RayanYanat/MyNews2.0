package views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mBeginDate;
    EditText mEndDate;
    Button mButtonSearch;
    EditText mSearchText;
    LinearLayout mCheckboxContainer;

    public static String QUERY = "QUERY";
    public static String FILTER_QUERY = "FILTER_QUERY";
    public static String BEGIN_DATE = "BEGIN_DATE";
    public static String END_DATE = "END_DATE";

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_search_activity);
        mBeginDate = findViewById(R.id.search_begin_date);
        mEndDate = findViewById(R.id.search_end_date);
        mButtonSearch = findViewById(R.id.search_button);
        mSearchText = findViewById(R.id.search_editText);
        mCheckboxContainer = findViewById(R.id.checkbox_container);

        this.configureClickListener();
    }

    public void onClick(View v) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (v == mBeginDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mBeginDate.setText(sdf.format(new Date(year - 1900, monthOfYear, dayOfMonth)));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == mEndDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mEndDate.setText(sdf.format(new Date(year - 1900, monthOfYear, dayOfMonth)));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == mButtonSearch) {
            Log.e("TAG", "ArrayList " + retrieveSelectedCheckbox());

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String query = mSearchText.getText().toString();
            String beginDate = null;
            String endDate = null;
            try {
                Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(mBeginDate.getText().toString());
                Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(mEndDate.getText().toString());
                beginDate = mSimpleDateFormat.format(d1);
                endDate = mSimpleDateFormat.format(d2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.e("TAG", "ArrayList " + query);

            if (!(query.equals(""))) {
                Log.e("TAG", "ArrayList " + retrieveSelectedCheckbox());
                if (!(retrieveSelectedCheckbox().isEmpty())) {
                    Log.e("TAG", "ArrayList " + retrieveSelectedCheckbox());
                    Intent intent = new Intent(this, SearchActivityResult.class);
                    intent.putExtra(QUERY, query);
                    intent.putExtra(BEGIN_DATE, beginDate);
                    intent.putExtra(END_DATE, endDate);
                    intent.putStringArrayListExtra(FILTER_QUERY, (ArrayList<String>) retrieveSelectedCheckbox());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "You have to select at least one category", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Query can't be empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<String> retrieveSelectedCheckbox() {
        List<String> selectedCheckboxes = new ArrayList<>();
        for (int i = 0; i < mCheckboxContainer.getChildCount(); i++) {
            View view = mCheckboxContainer.getChildAt(i);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = ((ViewGroup) view);
                for (int y = 0; y < viewGroup.getChildCount(); y++) {
                    View viewChild = viewGroup.getChildAt(y);
                    if (viewChild instanceof CheckBox) {
                        CheckBox checkBox = ((CheckBox) viewChild);
                        if (checkBox.isChecked()) {
                            selectedCheckboxes.add(checkBox.getTag().toString());
                        }
                    }
                }
            }
        }
        return selectedCheckboxes;
    }

    private void configureClickListener() {
        mBeginDate.setOnClickListener(this);
        mEndDate.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
    }
}
