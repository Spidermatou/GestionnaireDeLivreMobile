package com.example.abc.Author;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.Book.BookFragment;
import com.example.abc.Book.DescriptionFragment;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorDescription extends Fragment {
    private static final String ARG_AUTHOR_INFO = "author_info";
    private String AdresseURL = "http://192.168.10.52:3000/";
    private String authorInfo;
    private String authorName;
    private TextView textViewAuthorName;
    private TextView textViewBookList;
    private Button buttonBacktoAuthors;
    private Button buttonDeleteAuthor;

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
        buttonBacktoAuthors = view.findViewById(R.id.buttonBacktoAuthors);
        buttonDeleteAuthor = view.findViewById(R.id.buttonDeleteAuthor);

        buttonBacktoAuthors.setOnClickListener(v -> {
            // Retourner à la liste des auteurs
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerViewAuthor, new AuthorsListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        buttonDeleteAuthor.setOnClickListener(v -> {
            // Supprimer l'auteur
            try {
                JSONObject authorObject = new JSONObject(authorInfo);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url = AdresseURL + "authors/" + authorObject.getString("id");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, url, null,
                        response -> {
                            // Supprimer l'auteur de la liste des auteurs
                            Log.d("Delete Response", response.toString());
                        }, error -> {
                    // Gérer l'erreur ici
                    //Afficher l'erreur
                    System.out.println(error.getMessage());
                });

                queue.add(jsonObjectRequest);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerViewAuthor, new AuthorsListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
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
            JSONObject authorObject = new JSONObject(authorInfo);
            textViewAuthorName.setText(authorObject.getString("firstname") + " " +
                    authorObject.getString("lastname"));

            RequestQueue queue = Volley.newRequestQueue(getContext());
            String url = AdresseURL + "authors/" + authorObject.getString("id") + "/books";
            final StringBuilder bookList = new StringBuilder();
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response -> {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject book = response.getJSONObject(i);
                        bookList.append(book.getString("title")).append("\n");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(bookList.toString());
                textViewBookList.setText(bookList.toString());
            }, error -> {
                // Gérer l'erreur ici
                //Afficher l'erreur
                System.out.println(error.getMessage());
            });

            queue.add(jsonArrayRequest);



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
