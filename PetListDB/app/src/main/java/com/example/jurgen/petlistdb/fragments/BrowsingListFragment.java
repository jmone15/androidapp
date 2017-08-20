package com.example.jurgen.petlistdb.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jurgen.petlistdb.BrowsingListActivity;
import com.example.jurgen.petlistdb.PetProfileActivity;
import com.example.jurgen.petlistdb.R;
import com.example.jurgen.petlistdb.adapter.PetListAdapter;
import com.example.jurgen.petlistdb.db.PetManagementContract;
import com.example.jurgen.petlistdb.model.Pet;

import java.util.ArrayList;

/**
 * Created by Jurgen on 5/20/2017.
 */

public class BrowsingListFragment extends Fragment {

    private static final String TAG = "BrowsingListFragment";

    private static final String ARG_PET_SPECIES = "species_list";

    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;

    private static final String[] PROJECTION = {
            PetManagementContract.Pet._ID,
            PetManagementContract.Pet.COLUMN_NAME_PETNAME,
            PetManagementContract.Pet.COLUMN_NAME_BREED,
            PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI
    };

    private static final String SORT_ORDER = PetManagementContract.Pet.COLUMN_NAME_PETNAME + " ASC";


    //private PetManagementDbHelper dbHelper;

    private ListView petView;
    private PetListAdapter ptAdapter;



    public static BrowsingListFragment newInstance(String petSpecies) {
        BrowsingListFragment fragment = new BrowsingListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PET_SPECIES, petSpecies);
        fragment.setArguments(args);
        return fragment;
    }

    private String petSpecies;

    public BrowsingListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getArguments().isEmpty()) {
            petSpecies = getArguments().getString(ARG_PET_SPECIES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_browsing_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        petView = (ListView) getActivity().findViewById(R.id.listview_pets);
        populateListView(petSpecies);
    }

    private void populateListView(String speciesString){

        Log.d(TAG, "Populate view: Display data in list view");


        String selection = PetManagementContract.Pet.COLUMN_NAME_BREED+" = ?";
        String[] selectionArgs = { speciesString };

        Cursor cursor = getActivity().getContentResolver().query(
                PetManagementContract.Pet.CONTENT_URI,
                null,
                selection,
                selectionArgs,
                SORT_ORDER
        );

        ArrayList<Pet> listPetData = new ArrayList<>();
        while (cursor.moveToNext()){
            listPetData.add(new Pet
                    (
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getInt(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_CHIP_ID)),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11),
                            cursor.getString(12),
                            cursor.getString(13),
                            cursor.getString(14),
                            cursor.getInt(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI))
                    ));
            Log.d(TAG, "Give me the IMAGE URI --> \n"
                    +cursor.getString(0)+"\n"
                    +cursor.getString(1)+"\n"
                    +cursor.getString(2)+"\n"
                    +cursor.getString(3)+"\n"
                    +cursor.getString(4)+"\n"
                    +cursor.getString(5)+"\n"
                    +cursor.getString(6)+"\n"
                    +cursor.getInt(7)+"\n"
                    +cursor.getString(8)+"\n"
                    +cursor.getString(9)+"\n"
                    +cursor.getString(10)+"\n"
                    +cursor.getString(11)+"\n"
                    +cursor.getString(12)+"\n"
                    +cursor.getString(13)+"\n"
                    +cursor.getString(14)+"\n"
                    +cursor.getInt(cursor.getColumnIndexOrThrow(PetManagementContract.Pet.COLUMN_NAME_IMAGE_URI))+"\n"
            );
        }
        cursor.close();

        ptAdapter = new PetListAdapter(getActivity().getApplicationContext(), listPetData);

        if(!ptAdapter.isEmpty()) {

            if(petView != null) {
                petView.setAdapter(ptAdapter);

                petView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        String statusValue = (sharedpreferences.getString("Status", ""));

                        if (statusValue.equals("Loggedin")) {
                            String tag = view.getTag().toString();

                            Intent intent = new Intent(getActivity(), PetProfileActivity.class);
                            intent.putExtra("tag", tag);
                            startActivity(intent);
                        } else {

                            Toast.makeText(getActivity().getApplicationContext(), "Access denied, you need to log in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }else{
            petView.setVisibility(View.GONE);
        }
    }

}
