package com.rehan.laundryapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class LoginActivity extends AppCompatActivity {
    EditText edUsername,edPassword;

    Button btnLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edUsername = (EditText) findViewById(R.id.edUsername);
        edPassword = (EditText) findViewById(R.id.edPassword) ;
        btnLogin = (Button) findViewById(R.id.btnLogin);

// event Handler
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edUsername.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Ussername tidak boleh kosong",Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(edPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this,"Password tidak boleh kosong",Toast.LENGTH_LONG).show();
                }
                else {Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", edUsername.getText().toString());
                     startActivity(intent);
                    finish();


                }
                // Toast.makeText(LoginActivity.this, "ondeh kanai pencet", Toast.LENGTH_LONG).show();
              //  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              //  intent.putExtra("username", edUsername.getText().toString());
               // startActivity(intent);
              //  finish();
            }
        });


}
}