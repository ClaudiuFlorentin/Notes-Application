package com.claudiuapp.notesapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    TextView noItemText;
    SimpleDatabase simpleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton button = findViewById(R.id.addNoteButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openAddNote();
            }
        });

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        noItemText = findViewById(R.id.noItemText);
        simpleDatabase = new SimpleDatabase(this);
        List<Note> allNotes = simpleDatabase.getAllNotes();
        recyclerView = findViewById(R.id.allNotesList);

        if(allNotes.isEmpty())
        {
            noItemText.setVisibility(View.VISIBLE);
        }
        else
        {
            noItemText.setVisibility(View.GONE);
            displayList(allNotes);
        }
    }

    public void openAddNote()
    {
        Intent intent = new Intent(this, AddNote.class);
        startActivity(intent);
    }

    private void displayList(List<Note> allNotes)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, allNotes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        List<Note> getAllNotes = simpleDatabase.getAllNotes();
        if(getAllNotes.isEmpty())
        {
            noItemText.setVisibility(View.VISIBLE);
        }
        else {
            noItemText.setVisibility(View.GONE);
            displayList(getAllNotes);
        }
    }
}
