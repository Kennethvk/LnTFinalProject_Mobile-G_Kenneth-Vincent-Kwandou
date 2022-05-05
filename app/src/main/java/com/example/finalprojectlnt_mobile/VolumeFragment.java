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

public class VolumeFragment extends Fragment {

    TextView balok_calc;
    TextView pira_calc;
    TextView tabu_calc;
    EditText balok_l;
    EditText balok_w;
    EditText balok_h;
    EditText pira_l;
    EditText pira_w;
    EditText pira_h;
    EditText tabu_r;
    EditText tabu_h;
    Button balok_btn;
    Button pira_btn;
    Button tabu_btn;
    String str_num1;
    String str_num2;
    String str_num3;
    Double num1;
    Double num2;
    Double num3;

    View.OnClickListener calculate_balok = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str_num1 = balok_l.getText().toString();
            str_num2 = balok_w.getText().toString();
            str_num3 = balok_h.getText().toString();
            if (checkNum(str_num1) & checkNum(str_num2) & checkNum(str_num3)){
                num1 = Double.parseDouble(str_num1);
                num2 = Double.parseDouble(str_num2);
                num3 = Double.parseDouble(str_num3);
                balok_calc.setText(str_num1 + " x " + str_num2 + " x " + str_num3 + " = " + num1*num2*num3);
            }else{
                Toast.makeText(getActivity(), "l/w/h value must be a number", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener calculate_pira = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str_num1 = pira_l.getText().toString();
            str_num2 = pira_w.getText().toString();
            str_num3 = pira_h.getText().toString();
            if (checkNum(str_num1) & checkNum(str_num2) & checkNum(str_num3)){
                num1 = Double.parseDouble(str_num1);
                num2 = Double.parseDouble(str_num2);
                num3 = Double.parseDouble(str_num3);
                pira_calc.setText("(" + str_num1 + " x " + str_num2 + " x " + str_num3 + ")/3 = " + (num1*num2*num3)/3);
            }else{
                Toast.makeText(getActivity(), "l/w/h value must be a number", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener calculate_tabu = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str_num1 = tabu_r.getText().toString();
            str_num2 = tabu_h.getText().toString();
            if (checkNum(str_num1) & checkNum(str_num2)){
                num1 = Double.parseDouble(str_num1);
                num2 = Double.parseDouble(str_num2);
                tabu_calc.setText("3,14 x " + str_num1 + " x " + str_num1 + " x " + str_num2 + " = " + 3.14*num1*num1*num2);
            }else{
                Toast.makeText(getActivity(), "r/h value must be a number", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean checkNum(String num){
        try {
            Double.parseDouble(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volume, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        balok_calc = view.findViewById(R.id.balok_calc);
        pira_calc = view.findViewById(R.id.pira_calc);
        tabu_calc = view.findViewById(R.id.tabu_calc);
        balok_l = view.findViewById(R.id.balok_enter_input1);
        balok_w = view.findViewById(R.id.balok_enter_input2);
        balok_h = view.findViewById(R.id.balok_enter_input3);
        pira_l = view.findViewById(R.id.pira_enter_input1);
        pira_w = view.findViewById(R.id.pira_enter_input2);
        pira_h = view.findViewById(R.id.pira_enter_input3);
        tabu_r = view.findViewById(R.id.tabu_enter_input1);
        tabu_h = view.findViewById(R.id.tabu_enter_input2);
        balok_btn = view.findViewById(R.id.balok_btn);
        pira_btn = view.findViewById(R.id.pira_btn);
        tabu_btn = view.findViewById(R.id.tabu_btn);

        balok_btn.setOnClickListener(calculate_balok);
        pira_btn.setOnClickListener(calculate_pira);
        tabu_btn.setOnClickListener(calculate_tabu);
    }
}