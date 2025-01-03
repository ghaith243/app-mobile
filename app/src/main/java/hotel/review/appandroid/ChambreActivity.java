package hotel.review.appandroid;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChambreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RoomAdapter adapter;
    private List<Chambre> roomList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chambre);

        // Initialisation des vues
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //searchView = findViewById(R.id.searchView);

        // Création de la liste des chambres
        roomList = new ArrayList<>();
        roomList.add(new Chambre("Chambre Deluxe", 120, R.drawable.image1));
        roomList.add(new Chambre("Suite Familiale", 200, R.drawable.image2));


        // Configuration de l'adaptateur
        adapter = new RoomAdapter(this, roomList);
        recyclerView.setAdapter(adapter);

        // Configuration de la barre de recherche
        // configureSearchView();
    }

/*private void configureSearchView() {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            // Pas besoin de gestion lors de la soumission (retour/enter)
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // Appel de la méthode filter() dans l'adaptateur pour actualiser la liste
            adapter.filter(newText);
            return true;
        }
    });
}*/
}

