package com.dpu.bm.uykuapnesitespitsistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    private TextView halaKayitOlmadinizMi;
    private Button girisYapButton;
    private FirebaseAuth mAuth;
    private EditText mailKutusu,sifreKutusu;
    private String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        halaKayitOlmadinizMi = findViewById(R.id.KayitOlmadinizMi);
        girisYapButton = findViewById(R.id.GirisButton);
        mailKutusu = findViewById(R.id.MailKutusu);
        sifreKutusu = findViewById(R.id.SifreKutusu);

        mAuth = FirebaseAuth.getInstance();
        girisYapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mailKutusu.getText().toString();
                password=sifreKutusu.getText().toString();
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Snackbar.make(view,"Tüm alanları doldurunuz!!",Snackbar.LENGTH_LONG).show();
                }else{
                    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        Snackbar.make(view,"Email format hatası!",Snackbar.LENGTH_LONG).show();
                        return;
                    }

                    else if(password.length()<6){
                        Snackbar.make(view,"Şifre altı karakterden kısa olamaz!",Snackbar.LENGTH_LONG).show();
                        return;
                    }
                    else{
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, (OnCompleteListener<AuthResult>) task -> {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.e("Başarılı", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(new Intent(LoginActivity.this,HomePageActivity.class));
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.e("Başarısız", "signInWithEmail:failure");
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                }

            }
        });
        halaKayitOlmadinizMi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }
}