package dk.now.just.bogym.Adapter;


import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import dk.now.just.bogym.Model.Blog;
import dk.now.just.bogym.Model.Challenge;
import dk.now.just.bogym.R;
import dk.now.just.bogym.ViewChallenge;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView image;
    public TextView text;
    private dk.now.just.bogym.Interface.ItemClickListener itemClickListener;
    public RecyclerViewHolder( View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.ex_img);
        text = (TextView)itemView.findViewById(R.id.ex_name);
        itemView.setOnClickListener(this);
    }
    public void setItemClickListener(dk.now.just.bogym.Interface.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition());
    }
}
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<dk.now.just.bogym.Model.Challenge> challengesList;
    private Context context;
    public RecyclerViewAdapter(List<Challenge> challengesList, Context context) {
        this.challengesList = challengesList;
        this.context = context;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);
        return new RecyclerViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
    holder.image.setImageResource(challengesList.get(position).getImage_id());
    holder.text.setText(challengesList.get(position).getName());
        holder.setItemClickListener(new dk.now.just.bogym.Interface.ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;
                intent = new Intent(context, ViewChallenge.class);
                intent.putExtra("image_id", challengesList.get(position).getImage_id());
                intent.putExtra("name",challengesList.get(position).getName());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return challengesList.size();
    }
}
