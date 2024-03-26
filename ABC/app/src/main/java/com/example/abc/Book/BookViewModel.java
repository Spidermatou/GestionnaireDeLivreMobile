package com.example.abc.Book;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.abc.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BookViewModel extends AndroidViewModel {
    private MutableLiveData<JSONArray> book;

    public MutableLiveData<JSONArray> getBook() {
        return book;
    }


    private void loadData(Context context) throws Exception {
        InputStream inputStream = context.getResources().openRawResource(R.raw.rawbooks);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null ) {
            sb.append( line );
            sb.append( '\n' );
        }
        JSONArray json = new JSONArray(sb.toString());
        book.setValue(json);
    }
    public BookViewModel(Context context) throws Exception {
        super((Application) context.getApplicationContext());
        book = new MutableLiveData<>();
        loadData(context);
    }
}
