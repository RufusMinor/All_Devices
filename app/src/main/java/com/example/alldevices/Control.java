package com.example.alldevices;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alldevices.databinding.FragmentControlBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Control extends Fragment {
private FragmentControlBinding binding;
private FloatingActionButton add;
private AlertDialog dialog;
private TextView text,text2;
DatabaseReference mDatabase, mDatabaseChange;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabaseChange=mDatabase.child(date)


        binding=FragmentControlBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
        addVerefication();
        equalsDate();
        add=(FloatingActionButton)root.findViewById(R.id.floating_action_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
            }
        });
        return root;

    }
//Добавление приборов и даты поверки
    private void addVerefication() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view=getLayoutInflater().inflate(R.layout.dialog,null);
        TextInputEditText name=view.findViewById(R.id.nameEdit);
        TextInputEditText title=view.findViewById(R.id.title2);
        TextInputEditText date=view.findViewById(R.id.date);
        TextView text2=view.findViewById(R.id.text1);
        builder.setView(view);
        builder.setTitle("Внести данные о средствах измерений")
                .setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        dialog=builder.create();
    }
//Сравнение дат с БД
    public void equalsDate(){
        String date1 = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        Log.d("Date","На: "+date1);


        Query s=mDatabase.child("corrector").orderByChild("date");
        int result=date1.compareTo(String.valueOf(s));
        text2.setText(result);


    }



}
