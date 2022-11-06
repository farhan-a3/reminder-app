package com.example.reminderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private List<Reminder> reminderList;
    private Database db;
    private ReminderMenu activity;


    public ReminderAdapter(Database db, ReminderMenu activity) {
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_reminder_menu, parent, false);
        return new ViewHolder(newView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        db.openDatabase();

        final Reminder task = reminderList.get(position);
        holder.name.setText(task.getName());
        holder.date.setText(task.getDate());
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public Context getContext() {
        return activity;
    }

    public void setReminderList(List<Reminder> reminderList) {
        this.reminderList = reminderList;
        notifyDataSetChanged();
    }

    public void deleteEntry(int position) {
        Reminder task = reminderList.get(position);
        db.deleteReminder(task.getId());
        reminderList.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.reminderName);
            date = view.findViewById(R.id.reminderDate);
        }
    }
}
