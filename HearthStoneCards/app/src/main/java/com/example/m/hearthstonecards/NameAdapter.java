package com.example.m.hearthstonecards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NameAdapter extends RecyclerView.Adapter<NameViewHolder> {
    Context mContext;
    private ListItemClickListener mOnClickListener;
    public NameAdapter(Context context,ListItemClickListener listener){
        mOnClickListener=listener;
        mContext=context;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.name_list_item,viewGroup,false);
        return new NameViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder, int i) {
        nameViewHolder.setData(i);

    }

    @Override
    public int getItemCount() {
        return Card.cards.size();
    }
}
class NameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView image;
    public TextView cardName,rowNumber;
    private  ListItemClickListener mListener;
    private ConstraintLayout constraintLayout;

    public NameViewHolder(@NonNull View itemView, ListItemClickListener listener) {
        super(itemView);
        image=itemView.findViewById(R.id.imageView2);
        mListener = listener;
        cardName=itemView.findViewById(R.id.cardName);
        rowNumber=itemView.findViewById(R.id.rowNumber);
        constraintLayout=itemView.findViewById(R.id.nameLayout);
        constraintLayout.setOnClickListener(this);
    }
    public void setData(int i){
        String id=Card.cards.get(i).getID();
        cardName.setText(Card.cards.get(i).getName());
        rowNumber.setText(String.valueOf(i));
        String URL="https://art.hearthstonejson.com/v1/orig/"+id+".png";
        Picasso.get().load(URL).into(image);
    }


    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);

    }
}
