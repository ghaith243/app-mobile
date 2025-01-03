package hotel.review.appandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        EditText roomNumberEditText = findViewById(R.id.roomNumberEditText);
        EditText guestNameEditText = findViewById(R.id.guestNameEditText);
        EditText dateEditText = findViewById(R.id.dateEditText);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomNumber = roomNumberEditText.getText().toString().trim();
                String guestName = guestNameEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();

                if (roomNumber.isEmpty() || guestName.isEmpty() || date.isEmpty()) {
                    Toast.makeText(ReservationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Replace 1 with the actual logged-in user's ID
                int userId = 1;

                boolean success = dbHelper.addReservation(guestName, roomNumber, date);
                if (success) {
                    Toast.makeText(ReservationActivity.this, "Reservation Successful", Toast.LENGTH_SHORT).show();
                    roomNumberEditText.setText("");
                    guestNameEditText.setText("");
                    dateEditText.setText("");
                } else {
                    Toast.makeText(ReservationActivity.this, "Reservation Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
