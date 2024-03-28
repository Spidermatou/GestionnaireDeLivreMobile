package com.example.abc.Book;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.abc.R;
import com.example.abc.databinding.FragmentHomeBinding;
import com.example.abc.ui.home.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DescriptionFragment extends Fragment {
    private static final String ARG_BOOK_INFO = "book_info";
    private String bookInfo;
    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewDescription;
    private TextView textViewTags;
    private TextView textViewRating;
    private TextView textViewComments;

    public static DescriptionFragment newInstance(String bookInfo) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BOOK_INFO, bookInfo);
        fragment.setArguments(args);
        System.out.println("DescriptionFragment.newInstance: " + bookInfo);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookInfo = getArguments().getString(ARG_BOOK_INFO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.description_fragment , container, false);

        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewAuthor = view.findViewById(R.id.textViewAuthor);
        textViewDescription = view.findViewById(R.id.textViewDesc);
        textViewTags = view.findViewById(R.id.textViewTags);
        textViewRating = view.findViewById(R.id.textViewNote);
        textViewComments = view.findViewById(R.id.textViewComms);

        // Vérifier si des arguments ont été passés
        if (getArguments() != null) {
            // Récupérer les informations sur le livre
            bookInfo = getArguments().getString(ARG_BOOK_INFO);
            // Mettre à jour les TextViews avec les informations sur le livre
            updateTextViews();
        }

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void updateTextViews() {
        // Extraire les informations du livre depuis bookInfo (peut-être au format JSON)
        // et mettre à jour les TextViews avec ces informations
        try {
            JSONObject bookObject = new JSONObject(bookInfo);
            textViewTitle.setText(bookObject.getString("title"));
            textViewAuthor.setText(bookObject.getJSONObject("author").getString("firstname") + " " +
                    bookObject.getJSONObject("author").getString("lastname"));
            // Mettre à jour les autres TextViews de la même manière...

            textViewDescription.setText(bookObject.getString("description"));

            JSONArray tagsArray = bookObject.getJSONArray("tags");
            String tagsString = "";

            for (int i = 0; i < tagsArray.length(); i++) {
                // Ajouter un espace si c'est le deuxième tag ou plus
                if (i > 0) {
                    tagsString += " ";
                }
                tagsString += tagsArray.getString(i);
            }

            textViewTags.setText(tagsString);

            textViewRating.setText(bookObject.getString("rating"));

            JSONArray commentsArray = bookObject.getJSONArray("comments");
            String commentsString = "";

            for (int i = 0; i < commentsArray.length(); i++) {
                // Ajouter un espace si c'est le deuxième commentaire ou plus
                if (i > 0) {
                    commentsString += " ";
                }
                commentsString += commentsArray.getString(i);
            }

            textViewComments.setText(commentsString);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
