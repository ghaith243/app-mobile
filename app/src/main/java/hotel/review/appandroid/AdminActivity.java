package hotel.review.appandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Get all reservations for admin
        Cursor cursor = dbHelper.getAllReservations();

        if (cursor != null && cursor.getCount() > 0) {
            ArrayList<String> reservationList = new ArrayList<>();
            final ArrayList<Integer> reservationIds = new ArrayList<>();
            // Loop through the cursor to extract reservation data
            while (cursor.moveToNext()) {
                @SuppressLint("Range")
                int reservationId = cursor.getInt(cursor.getColumnIndex("id"));

                @SuppressLint("Range")
                String guestName = cursor.getString(cursor.getColumnIndex("guestName"));

                @SuppressLint("Range")
                String checkInDate = cursor.getString(cursor.getColumnIndex("checkInDate"));

                @SuppressLint("Range")
                String checkOutDate = cursor.getString(cursor.getColumnIndex("checkOutDate"));

                // Add reservation details to the list
                String reservationDetails = "Guest : " + guestName +" | "+ " Room : " +   " check in Date : " + checkInDate +" | "+ " check out Date : " + checkOutDate;
                reservationList.add(reservationDetails);
                reservationIds.add(reservationId);
            }

            // Set up the ListView with the reservation data
            ListView listView = findViewById(R.id.reservationsListView);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1, // Simple item layout
                    reservationList
            );
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Get the reservation ID for the clicked item
                    int reservationId = reservationIds.get(position);

                    // Delete the reservation from the database
                    boolean isDeleted = dbHelper.deleteReservation(reservationId);
                    if (isDeleted) {
                        Toast.makeText(AdminActivity.this, "Reservation deleted", Toast.LENGTH_SHORT).show();
                        // Refresh the activity after deletion
                        startActivity(new Intent(AdminActivity.this, AdminActivity.class));
                        finish();
                    } else {
                        Toast.makeText(AdminActivity.this, "Failed to delete reservation", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            // Handle the case when there are no reservations
            Toast.makeText(this, "No reservations found", Toast.LENGTH_SHORT).show();
        }
    }
}

