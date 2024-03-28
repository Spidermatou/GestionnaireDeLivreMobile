package com.example.abc.Author;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.abc.Book.DescriptionFragment;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorDescription extends Fragment {
    private static final String ARG_AUTHOR_INFO = "author_info";

    private String authorInfo;
    private String authorName;
    private TextView textViewAuthorName;
    private TextView textViewBookList;

    public static AuthorDescription newInstance(String authorinfo) {
        AuthorDescription fragment = new AuthorDescription();
        Bundle args = new Bundle();
        args.putString(ARG_AUTHOR_INFO, authorinfo);
        fragment.setArguments(args);
        System.out.println("DescriptionFragment.newInstance: " + authorinfo);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            authorInfo = getArguments().getString(ARG_AUTHOR_INFO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.author_description_fragment , container, false);

        textViewAuthorName = view.findViewById(R.id.textViewAuthorName);
        textViewBookList = view.findViewById(R.id.textViewBookListOfAuthor);

        // Vérifier si des arguments ont été passés
        if (getArguments() != null) {
            // Récupérer les informations sur le livre
            authorInfo = getArguments().getString(ARG_AUTHOR_INFO);
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
            JSONObject bookObject = new JSONObject(authorInfo);
            textViewAuthorName.setText(bookObject.getString("firstname") + " " +
                    bookObject.getString("lastname"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
