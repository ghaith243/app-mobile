package hotel.review.appandroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hotelDatabase.db";
    private static final int DATABASE_VERSION = 5;

    //table user
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    //table reservation
    private static final String TABLE_RESERVATIONS = "reservations";
    private static final String COLUMN_RESERVATION_ID = "id";
    private static final String COLUMN_GUEST_NAME = "guestName";
    private static final String COLUMN_CHECK_IN_DATE = "checkInDate";
    private static final String COLUMN_CHECK_OUT_DATE = "checkOutDate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(createTable);

        String createReservationsTable = "CREATE TABLE " + TABLE_RESERVATIONS + " (" +
                COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                COLUMN_CHECK_IN_DATE + " TEXT NOT NULL, " +
                COLUMN_CHECK_OUT_DATE + " TEXT NOT NULL)";
        db.execSQL(createReservationsTable);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS reservations");
        onCreate(db);
    }

    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isLoggedIn = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isLoggedIn;
    }

    public boolean addReservation(String guestName, String checkInDate, String checkOutDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GUEST_NAME, guestName);
        values.put(COLUMN_CHECK_IN_DATE, checkInDate);
        values.put(COLUMN_CHECK_OUT_DATE, checkOutDate);


        long result = db.insert(TABLE_RESERVATIONS, null, values);
        db.close();
        return result != -1;
    }

    public Cursor getAllReservations() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_RESERVATIONS;
        return db.rawQuery(query, null); // Return all reservations
    }

    public boolean deleteReservation(int reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("reservations", "id = ?", new String[]{String.valueOf(reservationId)});
        db.close();
        return rowsAffected > 0;
    }
}
