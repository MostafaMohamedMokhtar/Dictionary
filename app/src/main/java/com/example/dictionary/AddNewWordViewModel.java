package com.example.dictionary;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddNewWordViewModel extends AndroidViewModel {

    private WordsRepository mRepository ;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordsRepository(application);
    } // end constructor
    public void insert(Words words){
        mRepository.insert(words);
    }// end insert()
    public void update(Words words){
        mRepository.update(words);
    }// end update()

}
