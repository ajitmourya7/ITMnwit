package co.iam_infinity.www.itmnwit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    List<BlogData> blogData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        bar.setTitle(Html.fromHtml("<font color='#000000'>ITM nwit </font>"));
        bar.setElevation(0);
        gridView = (GridView) findViewById(R.id.listView_pic);
        blogData = new ArrayList<>();

        getdata();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //listViewCallingMethods(position);
                List<String> data = new ArrayList<String>();
                data.add(blogData.get(position).getTitle());
                data.add(blogData.get(position).getImage());
                data.add(blogData.get(position).getContent());
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putStringArrayListExtra("data", (ArrayList<String>) data);
                startActivity(intent);
            }
        });
    }

    public void getdata(){
        final FirebaseDatabase fd = FirebaseDatabase.getInstance();
        DatabaseReference myref = fd.getReference().child("BLOG");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        blogData.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            BlogData a = ds.getValue(BlogData.class);
            blogData.add(a);
        }
        BlogAdapter adapter = new BlogAdapter(MainActivity.this,blogData);
        gridView.setAdapter(adapter);
    }

}
