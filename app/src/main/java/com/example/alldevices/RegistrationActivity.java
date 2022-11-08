package com.example.alldevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextInputLayout email,password,nameOrg;
    Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth= FirebaseAuth.getInstance();

        email=(TextInputLayout) findViewById(R.id.nameEdit);
        password=(TextInputLayout) findViewById(R.id.passwordEdit);
        nameOrg=(TextInputLayout) findViewById(R.id.nameOrgEdit);
        regBtn=(Button)findViewById(R.id.buttonReg);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(email.toString(),password.toString());
            }
        });

    }

private void createAccount(String email,String password){
    mAuth.signInWithEmailAndPassword(email.toString(),password.toString())
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                        startActivity(intent);}
                }
            });

}
}