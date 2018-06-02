package co.iam_infinity.www.itmnwit;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BlogAdapter extends ArrayAdapter<BlogData> {
    private Activity context;
    private List<BlogData> infoList;

    public BlogAdapter(Activity context, List<BlogData> profileList)
    {
        super(context, R.layout.dataview, profileList);
        this.context=context;
        this.infoList = profileList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.dataview,null,true);

        ImageView image = (ImageView) listViewItem.findViewById(R.id.image);
        TextView title = (TextView)listViewItem.findViewById(R.id.title);
        TextView content = (TextView)listViewItem.findViewById(R.id.content);
        BlogData dataInfo = infoList.get(position);

        Glide.with(context)
                .load(dataInfo.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .thumbnail(0.1f)
                .centerCrop()
                .into(image);
        title.setText(dataInfo.getTitle());
        content.setText(dataInfo.getContent());

        return listViewItem;
    }

}
