package com.example.jurgen.petlistdb;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jurgen.petlistdb.model.User;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by jmone on 5/8/17.
 */

public class PetService extends IntentService {


    private static final String LOG_TAG = "PetService";

    public static final String ACTION_CREATE_USER = "com.example.jurgen.petlistdb.CREATE_USER";
    public static final String ACTION_GET_USERS = "com.example.jurgen.petlistdb.GET_USERS";

    public static final String ACTION_CREATE_USER_RESULT = "com.example.jurgen.petlistdb.CREATE_USER_RESULT";
    public static final String ACTION_GET_USERS_RESULT = "com.example.jurgen.petlistdb.GET_USERS_RESULT";

    public static final String EXTRA_USERNAME = "user.name";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_FIRST_NAME = "first.name";
    public static final String EXTRA_LAST_NAME = "last.name";



    public static final String EXTRA_CREATE_USER_RESULT = "create.user.result";
    public static final String EXTRA_USER_RESULT = "user.result";

    private static final String GET_USER_URL = "http://hodor.ait.gr:8080/myPets/api/user/";
    private static final String CREATE_USER_URL = "http://hodor.ait.gr:8080/myPets/api/user/";


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public PetService() {
        super("Pet service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        if (ACTION_CREATE_USER.equals(action)) {
            createUser(intent);
        } else if (ACTION_GET_USERS.equals(action)) {
            getUser(intent);
        } else {
            throw new UnsupportedOperationException("No implementation for action " + action);
        }
    }

   private void createUser(Intent intent){

       try{

           URL url = new URL(CREATE_USER_URL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setReadTimeout(10000 /* milliseconds */);
           conn.setConnectTimeout(15000 /* milliseconds */);
           conn.setRequestMethod("POST");
           conn.setDoInput(true);
           conn.setDoOutput(true);

           conn.addRequestProperty("Content-Type", "application/json");

           String password = intent.getStringExtra(EXTRA_PASSWORD);
           String userName = intent.getStringExtra(EXTRA_USERNAME);
           String firstName = intent.getStringExtra(EXTRA_FIRST_NAME);
           String lastName = intent.getStringExtra(EXTRA_LAST_NAME);

           User user = new User();
           user.setUserName(userName);
           user.setPassword(password);
           user.setFirstName(firstName);
           user.setLastName(lastName);


           String userJson = new Gson().toJson(user);

           Log.d(LOG_TAG, userJson);
           Log.d(LOG_TAG, CREATE_USER_URL);

           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
           writer.write(userJson);
           writer.flush();
           writer.close();

           conn.getOutputStream().close();


           // Starts the post
           conn.connect();

           int response = conn.getResponseCode();

           Log.d(LOG_TAG, "The response is: " + response);

           Intent resultIntent = new Intent(ACTION_CREATE_USER_RESULT);
           resultIntent.putExtra(EXTRA_CREATE_USER_RESULT, "Created student. Server responded with status " + response);

           LocalBroadcastManager.getInstance(this).sendBroadcast(resultIntent);

       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    private void getUser(Intent intent) {
        InputStream is = null;

        String password = intent.getStringExtra(EXTRA_PASSWORD);
        String userName = intent.getStringExtra(EXTRA_USERNAME);

        try {
            URL url = new URL("http://hodor.ait.gr:8080/myPets/api/user/"+userName+"/"+password);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();

            int response = conn.getResponseCode();
            Log.d(LOG_TAG, "The response is: " + response);
            //is = conn.getInputStream();

            // Convert the InputStream into a bitmap
            //String result = convertStreamToString(is);
            Intent resultIntent = new Intent(ACTION_GET_USERS_RESULT);

            if(response == 200) {

                resultIntent.putExtra(EXTRA_USER_RESULT, "success");
            }else{
                resultIntent.putExtra(EXTRA_USER_RESULT, "failure");
            }

            LocalBroadcastManager.getInstance(this).sendBroadcast(resultIntent);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception fetching students", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Exception closing stream", e);
                }
            }
        }
    }

    private String convertStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }

        return baos.toString();
    }

}

