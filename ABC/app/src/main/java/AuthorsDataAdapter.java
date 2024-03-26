import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Connait le view holder et modifie ses elements graphiques
 */
public class AuthorsDataAdapter extends RecyclerView.Adapter {
    private final JSONArray authorsData;

    public AuthorsDataAdapter(JSONArray array){
        super();
        authorsData = array;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        /* View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_view_holder, parent, false); */

        // appContext = parent.getContext();
        // return new AuthorsViewHolder(view);
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       /* if (holder instanceof PokemonViewHolder) {
            PokemonViewHolder pokemonViewHolder = (PokemonViewHolder) holder;
            String name;
            String stats;
            Bitmap icon;
            try {
                // retrieve name
                name = "#" + (position + 1) + " " + authorsData.getJSONObject(position).getJSONObject("name").getString("english");

                // retrieve and format stats
                stats = "<b> HP: </b>" + authorsData.getJSONObject(position).getJSONObject("base").getString("HP") + "<br>" +
                        "<b> Attack: </b>" + authorsData.getJSONObject(position).getJSONObject("base").getString("Attack") + "<br>" +
                        "<b> Defense: </b>" + authorsData.getJSONObject(position).getJSONObject("base").getString("Defense") + "<br>" +
                        "<b> Sp. Attack: </b>" + authorsData.getJSONObject(position).getJSONObject("base").getString("Sp. Attack") + "<br>" +
                        "<b> Sp. Defense: </b>" + authorsData.getJSONObject(position).getJSONObject("base").getString("Sp. Defense") + "<br>";


            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            pokemonViewHolder.getPokemonName().setText(name);
            pokemonViewHolder.getPokemonStats().setText(Html.fromHtml(stats, 0));
        } */
    }

    @Override
    public int getItemCount() {
        return authorsData.length();
    }
}
