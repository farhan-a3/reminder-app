package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReminderMenu extends AppCompatActivity {

    FloatingActionButton addEntry;
    private RecyclerView recyclerView;
    private ReminderAdapter adapter;
    private List<Reminder> reminderList;
    private Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_menu);

        db = new Database(this);
        db.openDatabase();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReminderAdapter(db, this);
        recyclerView.setAdapter(adapter);

        reminderList = db.getReminderList();
        adapter.setReminderList(reminderList);

        //dis is for adding entries (unfinished)
        addEntry = findViewById(R.id.fab);
    }

    @Override
    public void onResume() {
        super.onResume();
        reminderList = db.getReminderList();
        adapter.setReminderList(reminderList);
        adapter.notifyDataSetChanged();
    }


}