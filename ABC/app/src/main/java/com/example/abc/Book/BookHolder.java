package com.example.abc.Book;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookHolder extends RecyclerView.ViewHolder {
    private JSONArray bookDataSet;
    private TextView book;
    private FragmentManager fragmentManager ;
    public BookHolder(View itemView, FragmentManager fragmentManager) {
        super(itemView);
        book = (TextView) itemView.findViewById(R.id.textViewBook);
        this.fragmentManager = fragmentManager;

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookDataSet = ((BookAdapter) ((RecyclerView) itemView.getParent()).getAdapter()).getBookDataSet();
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Récupérer les informations sur le livre sélectionné
                    JSONObject selectedBook = null;
                    try {
                        selectedBook =  bookDataSet.getJSONObject(position);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Passer les informations au fragment parent
                    if (selectedBook != null && itemView.getContext() instanceof FragmentActivity) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Créer et configurer le DescriptionFragment avec les informations sur le livre sélectionné
                        DescriptionFragment descriptionFragment = DescriptionFragment.newInstance(selectedBook.toString());
                        // Remplacer le contenu du FragmentContainerView par le DescriptionFragment
                        fragmentTransaction.replace( R.id.fragmentContainerView, descriptionFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
            }
        });
    }



    public TextView getTextViewBook() {
        return book;
    }


}
