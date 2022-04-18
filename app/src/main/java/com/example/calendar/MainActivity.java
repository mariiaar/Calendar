package com.example.calendar;

import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    CalendarView calendarView;
    String selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        calendarView = findViewById(R.id.calendarView);
        //editText.setInputType(InputType.TYPE_NULL);
        //editText.setOnClickListener(new View.OnClickListener() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
            }
            //@Override
            //public void onClick(View v) {
}