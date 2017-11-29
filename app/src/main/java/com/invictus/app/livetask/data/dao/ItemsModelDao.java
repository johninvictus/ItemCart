package com.invictus.app.livetask.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import com.invictus.app.livetask.data.model.ItemsModel;
import com.invictus.app.livetask.data.model.cartQuantity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by invictus on 11/29/17.
 */
@Dao
public interface ItemsModelDao {

    @Query("select * from items")
    LiveData<List<ItemsModel>> getAllItems();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("select items.id, items.name, items.price, cart.quantity as cartQuantity " +
            "FROM items " +
            "LEFT JOIN cart ON cart.item_id = items.id")
    LiveData<List<cartQuantity>> getItemsByCart();

    @Insert(onConflict = REPLACE)
    void addItem(ItemsModel model);

    @Update
    void updateItem(ItemsModel model);

    @Delete
    void deleteItem(ItemsModel model);


}
