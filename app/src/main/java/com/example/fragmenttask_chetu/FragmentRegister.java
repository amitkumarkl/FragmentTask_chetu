package com.example.fragmenttask_chetu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentRegister extends Fragment {

    Button btnRegister;
    EditText etUserName,etPassword,etConfirmpassword;
    String userName,Password,ConfirmPass;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    public void onAttach(@NonNull Context context) {

        sharedPreferences = context.getSharedPreferences("usersFile",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_fragment,container,false);

        etUserName = view.findViewById(R.id.etusername);
        etPassword = view.findViewById(R.id.etpassword);
        etConfirmpassword = view.findViewById(R.id.etconfirmpassword);

        btnRegister = view.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = etUserName.getText().toString();
                Password = etPassword.getText().toString();
                ConfirmPass = etConfirmpassword.getText().toString();

                if (userName.isEmpty())
                {
                    etUserName.requestFocus();
                    etUserName.setError("Enter the user Id");
                    return;
                }
                if (Password.isEmpty())
                {
                    etPassword.requestFocus();
                    etPassword.setError("Enter the password");
                    return;
                }
                if (ConfirmPass.isEmpty())
                {
                    etConfirmpassword.requestFocus();
                    etConfirmpassword.setError("Enter the confirm password");
                    return;
                }

                if(!Password.equals(ConfirmPass)){
                    Toast.makeText(getContext(),"Confirm password not correct",Toast.LENGTH_SHORT).show();
                    return;
                }

                editor.putString("userName",userName);
                editor.putString("password",Password);
                editor.putString("confirmpass",ConfirmPass);
                editor.apply();


                Fragment fragment = new FragmentLogin();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getContext(), "Register Successfully", Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }
}
