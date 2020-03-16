package com.english.englishproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<DataSetList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        arrayList = new ArrayList<DataSetList>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/watch?v=1-39sVnAWYs&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=s01ByTMTDik&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=3-ANkhLd_1c&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=JGXK_99nc5s&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=BT3JbwlpQxU&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=B1ed-pfqdZg&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=TXXO9XvW_Kc&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=BvtG-myN8Lo&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=yJXd1jM7HGU&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=SkRYlNu_W7g&feature=youtu.be");
        arrayList.add(dataSetList);

        dataSetList = new DataSetList("https://www.youtube.com/watch?v=3zJhoTcb9y4&feature=youtu.be");
        arrayList.add(dataSetList);

//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=F5WDeNw9_Ek");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=vNTiUh72lFo");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=3Lj0_D1FZ3g");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=F1P4bBy00vg");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=rWmd39oMZws");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=IfsMvzf5yHA");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=eByGdzdn_rw");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=oBYfoG5wBP8");
//        arrayList.add(dataSetList);
//
//        dataSetList = new DataSetList("https://www.youtube.com/watch?v=YzInQMQ7hWE");
//        arrayList.add(dataSetList);

        youtubeAdapter youtube_Adapter = new youtubeAdapter(arrayList,getApplicationContext());
        recyclerView.setAdapter(youtube_Adapter);

    }
}
