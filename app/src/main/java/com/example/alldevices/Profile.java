package com.example.alldevices;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alldevices.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends Fragment {
private TextView textNameOrg;
private FragmentProfileBinding binding;
FirebaseUser mAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        textNameOrg = (TextView) root.findViewById(R.id.nameOrg);
        addUser();
        return root;
    }

    private  void addUser(){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        //String organization=nameOrg.getText().toString();
        String userId=user.getUid();
        String org=user.getDisplayName();

        Log.d("debug","Готово!"+userId);
        textNameOrg.setText(org);

    }
}