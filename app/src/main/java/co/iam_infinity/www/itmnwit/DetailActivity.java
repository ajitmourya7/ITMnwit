package co.iam_infinity.www.itmnwit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView_image;
    TextView textView_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent=getIntent();
        ArrayList<String> data = intent.getStringArrayListExtra("data");
        String title = data.get(0);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        bar.setTitle(Html.fromHtml("<font color='#000000'>"+title+"</font>"));
        bar.setElevation(0);

        imageView_image = (ImageView)findViewById(R.id.image);
        textView_content = (TextView)findViewById(R.id.content);

        Glide.with(DetailActivity.this)
                .load(data.get(1))
                .centerCrop()
                .into(imageView_image);
        textView_content.setText(data.get(2));
    }
}
