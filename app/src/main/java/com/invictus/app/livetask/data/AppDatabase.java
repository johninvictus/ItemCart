package com.invictus.app.livetask.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.invictus.app.livetask.data.dao.CartModelDao;
import com.invictus.app.livetask.data.dao.ItemsModelDao;
import com.invictus.app.livetask.data.model.CartModel;
import com.invictus.app.livetask.data.model.ItemsModel;

/**
 * Created by invictus on 11/29/17.
 */

@Database(entities = {ItemsModel.class, CartModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "shop_db")
                    .build();

        return INSTANCE;
    }

    public abstract ItemsModelDao itemsModelDao();
    public abstract CartModelDao cartModelDao();
}
