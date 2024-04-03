package com.example.abc.Author;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.R;
import com.example.abc.ui.dashboard.DashboardFragment;
import com.example.abc.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAuthorFragment extends Fragment {
    private EditText textViewFirstName;
    private EditText textViewLastName;
    private Button buttonCreate;
    private Button back;

    public CreateAuthorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.create_author_fragment, container, false);
        textViewFirstName = root.findViewById(R.id.editTextFirstName);
        textViewLastName = root.findViewById(R.id.editTextLastName);
        buttonCreate = root.findViewById(R.id.buttonCreateAuthor);
        back = root.findViewById(R.id.buttonBack);
        buttonCreate.setOnClickListener(v -> {
            // Create a new author
            String firstName = textViewFirstName.getText().toString();
            String lastName = textViewLastName.getText().toString();
            // Create a new author
            CreateAnAuthor(firstName, lastName);
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerViewAuthor, new AuthorsListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        back.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace( R.id.fragmentContainerViewAuthor, new AuthorsListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return root;
    }

    public void CreateAnAuthor(String firstName, String lastName) {
        // Create a new author
        String URL = "http://192.168.10.52:3000/authors";

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("firstname", firstName);
            jsonParams.put("lastname", lastName);
            // Ajoutez d'autres paramètres si nécessaire
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonParams,
                response -> {
                    // Réponse de la requête POST
                    Log.d("Response", response.toString());
                },
                error -> {
                    // Gestion des erreurs
                    Log.e("Error", "Erreur lors de la requête POST: " + error.toString());
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}
