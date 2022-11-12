package com.example.alldevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference  mDatabase;
    TextInputEditText email,password,nameOrg;
    Button regBtn;
    SharedPreferences mPref;
    public static final String APP_PREFERENCE="settings";
    public static final String APP_PREFERENCE_UID="uid";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth= FirebaseAuth.getInstance();
        mPref=getSharedPreferences(APP_PREFERENCE,MODE_PRIVATE);


        email=(TextInputEditText)  findViewById(R.id.nameEdit1);
        password=(TextInputEditText) findViewById(R.id.passwordEdit1);
        nameOrg=(TextInputEditText) findViewById(R.id.nameOrgEdit);
        String organization=nameOrg.getText().toString();
        regBtn=(Button)findViewById(R.id.buttonReg);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount(email.getText().toString(),password.getText().toString());
            }
        });

    }

private void createAccount(String email,String password){
    mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(RegistrationActivity.this,MainActivity.class);
                        startActivity(intent);
                        FirebaseUser user=mAuth.getCurrentUser();
                        addUser();
                    }
                    else  Toast.makeText(RegistrationActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }
            });

}
//Добавляем и сохраняем информацию о пользователе
public void addUser(){
    String organization=nameOrg.getText().toString();
    FirebaseUser user=mAuth.getInstance().getCurrentUser();
    String userId=user.getUid();
    mDatabase= FirebaseDatabase.getInstance().getReference(userId);
    UserProfileChangeRequest profileUpdate=new UserProfileChangeRequest.Builder()
            .setDisplayName(organization)
            .build();

    user.updateProfile(profileUpdate)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Log.d("debug","Готово!");
                    }
                }
            });
//Сохраняем в SharedPreference UID пользователя при регистрации
    mPref=getSharedPreferences(APP_PREFERENCE,MODE_PRIVATE);
    SharedPreferences.Editor editor=mPref.edit();
    editor.putString(APP_PREFERENCE_UID,userId);
    editor.apply();

}
}