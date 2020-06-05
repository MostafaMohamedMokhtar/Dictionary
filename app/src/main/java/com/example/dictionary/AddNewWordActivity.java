package com.example.dictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {
    private EditText wordEditText ;
    private EditText meaningEditText ;
    private EditText typeEditText ;

    public static final String EXTRA_ID = "com.example.dictionary.extraId" ;
    public static final String EXTRA_WORD = "com.example.dictionary.extraWord" ;
    public static final String EXTRA_MEANING = "com.example.dictionary.extraMeaning" ;
    public static final String EXTRA_TYPE = "com.example.dictionary.extraType" ;
    private boolean editMode ;
    private int mID ;

    private AddNewWordViewModel mViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word2);

        wordEditText = findViewById(R.id.word_edit_text);
        meaningEditText = findViewById(R.id.meaning_edit_text);
        typeEditText = findViewById(R.id.type_edit_text);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            // update word
            setTitle("Edit Word ");
            editMode = true ;
            mID = intent.getIntExtra(EXTRA_ID , -1);
            wordEditText.setText(intent.getStringExtra(EXTRA_WORD));
            meaningEditText.setText(intent.getStringExtra(EXTRA_MEANING));
            typeEditText.setText(intent.getStringExtra(EXTRA_TYPE));
        }// end if
        else {
            // insert new word
            setTitle("Add new Word ");
            editMode =false ;
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);
        mViewModel = ViewModelProviders.of(this).get(AddNewWordViewModel.class);
    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save_id:
                saveWord();
                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void saveWord(){
        String word = wordEditText.getText().toString().trim();
        String meaning = meaningEditText.getText().toString().trim();
        String type = typeEditText.getText().toString().trim();
        Words wordObject = new Words(word , meaning , type);
        if(word.isEmpty() || meaning.isEmpty() || type.isEmpty()){
            Toast.makeText(getApplicationContext() , " please fill all fields" , Toast.LENGTH_LONG).show();
            return;
        } // end if
        if(editMode){
            wordObject.setId(mID);
            mViewModel.update(wordObject);
        }
        else {
            mViewModel.insert(wordObject);
        }

        finish();
    } // end saveWord()
} // end class
