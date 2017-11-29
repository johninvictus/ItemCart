package com.invictus.app.livetask.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by invictus on 11/29/17.
 */

@Entity(tableName = "cart", foreignKeys = @ForeignKey(entity = ItemsModel.class,
        parentColumns = "id",
        childColumns = "item_id",
        onDelete = ForeignKey.CASCADE))

public class CartModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;


    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "item_id")
    private long itemId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
