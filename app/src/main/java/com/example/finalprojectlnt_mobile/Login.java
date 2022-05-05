package com.example.finalprojectlnt_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Fragment {

    EditText login_email;
    EditText login_pass;
    Button login_btn;
    TextView regis_page;
    String email;
    String pass;
    FirebaseAuth mAuth;

    View.OnClickListener go_to_register = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getParentFragmentManager().beginTransaction().replace(R.id.main_fragment, new Register()).addToBackStack(null).commit();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    View.OnClickListener login_account = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            email = login_email.getText().toString();
            pass = login_pass.getText().toString();

            if (validate()){
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                            Intent go_to_home = new Intent(getActivity(), Home.class);
                            startActivity(go_to_home);
                        }else{
                            Toast.makeText(getActivity(), "Error:\n" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    };

    private boolean validate(){
        int cek = 0;
        String error = "Error(s) found:\n";

        if (email.endsWith(".com") && email.contains("@")){
            cek++;
        }else{
            error += "- Email must end with \".com\" or contain \"@\"\n";
        }
        if (!pass.isEmpty()){
            cek++;
        }else{
            error += "- Password cannot be empty\n";
        }

        if (cek == 2){
            return true;
        }else{
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login_email = view.findViewById(R.id.login_email_input);
        login_pass = view.findViewById(R.id.login_pass_input);
        login_btn = view.findViewById(R.id.login_btn);
        regis_page = view.findViewById(R.id.login_to_regis);

        mAuth = FirebaseAuth.getInstance();

        regis_page.setOnClickListener(go_to_register);
        login_btn.setOnClickListener(login_account);
    }
}
