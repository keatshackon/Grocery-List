package com.keatssalazar.waitlist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keatssalazar.waitlist.data.GrocerylistContract;

public class GroceryListAdapter extends RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder> {


    private Context mContext;
    private Cursor mCursor;

    public GroceryListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }


    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grocery_list_item, parent, false);
        return new GroceryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {

        if (!(mCursor.moveToPosition(position))) return;

        String itemName = mCursor.getString(mCursor.getColumnIndex(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME));
        float weight = mCursor.getFloat(mCursor.getColumnIndex(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT));
        int sNumber = position + 1;

        long id =mCursor.getLong(mCursor.getColumnIndex(GrocerylistContract.GrocerylistEntry._ID));

        holder.itemView.setTag(id);

        holder.itemName.setText(itemName);
        holder.weight.setText(String.valueOf(weight));
        holder.sNumber.setText(String.valueOf(sNumber));


    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if(newCursor != null){
            this.notifyDataSetChanged();
        }

    }

    static class GroceryViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView weight;
        TextView sNumber;

        public GroceryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.items);
            weight = (TextView) itemView.findViewById(R.id.weight_text_view);
            sNumber = (TextView) itemView.findViewById(R.id.item_number);
        }
    }

}
