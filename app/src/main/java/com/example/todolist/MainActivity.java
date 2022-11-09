package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private FloatingActionButton floatingActionButton;

    ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        for (int i = 0; i < 25; i++) {
            Random random = new Random();
            Note note = new Note(i, "Note " + i, random.nextInt(3));
            notes.add(note);

        }

        showNotes();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = EditNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });

    }

    private void initViews() {
        linearLayout = findViewById(R.id.linearLayout);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

    private void showNotes() {
        for (Note note : notes) {

            View view = getLayoutInflater().inflate(
                    R.layout.note_item,
                    linearLayout,
                    false
            );
            TextView textViewNote = view.findViewById(R.id.textViewNote);
            textViewNote.setText(note.getText());

            int colorId;
            switch (note.getPriority()) {
                case 0:
                    colorId = android.R.color.holo_blue_light;
                    break;
                case 1:
                    colorId = android.R.color.holo_green_light;
                    break;
                default:
                    colorId = android.R.color.holo_red_light;
            }
            int color = ContextCompat.getColor(this,colorId);
            textViewNote.setBackgroundColor(color);
            linearLayout.addView(view);
        }



    }
}