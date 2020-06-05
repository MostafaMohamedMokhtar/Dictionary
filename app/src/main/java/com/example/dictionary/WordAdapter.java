package com.example.dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private static List<Words> mWordList = new ArrayList<>();
    private static OnItemClickListener mListener ;
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item , parent , false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Words currentWord = mWordList.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewMeaning.setText(currentWord.getWordMeaning());
        holder.textViewType.setText(currentWord.getWordType());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
    public void setWord(List<Words> words){
        mWordList = words ;
        notifyDataSetChanged();
    } // end setWords

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewWord ;
        public TextView textViewMeaning;
        public TextView textViewType ;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.word_text_view);
            textViewMeaning = itemView.findViewById(R.id.meaning_text_view);
            textViewType = itemView.findViewById(R.id.type_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if( mListener != null && index != RecyclerView.NO_POSITION){
                        mListener.onItemClick(mWordList.get(index));
                    } // end if
                } // end onClick()
            });
        } // end WordViewHolder constructor
    } // end inner class
    public interface OnItemClickListener{
        void onItemClick(Words word);
    } // end interface
    public void onItemClickListener(OnItemClickListener listener){
        mListener = listener ;
    } // end onItemClickListener()
    public Words getWordAt(int position){
        return mWordList.get(position);
    }
} // end outer class
