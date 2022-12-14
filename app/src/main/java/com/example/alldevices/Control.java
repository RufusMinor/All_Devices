package com.example.alldevices;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alldevices.databinding.FragmentControlBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Control extends Fragment {
private FragmentControlBinding binding;
private FloatingActionButton add;
private AlertDialog dialog;
TextView name,title,date;
DatabaseReference mDatabase, mDatabaseChange;
LinearLayout container1;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDatabase= FirebaseDatabase.getInstance().getReference("user");
        mDatabaseChange=mDatabase.push();
        String key=mDatabaseChange.getKey();
        //mDatabaseChange=mDatabase.child(date);


        binding=FragmentControlBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        container1=(LinearLayout)root.findViewById(R.id.container);
        addVerefication();
        equalsDate(root);
       // getUser();
        add=(FloatingActionButton)root.findViewById(R.id.floating_action_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });
        return root;

    }
//???????????????????? ???????????????? ?? ???????? ??????????????
    private void addVerefication() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view=getLayoutInflater().inflate(R.layout.dialog,null);
        TextInputEditText name=view.findViewById(R.id.nameEdit);
        TextInputEditText title=view.findViewById(R.id.title2);
        TextInputEditText date=view.findViewById(R.id.date);
        TextView text2=view.findViewById(R.id.text1);
        builder.setView(view);
        builder.setTitle("???????????? ???????????? ?? ?????????????????? ??????????????????")
                .setPositiveButton("??????????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("????????????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog=builder.create();
    }
//?????????????????? ?????? ?? ????
    public void equalsDate(View view) {
        Calendar now=Calendar.getInstance();
        now.add(Calendar.DATE,7);
        Date date3=now.getTime();
        String dayFinal= new SimpleDateFormat("dd.MM.yyyy").format(date3);
        Log.d("Date","????: "+dayFinal);

        Query query=mDatabase.child("device").orderByChild("meter/date").equalTo(dayFinal);
        query.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                Log.d("Date","?????????? ????????????????");
                if(snapshot.exists()){
                    Log.d("Date","???????????????????? ????????!");
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String name= ds.child("name").getValue(String.class);
                        String title= ds.child("meter/title").getValue(String.class);
                        String date= ds.child("date").getValue(String.class);
                        Log.d("Date","????: "+name+date+title);
                            addCard(name,title,date);
                    }
                }
                else {
                    Log.d("Date","???????????????????? ??????");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){
                Log.d("Date","????????????");
            }
        });






    }
    private void addCard(String nameM,String titleM,String dateM){
        View view=getLayoutInflater().inflate(R.layout.card,null);
        name=(TextView)view.findViewById(R.id.name);
        title=(TextView) view.findViewById(R.id.title);
        date=(TextView) view.findViewById(R.id.date);
        name.setText(nameM);
        title.setText(titleM);
        date.setText(dateM);

        container1.addView(view);

    }
    public void getUser(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String userId=user.getUid();
        Log.d("debug","??????????: "+userId);
        String name = user.getDisplayName();
        Log.d("debug","??????????: "+name);
    }



}
