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

public class FragmentLogin extends Fragment {

    Button btnLogin,btnRegister;
    EditText etUserName,etPassword;
    CallbackFragment callbackFragment;
    String userName,Password;
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

        View view = inflater.inflate(R.layout.login_fragment,container,false);

        etUserName = view.findViewById(R.id.etusername);
        etPassword = view.findViewById(R.id.etpassword);
        btnLogin = view.findViewById(R.id.btn_login);
        btnRegister = view.findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = etUserName.getText().toString();
                Password = etPassword.getText().toString();

                if (userName.isEmpty())
                {
                    etUserName.requestFocus();
                    etUserName.setError("Please enter the user Id");
                    return;
                }
                if (Password.isEmpty())
                {
                    etPassword.requestFocus();
                    etPassword.setError("Please enter the password");
                    return;
                }
//                if (Password.length()<8)
//                {
//                    etPassword.requestFocus();
//                    etPassword.setError("Enter the correct password");
//                    return;
//                }
                String uName,uPass;
                uName = sharedPreferences.getString("userName",null);
                uPass = sharedPreferences.getString("password",null);

                if (userName.equals(uName) && Password.equals(uPass))
                {
                    Toast.makeText(getContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(getContext(), "Login Faild", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new FragmentRegister();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return view;
    }
    public void setCallbackFragment(CallbackFragment callbackFragment)
    {
        this.callbackFragment = callbackFragment;
    }
}
