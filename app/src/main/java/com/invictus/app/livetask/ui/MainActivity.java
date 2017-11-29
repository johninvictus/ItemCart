package com.invictus.app.livetask.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.invictus.app.livetask.R;
import com.invictus.app.livetask.adapter.ItemsRecyclerViewAdapter;
import com.invictus.app.livetask.data.model.CartModel;
import com.invictus.app.livetask.data.model.ItemsModel;
import com.invictus.app.livetask.data.model.cartQuantity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    RecyclerView recyclerView;
    private ItemsListViewModel viewModel;
    private ItemsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.items_recycler);

        adapter = new ItemsRecyclerViewAdapter(new ArrayList<cartQuantity>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        viewModel = viewModel = ViewModelProviders.of(this).get(ItemsListViewModel.class);


        viewModel.getItemAndCartLiveData().observe(MainActivity.this, new Observer<List<cartQuantity>>() {
            @Override
            public void onChanged(@Nullable List<cartQuantity> list) {
                adapter.addItems(list);
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        cartQuantity itemsModel = (cartQuantity) v.getTag();
        viewModel.addToCart(itemsModel);
        return true;
    }


    public void newItem(View view) {
        ItemsModel model = new ItemsModel();
        model.setPrice(200);
        model.setName("King");

        viewModel.addData(model);
    }


}
