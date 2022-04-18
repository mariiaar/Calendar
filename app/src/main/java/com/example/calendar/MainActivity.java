package com.example.calendar;

import android.os.Build;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.os.Bundle;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import android.text.InputType;
import android.view.View;
import android.os.Bundle;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static String FILE_NAME = "events.json";

    Button buttonSave;
    EditText editText;
    EditText editText2;
    EditText editText3;
    CalendarView calendarView;
    LocalDate selectedDate;

    HashMap<String, Datapoint> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        calendarView = findViewById(R.id.calendarView);
        buttonSave=findViewById(R.id.buttonSave);
        events = new HashMap<String, Datapoint>();

        selectedDate = LocalDate.now();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = LocalDate.of(year, month, dayOfMonth);
                Datapoint dateData = events.get(selectedDate.toString());
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
                events.put(selectedDate.toString(), data);
                Gson gson = new Gson();
                String json = gson.toJson(events);
                System.out.println(json);
                try {
                    saveFile(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            events = readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveFile(String json) throws IOException {
        String filePath = new File(this.getFilesDir(), FILE_NAME).getPath();
        FileWriter fw = new FileWriter(filePath);

        fw.write(json);
        fw.close();
    }

    private HashMap<String, Datapoint> readFile() throws IOException {
        File file = new File(this.getFilesDir(), FILE_NAME);
        Scanner sc = new Scanner(file);

        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine()) {
            sb.append(sc.nextLine());
        }

        String json = sb.toString();

        // From Gson documentation: https://github.com/google/gson/blob/master/UserGuide.md
        Gson gson = new Gson();
        Type targetType = new TypeToken<HashMap<String, Datapoint>>(){}.getType();
        return gson.fromJson(json, targetType);
    }
}