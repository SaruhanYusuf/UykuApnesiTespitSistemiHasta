package com.dpu.bm.uykuapnesitespitsistemi;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText mailE, sifreE;
    private String mymail, mypassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button uyeOlButton = (Button) findViewById(R.id.UyeOlButton);
        mailE = findViewById(R.id.mailbelirlemekutusu);
        sifreE = findViewById(R.id.sifrebelirlemekutus);
        Users user = new Users();
        user.set_hastaId(1);
        user.set_isim("Yusuf");
        user.set_soyisim("Sarukan");
        user.set_tempt("36");
        user.set_diagnosis("IleriDuzeyHasta");
        user.set_heartBeat("95");
        user.set_oxygen("92");
        mAuth = FirebaseAuth.getInstance();
        uyeOlButton.setOnClickListener(view -> {
            if(mailE.getText().toString().equals("") && sifreE.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Lütfen Tüm Alanları Doldurun", Toast.LENGTH_LONG).show();
            }
            else {
                mymail = mailE.getText().toString();
                mypassword = sifreE.getText().toString();
                mAuth.createUserWithEmailAndPassword(mymail,mypassword).addOnCompleteListener(MainActivity.this, task -> {
                    if(task.isSuccessful()){
                        Log.e("Kullanıcı Oluşturuldu","Başarılı");
                    }
                    else{
                        Log.e("Kullanıcı Oluşturalamdı", "Başarısız");

                    }

                });
            }
        });


    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Log.e("Hata","Böyle Bir Kullanıcı Yok");
        }
    }

}