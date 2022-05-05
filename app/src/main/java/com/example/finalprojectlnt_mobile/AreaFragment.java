package com.example.finalprojectlnt_mobile;

import android.content.Context;
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

public class AreaFragment extends Fragment {

    TextView persegi_calc;
    TextView segitiga_calc;
    TextView lingkaran_calc;
    EditText persegi_sisi;
    EditText segitiga_alas;
    EditText segitiga_tinggi;
    EditText lingkaran_radius;
    Button persegi_btn;
    Button segitiga_btn;
    Button lingkaran_btn;
    String str_num1;
    String str_num2;
    Double num1;
    Double num2;

    View.OnClickListener calculate_persegi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str_num1 = persegi_sisi.getText().toString();
            if (checkNum(str_num1)){
                num1 = Double.parseDouble(str_num1);
                persegi_calc.setText(str_num1 + " x " + str_num1 + " = " + num1*num1);
            }else{
                Toast.makeText(getActivity(), "Sisi value must be a number", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener calculate_segitiga = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str_num1 = segitiga_alas.getText().toString();
            str_num2 = segitiga_tinggi.getText().toString();
            if (checkNum(str_num1) && checkNum(str_num2)){
                num1 = Double.parseDouble(str_num1);
                num2 = Double.parseDouble(str_num2);
                segitiga_calc.setText("( " + str_num1 + " x " + str_num2 + " )/2 = " + (num1*num2)/2);
            }else{
                Toast.makeText(getActivity(), "Alas/Tinggi value must be a number", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener calculate_lingkaran = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str_num1 = lingkaran_radius.getText().toString();
            if (checkNum(str_num1)){
                num1 = Double.parseDouble(str_num1);
                lingkaran_calc.setText("3,14 x " + str_num1 + " x " + str_num1 + " = " + (3.14*num1*num1));
            }else{
                Toast.makeText(getActivity(), "Radius value must be a number", Toast.LENGTH_SHORT).show();
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
        return inflater.inflate(R.layout.fragment_area, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        persegi_calc = view.findViewById(R.id.persegi_calc);
        segitiga_calc = view.findViewById(R.id.segitiga_calc);
        lingkaran_calc = view.findViewById(R.id.lingkaran_calc);
        persegi_sisi = view.findViewById(R.id.persegi_enter_input);
        segitiga_alas = view.findViewById(R.id.segitiga_enter_input1);
        segitiga_tinggi = view.findViewById(R.id.segitiga_enter_input2);
        lingkaran_radius = view.findViewById(R.id.lingkaran_enter_input);
        persegi_btn = view.findViewById(R.id.persegi_btn);
        segitiga_btn = view.findViewById(R.id.segitiga_btn);
        lingkaran_btn = view.findViewById(R.id.lingkaran_btn);

        persegi_btn.setOnClickListener(calculate_persegi);
        segitiga_btn.setOnClickListener(calculate_segitiga);
        lingkaran_btn.setOnClickListener(calculate_lingkaran);
    }
}