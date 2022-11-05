package com.example.alldevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.alldevices.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater ());

        setContentView(binding.getRoot());

        replaceFragment(new Control());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{

            switch (item.getItemId()){

                case R.id.control:
                    replaceFragment(new Control());
                    break;
                case R.id.up:
                    replaceFragment(new Up());
                    break;
                case R.id.profile:
                    replaceFragment(new Profile());
                    break;
                case R.id.settings:
                    replaceFragment(new Settings());
                    break;
            }
            return true;
        });
    }


private  void replaceFragment(Fragment fragment){
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.frameLayout,fragment);
    fragmentTransaction.commit();
}
}