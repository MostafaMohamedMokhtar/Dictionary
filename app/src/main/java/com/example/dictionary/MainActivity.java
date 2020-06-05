package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel ;
    private RecyclerView mRecyclerView;
    private WordAdapter mWordAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_button_id);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AddNewWordActivity.class);
                startActivityForResult(intent ,1);
            }
        });

        mRecyclerView = findViewById(R.id.recycler_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mWordAdapter = new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);

        // view Model
        mWordViewModel = ViewModelProviders.of(this ).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {
                // update UI
                // update RecycleView
                mWordAdapter.setWord(words);
               // Toast.makeText(getApplicationContext() , " on changed work " , Toast.LENGTH_LONG).show();
            } // end onChanged
        });
        mWordAdapter.onItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Words word) {
                Intent i = new Intent(MainActivity.this , AddNewWordActivity.class);
                i.putExtra(AddNewWordActivity.EXTRA_ID , word.getId() );
                i.putExtra(AddNewWordActivity.EXTRA_WORD , word.getWordName() );
                i.putExtra(AddNewWordActivity.EXTRA_MEANING , word.getWordMeaning() );
                i.putExtra(AddNewWordActivity.EXTRA_TYPE , word.getWordType() );
                startActivity(i);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // delete item
                int position = viewHolder.getAdapterPosition();
                mWordViewModel.delete(mWordAdapter.getWordAt(position));
            }
        }).attachToRecyclerView(mRecyclerView);
    } // end onCreate
} // end MainActivity
