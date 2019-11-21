package dk.now.just.bogym.Adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import dk.now.just.bogym.Interface.ItemClickListener;

import java.util.List;

import dk.now.just.bogym.R;
import dk.now.just.bogym.ViewChallenge;

class RecyclerViewHolderBlog extends RecyclerView.ViewHolder
{
    public ImageView image;
    public TextView text;
    public TextView title;
    private dk.now.just.bogym.Interface.ItemClickListener itemClickListener;


    public RecyclerViewHolderBlog( View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.imgBlog);
        title = (TextView)itemView.findViewById(R.id.blogTitle);
        text = itemView.findViewById(R.id.blogBody);
    }

}

public class RecyclerViewAdapterBlog extends RecyclerView.Adapter<RecyclerViewHolderBlog> {

    private List<dk.now.just.bogym.Model.Blog> blogList;
    private Context context;
    public RecyclerViewAdapterBlog (List<dk.now.just.bogym.Model.Blog> blogList, Context context) {

        this.blogList = blogList;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerViewHolderBlog onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_blog,viewGroup,false);

        return new RecyclerViewHolderBlog(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderBlog recyclerViewHolderBlog, int i) {
        recyclerViewHolderBlog.image.setImageResource(blogList.get(i).getBlogImage_id());
        recyclerViewHolderBlog.title.setText(blogList.get(i).getBlogName());
        recyclerViewHolderBlog.text.setText(blogList.get(i).getBlogBody());

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
}
