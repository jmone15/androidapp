package com.example.jurgen.petlistdb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

public class RegisterActivity extends AppCompatActivity {

    /*
    private BroadcastReceiver createUserResultBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String resultMsg = intent.getStringExtra(PetService.EXTRA_CREATE_USER_RESULT);

            if(resultMsg.equals("Created student. Server responded with status 200")) {
                Toast.makeText(RegisterActivity.this, resultMsg, Toast.LENGTH_SHORT).show();
                Intent goToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goToLogin);
                finish();
            }else{
                Toast.makeText(RegisterActivity.this, resultMsg, Toast.LENGTH_SHORT).show();
            }
        }
    };
    */
    //Start of Retrofit implementation
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("List of Pets");

        //Retrofit additions
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);

        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = ((TextView) findViewById(R.id.registarUsername)).getText().toString();
                String password = ((TextView) findViewById(R.id.registarPassword)).getText().toString();
                String confirmPass = ((TextView) findViewById(R.id.confirmPassword)).getText().toString();
                String firstName = ((TextView) findViewById(R.id.registarFirstName)).getText().toString();
                String lastName = ((TextView) findViewById(R.id.registarLastName)).getText().toString();

                if(confirmPass.equals(password)) {
                    if (confirmPass.length() >= 6) {
                        userApi.createUser(new User(userName, password, firstName, lastName))
                                .enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if (response.isSuccessful()) {

                                            Toast.makeText(RegisterActivity.this, "Created student", Toast.LENGTH_SHORT).show();
                                            Intent goToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(goToLogin);
                                            finish();

                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Insertation failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(RegisterActivity.this, "Server Failure", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password must have 6 or more characters", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Insertion failed", Toast.LENGTH_SHORT).show();
                }


                //insertUser(userName, password, confirmPass, firstName, lastName);


            }
        });

        findViewById(R.id.BackTologin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);

        IntentFilter createStudentResultIntentFilter = new IntentFilter(PetService.ACTION_CREATE_USER_RESULT);
        broadcastManager.registerReceiver(createUserResultBroadcastReceiver, createStudentResultIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.unregisterReceiver(createUserResultBroadcastReceiver);
    }

    private void insertUser(String userName, String password, String confirmPassword, String firstName, String lastName) {

        if(confirmPassword.equals(password)) {
            if(confirmPassword.length() >=6 ) {
                Intent intent = new Intent(this, PetService.class);

                intent.setAction(PetService.ACTION_CREATE_USER);

                intent.putExtra(PetService.EXTRA_USERNAME, userName);
                intent.putExtra(PetService.EXTRA_PASSWORD, password);
                intent.putExtra(PetService.EXTRA_FIRST_NAME, firstName);
                intent.putExtra(PetService.EXTRA_LAST_NAME, lastName);

                startService(intent);
            }else{
                Toast.makeText(RegisterActivity.this, "Password must have 6 or more characters", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(RegisterActivity.this, "Insertion failed", Toast.LENGTH_SHORT).show();
        }
    }
    */
}
