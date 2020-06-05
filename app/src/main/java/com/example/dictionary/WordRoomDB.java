package com.example.dictionary;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Words.class ,  version = 1)
public abstract class WordRoomDB extends RoomDatabase {
    private static WordRoomDB instance ;

    public abstract WordsDao wordsDao();

    // singleton
    public static synchronized WordRoomDB getInstance(Context context){
        if(instance == null ){
            instance = Room.databaseBuilder(context.getApplicationContext() ,
                    WordRoomDB.class , "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        } // end if
        return instance ;
    } // end singleton

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    }  ; // end roomCallBack()

    private static class PopulateDataAsyncTask extends AsyncTask<Void , Void , Void>{
        private WordsDao mWordsDao ;

        public PopulateDataAsyncTask(WordRoomDB db) {
            mWordsDao = db.wordsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.insert(new Words(" book " , "Book " , "noun"));
            mWordsDao.insert(new Words(" book " , "Book " , "noun"));
            mWordsDao.insert(new Words(" book " , "Book " , "noun"));
            return null;

        } // end doInBackground()
    } // end inner Class

} // end outer class
