package com.example.posts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<TextRow> mDataset;

    public Context parent;

    public MyAdapter(List<TextRow> myDataset, Context parent) {
            mDataset = myDataset;

            this.parent = parent;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(TextView v) {
            super(v);

            textView = v;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
            Context context = parent.getContext();
            TextView v = (TextView) LayoutInflater.from(context)
                    .inflate(R.layout.item_post, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset.get(position).name);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent, UploadText.class);
                intent.putExtra("name", mDataset.get(position).name);
                intent.putExtra("text", mDataset.get(position).text);
                parent.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}