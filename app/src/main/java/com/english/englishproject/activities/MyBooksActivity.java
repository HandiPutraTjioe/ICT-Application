package com.english.englishproject.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.english.englishproject.R;
import com.english.englishproject.adapters.RecyclerViewAdapter;
import com.english.englishproject.model.EnglishBooks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyBooksActivity extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/HandiPutraTjioe/58e4b94e9b8bb6dc43ffa62eb22e23af/raw/80e7375b7d6b23b545b84766e9543e6709d4421f/book.json";
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<EnglishBooks> lstBook ;
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);

        lstBook = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest() {
        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        EnglishBooks book = new EnglishBooks();
                        book.setName(jsonObject.getString("name"));
                        book.setDescription(jsonObject.getString("description"));
                        book.setRating(jsonObject.getString("Rating"));
                        book.setCategory(jsonObject.getString("category"));
                        book.setPenerbit(jsonObject.getString("penerbit"));
                        book.setImgUrl(jsonObject.getString("img"));
                        lstBook.add(book);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(lstBook);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(MyBooksActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<EnglishBooks> lstBook){
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstBook);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
