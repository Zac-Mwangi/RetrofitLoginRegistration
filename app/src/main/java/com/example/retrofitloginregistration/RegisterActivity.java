package com.example.retrofitloginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password,email;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        reg = findViewById(R.id.signUp);

        reg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == reg){
            //Toast.makeText(getApplicationContext(), "reg", Toast.LENGTH_SHORT).show();
            final String reg_name = username.getText().toString();
            final String reg_email = email.getText().toString();
            final String reg_password = password.getText().toString();

            registerUser(reg_name,reg_email,reg_password);
        }
    }
    public void registerUser(String user_name,String user_email,String user_pass){
        //final String reg_username = username.getText().toString();
        //final String reg_email = email.getText().toString();
        //final String reg_password = password.getText().toString();

        //call retrofit
        //making api call
        Api api = ApiClient.getClient().create(Api.class);
        Call<Model> register = api.register(user_name,user_email,user_pass);

        register.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                //success
                if (response.body().getIsSuccess() == 1) {
                    Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Err", Toast.LENGTH_SHORT).show();
            }
        });
    }
}