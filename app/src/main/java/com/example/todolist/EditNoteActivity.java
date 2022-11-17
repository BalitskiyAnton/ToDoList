package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editText;
    private RadioButton radioButton0;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private Button button;
    private Database database = Database.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        initViews();

        radioButton1.setChecked(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

    }

    private void initViews() {
        editText = findViewById(R.id.editTextTextPersonName);
        radioButton0 = findViewById(R.id.radioButtonPriority0);
        radioButton1 = findViewById(R.id.radioButtonPriority1);
        radioButton2 = findViewById(R.id.radioButtonPriority2);
        button = findViewById(R.id.button);
    }

    private void saveNote() {
        String text = editText.getText().toString().trim();
        int priority = getPriority();
        int id = database.getNotes().size();
        Note note = new Note(id, text, priority);
        database.add(note);

        finish();
    }

    private int getPriority() {
        int priority;
        if (radioButton0.isChecked()) {
            priority = 0;
        } else if (radioButton1.isChecked()) {
            priority = 1;
        } else {
            priority = 2;
        }
        return priority;

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, EditNoteActivity.class);

    }
}