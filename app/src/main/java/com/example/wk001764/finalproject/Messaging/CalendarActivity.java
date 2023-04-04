package com.example.wk001764.finalproject.Messaging;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.wk001764.finalproject.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity {

    private Button mSave;
    private EditText mTitle, mDescription, mLocation;
    private CalendarView mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mSave = (Button) findViewById(R.id.save);
        mTitle = (EditText) findViewById(R.id.title);
        mDescription = (EditText) findViewById(R.id.description);
        mLocation = (EditText) findViewById(R.id.location);
        mDate = (CalendarView) findViewById(R.id.date);

        mSave.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {

                final String title = mTitle.getText().toString();
                final String description = mDescription.getText().toString();
                final String location = mLocation.getText().toString();
                Calendar date = Calendar.getInstance();
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DAY_OF_MONTH);

                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setData(CalendarContract.Events.CONTENT_URI);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, title);
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, location);
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, description);

                
                GregorianCalendar calDate = new GregorianCalendar(year,month,day);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                calIntent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
                calIntent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                startActivity(calIntent);

            }

        });
    }
}
