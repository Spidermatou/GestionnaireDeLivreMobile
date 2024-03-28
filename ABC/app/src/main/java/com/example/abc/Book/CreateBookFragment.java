package com.example.abc.Book;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.abc.R;

public class CreateBookFragment extends Fragment {
    private EditText textViewTitle;
    private EditText textViewAuthor;
    private Button buttonCreate;

    public CreateBookFragment() {
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
        View root = inflater.inflate(R.layout.create_book_fragment, container, false);
        textViewTitle = root.findViewById(R.id.editTextTitle);
        textViewAuthor = root.findViewById(R.id.editTextAuthor);
        buttonCreate = root.findViewById(R.id.buttonSubmit);
        return root;
    }
}
