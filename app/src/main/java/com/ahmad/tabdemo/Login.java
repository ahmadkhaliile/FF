package com.ahmad.tabdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    private boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
        //Getting editor
        SharedPreferences.Editor editor = preferences.edit();

        //Puting the value false for loggedin
        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //Putting blank value to email
        editor.putString(Config.EMAIL_SHARED_PREF, "");
        editor.putString(Config.ID_SHARED_PREF, "");
        editTextEmail    = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin      = (Button)findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF,false);

        if(loggedIn){

            Intent intetnt = new Intent(Login.this,Profile.class);
            startActivity(intetnt);
            finish();
        }
    }

    private void login(){

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase(Config.LOGIN_Failure)){

                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();

                }else{
                    SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                    editor.putString(Config.EMAIL_SHARED_PREF, email);
                    editor.putString(Config.ID_SHARED_PREF, response);
                    editor.commit();
                   // Toast.makeText(Login.this,"ID = "+sharedPreferences.getString(Config.ID_SHARED_PREF,"HI"),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this,Profile.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put(Config.KEY_EMAIL,email);
                params.put(Config.KEY_PASSWORD,password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
