package com.example.abc.Book;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.R;
import com.example.abc.databinding.FragmentHomeBinding;
import com.example.abc.ui.home.HomeFragment;
import com.example.abc.ui.home.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DescriptionFragment extends Fragment {
    private static final String ARG_BOOK_INFO = "book_info";
    private String AdresseURL = "http://192.168.10.52:3000/";
    private String bookInfo;
    private TextView textViewTitle;
    private TextView textViewAuthor;
    private TextView textViewTags;
    private TextView textViewRating;
    private TextView textViewComments;
    private Button buttonBackToList;
    private Button buttonDelete;

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
        textViewTags = view.findViewById(R.id.textViewTags);
        textViewRating = view.findViewById(R.id.textViewNote);
        textViewComments = view.findViewById(R.id.textViewComms);
        buttonBackToList = view.findViewById(R.id.buttonBackToList);
        buttonDelete = view.findViewById(R.id.buttonDelete);

        buttonBackToList.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, new BookFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        buttonDelete.setOnClickListener(v -> {
            RequestQueue queue = Volley.newRequestQueue(getContext());
            JSONObject bookObject;
            try {
                 bookObject = new JSONObject(bookInfo);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            String url = null;
            try {
                url = AdresseURL + "books/" + bookObject.getString("id");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                    response -> {
                        // Traitement de la réponse en cas de succès de la suppression
                        Log.d("Delete Response", response);
                    },
                    error -> {
                        // Gestion des erreurs
                        Log.e("Delete Error", "Erreur lors de la suppression: " + error.toString());
                    });

            queue.add(deleteRequest);
        });

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

            //textViewDescription.setText(bookObject.getString("description"));


            RequestQueue queue = Volley.newRequestQueue(getContext());
            String url = AdresseURL + "books/" + bookObject.getString("id")+"/tags";
            System.out.println(url);
            final StringBuilder tagsStringBuilder = new StringBuilder();
            JsonArrayRequest request = new JsonArrayRequest(url, response -> {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject tagObject = response.getJSONObject(i);
                        String tagName = tagObject.getString("name");

                        // Ajouter un espace si c'est le deuxième tag ou plus
                        if (i > 0) {
                            tagsStringBuilder.append(" ");
                        }
                        tagsStringBuilder.append(tagName);
                    }

                    // Une fois que tous les tags ont été ajoutés, mettre à jour le TextView
                    textViewTags.setText(tagsStringBuilder.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                System.out.println("Erreur lors de la récupération des tags");
            });
            queue.add(request);
            textViewTags.setText(tagsStringBuilder.toString());


            String urlRating = AdresseURL + "books/" + bookObject.getString("id")+"/ratings";

            final StringBuilder ratingStringBuilder = new StringBuilder();
            JsonArrayRequest requestRating = new JsonArrayRequest(urlRating, response -> {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject ratingObject = response.getJSONObject(i);
                        String rating = ratingObject.getString("value");

                        // Ajouter un espace si c'est le deuxième tag ou plus
                        if (i > 0) {
                            ratingStringBuilder.append(" / ");
                        }
                        ratingStringBuilder.append(rating);
                    }

                    // Une fois que tous les tags ont été ajoutés, mettre à jour le TextView
                    textViewRating.setText(ratingStringBuilder.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                System.out.println("Erreur lors de la récupération des tags");
            });

            queue.add(requestRating);

            textViewRating.setText(ratingStringBuilder.toString());

            String urlComments = AdresseURL + "books/" + bookObject.getString("id")+"/comments";

            final StringBuilder commentsStringBuilder = new StringBuilder();
            JsonArrayRequest requestComments = new JsonArrayRequest(urlComments, response -> {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject commentObject = response.getJSONObject(i);
                        String comment = commentObject.getString("content");

                        // Ajouter un espace si c'est le deuxième tag ou plus
                        if (i > 0) {
                            commentsStringBuilder.append("\n");
                        }
                        commentsStringBuilder.append(comment);
                    }

                    // Une fois que tous les tags ont été ajoutés, mettre à jour le TextView
                    textViewComments.setText(commentsStringBuilder.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                System.out.println("Erreur lors de la récupération des tags");
            });

            queue.add(requestComments);

            textViewComments.setText(commentsStringBuilder.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
