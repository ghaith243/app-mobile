package hotel.review.appandroid;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private Context context;
    private List<Chambre> roomList;
    private List<Chambre> filteredRoomList;

    public RoomAdapter(Context context, List<Chambre> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Chambre room = roomList.get(position);

        holder.roomImage.setImageResource(room.getImageResource());
        holder.roomName.setText(room.getName());
        holder.roomPrice.setText("Prix: " + room.getPrice() + "€");

        holder.bookButton.setOnClickListener(v -> {
            // Intent to start ReservationActivity
            Intent intent = new Intent(context, ReservationActivity.class);

            // Optional: Pass any data if needed, like the room name
            intent.putExtra("ROOM_NAME", room.getName());

            // Start the ReservationActivity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        ImageView roomImage;
        TextView roomName, roomPrice;
        Button bookButton;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomImage = itemView.findViewById(R.id.roomImage);
            roomName = itemView.findViewById(R.id.roomName);
            roomPrice = itemView.findViewById(R.id.roomPrice);
            bookButton = itemView.findViewById(R.id.bookButton);
        }
    }
}