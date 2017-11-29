package com.invictus.app.livetask.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.invictus.app.livetask.data.AppDatabase;
import com.invictus.app.livetask.data.model.CartModel;
import com.invictus.app.livetask.data.model.ItemsModel;
import com.invictus.app.livetask.data.model.cartQuantity;

import java.util.List;

/**
 * Created by invictus on 11/29/17.
 */

public class ItemsListViewModel extends AndroidViewModel {

    private final LiveData<List<cartQuantity>> listLiveData;
    private AppDatabase appDatabase;

    public ItemsListViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        listLiveData = appDatabase.itemsModelDao().getItemsByCart();
    }


    public LiveData<List<cartQuantity>> getItemAndCartLiveData() {
        return listLiveData;
    }

    public void addData(ItemsModel model) {
        new saveAsyncTask(appDatabase).execute(model);
    }

    public void addToCart(cartQuantity model) {
        new cartSaveOrUpadeAsyncTask(appDatabase).execute(model);
    }

    private static class saveAsyncTask extends AsyncTask<ItemsModel, Void, Void> {
        private AppDatabase db;

        public saveAsyncTask(AppDatabase appDatabase) {
            this.db = appDatabase;
        }

        @Override
        protected Void doInBackground(ItemsModel... itemsModels) {
            db.itemsModelDao().addItem(itemsModels[0]);
            return null;
        }
    }

    private static class cartSaveOrUpadeAsyncTask extends AsyncTask<cartQuantity, Void, Void> {

        private AppDatabase db;

        public cartSaveOrUpadeAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(cartQuantity... cartModels) {

            if (cartModels[0].getItemCart() != 0) {
                CartModel model = new CartModel();
                int quan = cartModels[0].getItemCart() + 1;
                Log.e("xxxx", ""+ quan);
                model.setQuantity(quan);
                model.setItemId(cartModels[0].getId());
                model.setId(1);
                db.cartModelDao().updateCart(quan,cartModels[0].getId() );
            } else {
                CartModel model = new CartModel();
                model.setQuantity(12);
                model.setName(cartModels[0].getName());
                model.setItemId(cartModels[0].getId());
                db.cartModelDao().addCart(model);
            }

            return null;
        }
    }
}
