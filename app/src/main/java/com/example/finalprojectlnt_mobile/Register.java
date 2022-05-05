package com.example.finalprojectlnt_mobile;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends Fragment {
    EditText regis_id;
    EditText regis_email;
    EditText regis_name;
    EditText regis_pass;
    EditText regis_conf_pass;
    TextView login_page;
    Button regis_btn;
    String id;
    String email;
    String name;
    String pass;
    String conf_pass;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    View.OnClickListener go_to_login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getParentFragmentManager().beginTransaction().replace(R.id.main_fragment, new Login()).addToBackStack(null).commit();
        }
    };

    View.OnClickListener register_account = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            id = regis_id.getText().toString();
            email = regis_email.getText().toString();
            name = regis_name.getText().toString();
            pass = regis_pass.getText().toString();
            conf_pass = regis_conf_pass.getText().toString();

            if(validate()){
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            AccountData accountData = new AccountData();
                            accountData.setID(id);
                            accountData.setName(name);
                            accountData.setEmail(email);

                            firebaseDatabase = FirebaseDatabase.getInstance();
                            ref = firebaseDatabase.getReference("users");
                            ref.child(id).setValue(accountData);

                            Toast.makeText(getActivity(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
                            getParentFragmentManager().popBackStack();
                        } else{
                            Toast.makeText(getActivity(), "Error(s) found:\n" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    };

    private boolean validate(){
        int cek = 0;
        String error = "Error(s) found:\n";

        if (!id.isEmpty()){
            cek++;
        }else{
            error += "- ID cannot be empty\n";
        }
        if (email.endsWith(".com") && email.contains("@")){
            cek++;
        }else{
            error += "- Email must end with \".com\" or contain \"@\"\n";
        }
        if (name.length() >= 5){
            cek++;
        }else{
            error += "- Name length must be at least five characters long\n";
        }
        if (!pass.isEmpty()){
            cek++;
        }else{
            error += "- Password cannot be empty\n";
        }
        if (pass.equals(conf_pass)){
            cek++;
        }else{
            error += "- Password confirmation isn't the same as password\n";
        }

        if (cek == 5){
            return true;
        }else{
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regis_id = view.findViewById(R.id.regis_id_input);
        regis_email = view.findViewById(R.id.regis_email_input);
        regis_name = view.findViewById(R.id.regis_name_input);
        regis_pass = view.findViewById(R.id.regis_pass_input);
        regis_conf_pass = view.findViewById(R.id.regis_conf_pass_input);
        regis_btn = view.findViewById(R.id.regis_btn);
        login_page = view.findViewById(R.id.regis_to_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        login_page.setOnClickListener(go_to_login);
        regis_btn.setOnClickListener(register_account);
    }
}