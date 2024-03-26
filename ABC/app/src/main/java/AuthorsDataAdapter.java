import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.AuthorViewHolder;
import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Connait le view holder et modifie ses elements graphiques
 */
public class AuthorsDataAdapter extends RecyclerView.Adapter {
    private final JSONArray authorsData;
    private Context appContext;

    public AuthorsDataAdapter(JSONArray array){
        super();
        authorsData = array;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_author, parent, false);

        appContext = parent.getContext();

        return new AuthorViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       if (holder instanceof AuthorViewHolder) {
           AuthorViewHolder authVH = (AuthorViewHolder) holder;

           String firstname;
           String lastname;
            try {
                // retrieve name
                lastname = authorsData.getJSONObject(position).getString("lastname");
                firstname = authorsData.getJSONObject(position).getString("firstname");
                authVH.getauthorName().setText(lastname + " " + firstname);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getItemCount() {
        return authorsData.length();
    }
}
