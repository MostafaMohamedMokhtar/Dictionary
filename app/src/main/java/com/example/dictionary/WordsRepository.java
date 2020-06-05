package com.example.dictionary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordsRepository {
    private WordsDao mWordsDao ;
    private LiveData<List<Words>> getAllWords ;

    public WordsRepository(Application context ){
        WordRoomDB db = WordRoomDB.getInstance(context);
        mWordsDao = db.wordsDao();
        getAllWords = mWordsDao.getAllWords();
    } // end constructor

    // insert
    public void insert(Words words){
        new InsertAsyncTask(mWordsDao).execute(words);
    }
    // delete
    public void delete(Words words){
        new DeleteAsyncTask(mWordsDao).execute(words);
    }
    // update
    public void update(Words words){
        new UpdateAsyncTask(mWordsDao).execute(words);
    }
    // getAllWords
    public LiveData<List<Words>>getAllWords(){
        return getAllWords ;
    }
    // deleteAllWords
    public void deleteAllWords(){
        new DeleteAllWordsAsyncTask(mWordsDao).execute();
    }

    public static class InsertAsyncTask extends AsyncTask<Words , Void , Void>{

        private WordsDao mWordsDao ;
        public InsertAsyncTask(WordsDao mWordsDao)
        {
            this.mWordsDao = mWordsDao ;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.insert(words[0]);
            return null;
        }
    } // end class InsertAsyncTask
    public static class DeleteAsyncTask extends AsyncTask<Words , Void , Void>{

        private WordsDao mWordsDao ;
        public DeleteAsyncTask(WordsDao mWordsDao)
        {
            this.mWordsDao = mWordsDao ;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    } // end class DeleteAsyncTask

    public static class UpdateAsyncTask extends AsyncTask<Words , Void , Void>{

        private WordsDao mWordsDao ;
        public UpdateAsyncTask(WordsDao mWordsDao)
        {
            this.mWordsDao = mWordsDao ;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    } // end class UpdateAsyncTask
    public static class DeleteAllWordsAsyncTask extends AsyncTask<Void , Void , Void>{

        private WordsDao mWordsDao ;

        public DeleteAllWordsAsyncTask(WordsDao mWordsDao) {
            this.mWordsDao = mWordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    } // end class UpdateAsyncTask
} // end base class
