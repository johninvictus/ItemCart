package com.invictus.app.livetask.data.model;

/**
 * Created by invictus on 11/29/17.
 */

public class cartQuantity extends ItemsModel {
    public int cartQuantity;

    public int getItemCart() {
        return cartQuantity;
    }

    public void setItemCart(int itemCart) {
        this.cartQuantity = itemCart;
    }
}
