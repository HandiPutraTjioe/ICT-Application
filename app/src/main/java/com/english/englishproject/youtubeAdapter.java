package com.english.englishproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class youtubeAdapter extends RecyclerView.Adapter<youtubeHolder> {

    ArrayList<DataSetList> arrayList;
    Context context;

    public youtubeAdapter(ArrayList<DataSetList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public youtubeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_course_per_row,viewGroup,false);
        return new youtubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull youtubeHolder youtubeHolder, int i) {
        final DataSetList current = arrayList.get(i);
        youtubeHolder.webView.loadUrl(current.getLink());
        youtubeHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,VideoFullscreenActivity.class);
                intent.putExtra("link",current.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
