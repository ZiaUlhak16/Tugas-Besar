package com.pemrogramanmobile.tugassbesar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView reg, home;
    EditText inputEmail, inputPassword;
    String Email, Password;
    Button btnLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });


        reg = (TextView) findViewById(R.id.BuatAkunBaru);
                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                    }
                });
    }

    private void cekLogin() {
        Email = inputEmail.getText().toString();
        Password = inputPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            home = (TextView) findViewById(R.id.button);
                                    home.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(MainActivity. this, HomeFragment.class));
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Login Gagal, Silahkan Cek Email atau Password yang anda masukkan salah", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}