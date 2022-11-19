package com.example.alldevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingInActivity extends AppCompatActivity {

    private Button btnSingIn,btnReg;
     TextInputEditText emailUser, passwordUser;
     FirebaseAuth mAuth;
     HorizontalScrollView mScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        mAuth=FirebaseAuth.getInstance();
        scrolAuto();
        emailUser=(TextInputEditText) findViewById(R.id.emailEdit);
        passwordUser=(TextInputEditText) findViewById(R.id.passwordEdit);
        mScroll= (HorizontalScrollView) findViewById(R.id.scroll);

        btnReg=(Button) findViewById(R.id.buttonSingUp);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SingInActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnSingIn=(Button) findViewById(R.id.buttonSingIn);
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singInEmailPassword(emailUser.getText().toString(),passwordUser.getText().toString());
            }
        });


    }

    private void singInEmailPassword(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent =new Intent(SingInActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SingInActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private void scrolAuto(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mScroll.scrollBy(+1000, 0);
            }
        }, 4000);

    }
}