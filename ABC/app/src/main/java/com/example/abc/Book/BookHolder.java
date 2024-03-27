package com.example.abc.Book;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.R;

public class BookHolder extends RecyclerView.ViewHolder {
    private TextView book;
    public BookHolder(View itemView) {
        super(itemView);
        book = (TextView) itemView.findViewById(R.id.textViewBook);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Supprimer la ligne associée à cet élément de la liste
                /*int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onDeleteClick(position);
                }*/
            }
        });
    }



    public TextView getTextViewBook() {
        return book;
    }




}
