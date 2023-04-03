package com.dpu.bm.uykuapnesitespitsistemi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

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
            if(TextUtils.isEmpty(mailE.getText().toString()) && TextUtils.isEmpty(sifreE.getText().toString())){

                Snackbar.make(view,"Lütfen tüm alanları doldurun!",Snackbar.LENGTH_LONG).show();
            }
            else {
                if(Patterns.EMAIL_ADDRESS.matcher(mailE.getText().toString()).matches()){
                    Snackbar.make(view,"Email formati yanlış!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if(sifreE.getText().toString().length()>=6){
                    Snackbar.make(view,"Şifre altı karakterden kısa olamaz!",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else{
                    mymail = mailE.getText().toString();
                    mypassword = sifreE.getText().toString();
                    mAuth.createUserWithEmailAndPassword(mymail,mypassword).addOnCompleteListener(MainActivity.this, task -> {
                        if(task.isSuccessful()){
                            Snackbar.make(view,"Üyelik işlemi başarılı",Snackbar.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            finish();
                        }
                        else{
                            Log.e("Kullanıcı Oluşturalamdı", "Başarısız");
                        }
                    });
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAuth.signOut();
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