package com.english.englishproject.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.english.englishproject.R;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

//        getSupportActionBar().hide();
        // Recieve data

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name  = bundle.getString("book_name");
            String description = bundle.getString("book_description");
            String penerbit = bundle.getString("book_penerbit") ;
            String category = bundle.getString("book_category");
            String rating = bundle.getString("book_rating") ;
            String image_url = bundle.getString("book_img") ;

            CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
            collapsingToolbarLayout.setTitleEnabled(true);

            TextView tv_name = findViewById(R.id.aa_book_name);
            TextView tv_penerbit = findViewById(R.id.aa_penerbit);
            TextView tv_category = findViewById(R.id.aa_category) ;
            TextView tv_description = findViewById(R.id.aa_description) ;
            TextView tv_rating  = findViewById(R.id.aa_rating) ;
            ImageView img = findViewById(R.id.aa_thumbnail);

            // setting values to each view

            tv_name.setText(name);
            tv_category.setText(category);
            tv_description.setText(description);
            tv_rating.setText(rating);
            tv_penerbit.setText(penerbit);

            collapsingToolbarLayout.setTitle(name);

            // ini views

            RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


            // set image using Glide
            Glide.with(this).load(image_url).apply(requestOptions).into(img);
        }


    }
}
