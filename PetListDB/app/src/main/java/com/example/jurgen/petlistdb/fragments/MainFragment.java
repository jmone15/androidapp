package com.example.jurgen.petlistdb.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.jurgen.petlistdb.BrowsingListActivity;
import com.example.jurgen.petlistdb.MainActivity;
import com.example.jurgen.petlistdb.R;
import com.example.jurgen.petlistdb.model.Pet;

import java.util.List;


/**
 * Created by Jurgen on 5/20/2017.
 */

public class MainFragment extends Fragment {

    private ImageButton dogs;
    private ImageButton cats;
    private ImageButton others;

    public interface OnFragmentInteractionListener {
        void onSpeciesSelected(String petSpecies);
    }

    private OnFragmentInteractionListener mListener;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dogs = (ImageButton) getActivity().findViewById(R.id.dogs);
        cats = (ImageButton) getActivity().findViewById(R.id.cats);
        others = (ImageButton) getActivity().findViewById(R.id.others);

        dogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String species = "Dog";
                mListener.onSpeciesSelected(species);
            }
        });

        cats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String species = "Cat";
                mListener.onSpeciesSelected(species);
            }
        });

        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String species = "Other";
                mListener.onSpeciesSelected(species);
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
