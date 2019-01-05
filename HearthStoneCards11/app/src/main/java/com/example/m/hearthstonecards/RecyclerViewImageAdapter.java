package com.example.m.hearthstonecards;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class RecyclerViewImageAdapter extends RecyclerView.Adapter<RecyclerViewImageHolder> {
    Context mContext;
    private ListItemClickListener mOnClickListener;
    public RecyclerViewImageAdapter(@NonNull Context context,ListItemClickListener listener){
        mContext=context;
        mOnClickListener=listener;

    }

    @NonNull
    @Override
    public RecyclerViewImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_list_item,viewGroup,false);
        return new RecyclerViewImageHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewImageHolder recyclerViewImageHolder, int i) {
        recyclerViewImageHolder.setData(i);

    }

    @Override
    public int getItemCount() {
        return Card.cards.size();
    }
}
class RecyclerViewImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView image;
    private  ListItemClickListener mListener;

    public RecyclerViewImageHolder(@NonNull View itemView, ListItemClickListener listener) {
        super(itemView);
        image=itemView.findViewById(R.id.imageView);
        mListener = listener;
        image.setOnClickListener(this);
    }
    public void setData(int i) {
        Card clickedCard =Card.cards.get(i);
        Picasso.get().load(clickedCard.getImgURL()).into(image);
    }

    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);
    }
}