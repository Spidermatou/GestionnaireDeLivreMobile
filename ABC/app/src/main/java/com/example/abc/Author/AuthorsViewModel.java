package com.example.abc.Author;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class AuthorsViewModel extends AndroidViewModel {
    private MutableLiveData<JSONArray> authors;
    public AuthorsViewModel(@NonNull Context context) {
        super((Application) context.getApplicationContext());
        authors = new MutableLiveData<>(new JSONArray());
        try {
            loadData(context);
        }
        catch (Exception e){
            System.out.println("Error during data loading");
        }
    }

    public MutableLiveData<JSONArray> getAuthors() {
        return authors;
    }


    // V1.0 en dur
    private void loadData(Context context) throws IOException, JSONException {

        InputStream inputStream = context.getResources().openRawResource(R.raw.rawauthors);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // get entire text
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ( (line =  reader.readLine()) != null  ){
            stringBuilder.append(line);
        }

        // parse to json
        JSONArray data = new JSONArray(stringBuilder.toString());

        System.out.println(data); // test
        authors.setValue(data);
    }

}
