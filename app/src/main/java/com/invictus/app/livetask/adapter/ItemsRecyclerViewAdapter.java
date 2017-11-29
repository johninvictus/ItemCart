package com.invictus.app.livetask.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.invictus.app.livetask.R;
import com.invictus.app.livetask.data.model.cartQuantity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by invictus on 11/29/17.
 */

public class ItemsRecyclerViewAdapter extends RecyclerView.Adapter<ItemsRecyclerViewAdapter.ViewHolder> {

    private List<cartQuantity> list = new ArrayList<>();
    private View.OnLongClickListener longClickListener;

    public ItemsRecyclerViewAdapter(List<cartQuantity> list, View.OnLongClickListener longClickListener) {
        this.list = list;
        this.longClickListener = longClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cartQuantity cartQuantity = list.get(position);

        holder.itemView.setOnLongClickListener(longClickListener);
        holder.itemView.setTag(cartQuantity);

        holder.populate(cartQuantity);
    }

    public void addItems(List<cartQuantity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title_tv;
        TextView price_tv;
        TextView cart_quantity_tv;

        public ViewHolder(View view) {
            super(view);


            title_tv = view.findViewById(R.id.title_tv);
            price_tv = view.findViewById(R.id.price_tv);
            cart_quantity_tv = view.findViewById(R.id.cart_quantity_tv);
        }

        public void populate(cartQuantity cartQuantity) {
            title_tv.setText(cartQuantity.getName());
            price_tv.setText(String.valueOf(cartQuantity.getPrice()));
            cart_quantity_tv.setText(String.valueOf(cartQuantity.getItemCart()));
        }
    }
}
