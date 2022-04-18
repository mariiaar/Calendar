package com.example.calendar;

import android.os.Build;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.os.Bundle;
import java.time.LocalDate;
import java.util.HashMap;

import android.text.InputType;
import android.view.View;
import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button buttonSave;
    EditText editText;
    EditText editText2;
    EditText editText3;
    CalendarView calendarView;
    LocalDate selectedDate;

    HashMap<LocalDate, Datapoint> events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        calendarView = findViewById(R.id.calendarView);
        buttonSave=findViewById(R.id.buttonSave);
        events = new HashMap<LocalDate, Datapoint>();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = LocalDate.of(year, month, dayOfMonth);
                Datapoint dateData = events.get(selectedDate);
                if (dateData != null) {
                    editText.setText(dateData.event);
                    editText2.setText(dateData.place);
                    editText3.setText(dateData.review);
                } else {
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Datapoint data = new Datapoint(
                        editText.getText().toString(),
                        editText2.getText().toString(),
                        editText3.getText().toString()

                );
                events.put(selectedDate, data);
            }
        });
    }
}