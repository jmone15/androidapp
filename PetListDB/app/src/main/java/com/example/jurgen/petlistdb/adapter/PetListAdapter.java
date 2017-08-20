package com.example.jurgen.petlistdb.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jurgen.petlistdb.R;
import com.example.jurgen.petlistdb.model.Pet;

import java.util.List;

/**
 * Created by Jurgen on 4/17/2017.
 */

public class PetListAdapter extends BaseAdapter {

    private Context myPetContext;
    private List<Pet> myPetList;

    public PetListAdapter(Context myPetContext, List<Pet> myPetList) {
        this.myPetContext = myPetContext;
        this.myPetList = myPetList;
    }

    @Override
    public int getCount() {
        return myPetList.size();
    }

    @Override
    public Object getItem(int position) {
        return myPetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(myPetContext, R.layout.list_pets, null);
        //add data
        ImageView pImage = (ImageView) view.findViewById(R.id.petImage);
        TextView pName = (TextView) view.findViewById(R.id.pet_name);
        TextView pBreed = (TextView) view.findViewById(R.id.pet_breed);

        pImage.setImageResource(myPetList.get(position).getImageUri());
        pName.setText(myPetList.get(position).getName());
        pBreed.setText(myPetList.get(position).getBreed());

        view.setTag(myPetList.get(position).getId());

        return view;
    }
}
