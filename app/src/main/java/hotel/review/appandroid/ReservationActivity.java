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

        EditText guestNameEditText = findViewById(R.id.guestNameEditText);
        EditText checkInDateEditText = findViewById(R.id.checkInDateEditText);
        EditText checkOutDateEditText = findViewById(R.id.checkOutDateEditText);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guestName = guestNameEditText.getText().toString().trim();
                String checkInDate = checkInDateEditText.getText().toString().trim();
                String checkOutDate = checkOutDateEditText.getText().toString().trim();

                if (checkInDate.isEmpty() || guestName.isEmpty() || checkOutDate.isEmpty()) {
                    Toast.makeText(ReservationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }


                boolean success = dbHelper.addReservation(guestName, checkInDate, checkOutDate);
                if (success) {
                    Toast.makeText(ReservationActivity.this, "Reservation Successful", Toast.LENGTH_SHORT).show();
                    guestNameEditText.setText("");
                    checkInDateEditText.setText("");
                    checkOutDateEditText.setText("");
                } else {
                    Toast.makeText(ReservationActivity.this, "Reservation Failed", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
