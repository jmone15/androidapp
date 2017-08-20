package com.example.jurgen.petlistdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jurgen.petlistdb.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "UserPreferences" ;
    public SharedPreferences sharedpreferences;

    /*
    private BroadcastReceiver getAllUsersResultBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String studentResults = intent.getStringExtra(PetService.EXTRA_USER_RESULT);
            Toast.makeText(LoginActivity.this, studentResults, Toast.LENGTH_SHORT).show();
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            if(studentResults.equals("success")) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Status", "Loggedin");
                editor.apply();

                Intent loggedIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loggedIntent);
                finish();
            }else{
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Status", "NotLoggedIn");
                editor.apply();
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    };
    */
    //Start of Retrofit implementation
    private UserApi userApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("List of Pets");

        //Retrofit additions
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String username = ((TextView) findViewById(R.id.loginUsername)).getText().toString();
                String password = ((TextView) findViewById(R.id.loginPassword)).getText().toString();
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "One of the fields is empty", Toast.LENGTH_SHORT).show();
                }else {
                    //authenticateUser(username, password);
                    userApi.getUser(username, password)
                            .enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    if(response.isSuccessful()){

                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString("Status", "Loggedin");
                                        editor.apply();

                                        Intent loggedIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(loggedIntent);
                                        finish();
                                    }else{
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString("Status", "NotLoggedIn");
                                        editor.apply();
                                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Toast.makeText(LoginActivity.this, "Server Failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        findViewById(R.id.goToRegister).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
              }
        });
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter getStudentsResultIntentFilter = new IntentFilter(PetService.ACTION_GET_USERS_RESULT);
        broadcastManager.registerReceiver(getAllUsersResultBroadcastReceiver, getStudentsResultIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(getAllUsersResultBroadcastReceiver);
    }

    private void authenticateUser(String username, String password){
        Intent intent = new Intent(this, PetService.class);
        intent.setAction(PetService.ACTION_GET_USERS);

        intent.putExtra(PetService.EXTRA_USERNAME, username);
        intent.putExtra(PetService.EXTRA_PASSWORD, password);

        startService(intent);
    }
    */
}
