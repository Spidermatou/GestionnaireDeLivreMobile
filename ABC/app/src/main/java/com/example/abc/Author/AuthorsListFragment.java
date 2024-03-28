package com.example.abc.Author;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc.Book.BookAdapter;
import com.example.abc.Book.BookViewModel;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorsListFragment extends Fragment {

    public AuthorsListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment authorsList.
     */
    public static AuthorsListFragment newInstance() {
        return new AuthorsListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authors_list, container, false);
        recyclerView = view.findViewById(R.id.AuthorsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        try {
            AuthorsViewModel authorsViewModel = new AuthorsViewModel(getContext());
            authorsViewModel.getAuthors().observe(getViewLifecycleOwner(), (JSONArray authors) -> {
                AuthorsDataAdapter authorsAdapter = new AuthorsDataAdapter(authors, getParentFragmentManager());
                recyclerView.setAdapter(authorsAdapter);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return view;
    }
}