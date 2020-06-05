package com.example.dictionary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordsRepository mRepository ;
    private LiveData<List<Words>> mAllWords ;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordsRepository(application);
        mAllWords = mRepository.getAllWords();
    } // end constructor
    public void insert(Words words){
        mRepository.insert(words);
    }// end insert()
    public void delete(Words words){
        mRepository.delete(words);
    }// end delete()
    public void update(Words words){
        mRepository.update(words);
    }// end update()
    public LiveData<List<Words>> getAllWords(){
        return  mAllWords ;
    } // end getAllWords()

} // end base class
