package com.example.abc.Author;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class AuthorsViewModel extends AndroidViewModel {
    private String AdresseURL = "http://192.168.10.52:3000/";
    private MutableLiveData<JSONArray> authors;
    public AuthorsViewModel(@NonNull Context context) {
        super((Application) context.getApplicationContext());
        authors = new MutableLiveData<>();

        try {
            loadData(context);
        }
        catch (Exception e){
            System.out.println("Error during data loading");
            authors.setValue(new JSONArray());
        }

    }

    public MutableLiveData<JSONArray> getAuthors() {
        return authors;
    }


    // V1.0 en dur
    private void loadData(Context context) throws IOException, JSONException {

        System.out.println("On est la");
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = AdresseURL + "authors";
        // Request a string response from the provided URL.
        System.out.println("Pas de probleme ici");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    authors.setValue(response);
                    System.out.println("IL y a pas de probleme ici");
                    System.out.println(response.toString());
                },
                error -> {
                    // Gérer l'erreur ici
                    //Afficher l'erreur
                    System.out.println(error.getMessage());

                    authors.setValue(new JSONArray());
                    System.out.println("Il y a un probleme ici");
                });
        queue.add(jsonArrayRequest);
    }


    public String getAdresseURL() {
        return AdresseURL;
    }
}
