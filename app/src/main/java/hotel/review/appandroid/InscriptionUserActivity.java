package hotel.review.appandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionUserActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_user);

        dbHelper = new DatabaseHelper(this);

        EditText usernameInput = findViewById(R.id.username);
        EditText passwordInput = findViewById(R.id.password);
        Button signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                // Vérification si les champs sont remplis
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(InscriptionUserActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Enregistrer l'utilisateur dans la base de données
                    if (dbHelper.registerUser(username, password)) {
                        Toast.makeText(InscriptionUserActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                        // Retourner à l'activité de connexion
                        finish();
                    } else {
                        Toast.makeText(InscriptionUserActivity.this, "Erreur lors de l'inscription", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
