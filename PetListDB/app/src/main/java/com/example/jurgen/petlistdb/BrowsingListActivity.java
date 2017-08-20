package com.example.jurgen.petlistdb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jurgen.petlistdb.adapter.PetListAdapter;
import com.example.jurgen.petlistdb.db.PetManagementContract;
import com.example.jurgen.petlistdb.db.PetManagementDbHelper;
import com.example.jurgen.petlistdb.fragments.BrowsingListFragment;
import com.example.jurgen.petlistdb.model.Pet;

import java.util.ArrayList;

public class BrowsingListActivity extends AppCompatActivity {

    private static final String TAG = "BrowsingListActivity";

    private static final String EXTRA_PET_SPECIES = "species_list";

    public static Intent getStartIntent(Context context, String petSpecies) {
        Intent intent = new Intent(context, BrowsingListActivity.class);
        intent.putExtra(EXTRA_PET_SPECIES, petSpecies);

        return intent;
    }

    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing);
        setTitle("List of Pets");

        String petSpecies = getIntent().getStringExtra(EXTRA_PET_SPECIES);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            BrowsingListFragment firstFragment = BrowsingListFragment.newInstance(petSpecies);
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedpreferences.contains("Status")) {
            menu.findItem(R.id.Login).setVisible(false);

        }else{
            menu.findItem(R.id.Logout).setVisible(false);
        }
        menu.findItem(R.id.DeletePet).setVisible(false);
        menu.findItem(R.id.EditPet).setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Login:
                Intent intent = new Intent(BrowsingListActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;
            case R.id.Logout:
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                intent = new Intent(BrowsingListActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.AddPet:
                intent = new Intent(BrowsingListActivity.this, CreatePetActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
