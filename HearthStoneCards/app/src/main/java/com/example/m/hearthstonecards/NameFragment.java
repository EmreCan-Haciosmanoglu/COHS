package com.example.m.hearthstonecards;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NameFragment extends Fragment implements ListItemClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_name,container,false);
        NameAdapter recyclerViewImageAdapter=new NameAdapter(RecyclerActivity.mContext,this);
        RecyclerView recyclerView=result.findViewById(R.id.nameRecycler);
        recyclerView.setAdapter(recyclerViewImageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.mContext,LinearLayoutManager.VERTICAL,false));

        return result;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent =new Intent(getActivity(),DetailActivity.class);
        intent.putExtra("Name",Card.cards.get(clickedItemIndex).getName());
        intent.putExtra("ID",Card.cards.get(clickedItemIndex).getID());
        intent.putExtra("Rarity",Card.cards.get(clickedItemIndex).getRarity());
        intent.putExtra("Class",Card.cards.get(clickedItemIndex).getCardClass());
        startActivity(intent);

    }
}
