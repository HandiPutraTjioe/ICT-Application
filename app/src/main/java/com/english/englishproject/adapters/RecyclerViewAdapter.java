package com.english.englishproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.english.englishproject.R;
import com.english.englishproject.activities.BooksActivity;
import com.english.englishproject.model.EnglishBooks;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<EnglishBooks> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<EnglishBooks> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.activity_list_books,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BooksActivity.class);
                i.putExtra("book_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("book_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("book_category",mData.get(viewHolder.getAdapterPosition()).getCategory());
                i.putExtra("book_penerbit",mData.get(viewHolder.getAdapterPosition()).getPenerbit());
                i.putExtra("book_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("book_img",mData.get(viewHolder.getAdapterPosition()).getImgUrl());

                mContext.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_penerbit.setText(mData.get(position).getPenerbit());
        holder.tv_category.setText(mData.get(position).getCategory());
        Glide.with(mContext).load(mData.get(position).getImgUrl()).apply(option).into(holder.img_thumbnail);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_rating;
        TextView tv_penerbit;
        TextView tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.book_name);
            tv_category = itemView.findViewById(R.id.category);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_penerbit = itemView.findViewById(R.id.penerbit);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
