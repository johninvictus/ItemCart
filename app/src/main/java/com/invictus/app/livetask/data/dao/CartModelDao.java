package com.invictus.app.livetask.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.invictus.app.livetask.data.model.CartModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by invictus on 11/29/17.
 */

@Dao
public interface CartModelDao {

    @Insert(onConflict = REPLACE)
    void addCart(CartModel model);

    @Query("UPDATE cart SET quantity = :quantity WHERE id = :userid")
    void updateCart(int quantity, long userid);
}
