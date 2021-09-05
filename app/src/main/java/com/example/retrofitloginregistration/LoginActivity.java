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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText login_username,login_password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==signIn){
            Login();
        }
    }
    public void Login(){
        final String log_username = login_username.getText().toString();
        final String log_password = login_password.getText().toString();

        Api api = ApiClient.getClient().create(Api.class);
        Call<Model> login = api.login(log_username,log_password);
        login.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                //success
                if (response.body().getIsSuccess() == 1) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Err", Toast.LENGTH_SHORT).show();
            }
        });
    }
}