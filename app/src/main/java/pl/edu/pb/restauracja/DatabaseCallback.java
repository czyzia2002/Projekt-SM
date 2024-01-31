package pl.edu.pb.restauracja;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pl.edu.pb.restauracja.DatabaseInstance;

public class DatabaseCallback extends RoomDatabase.Callback {
    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);
        // Handle onCreate
        Log.d("DatabaseCallback", "onCreate called");
        DatabaseInstance.insertInitialData(DatabaseInstance.getInstance(DatabaseInstance.getAppContext()).restaurantDao(), db);
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
        super.onOpen(db);
        // Handle onOpen
        Log.d("DatabaseCallback", "onOpen called");

    }
}
