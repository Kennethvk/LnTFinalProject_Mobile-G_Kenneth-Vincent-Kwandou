package com.example.finalprojectlnt_mobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CounterFragment extends Fragment {

    TextView counter_num;
    Button counter_reset;
    Button counter_min;
    Button counter_plus;
    String num_string = "0";
    Integer num;
    SharedPreferences sharedPreferences;

    View.OnClickListener reset_number = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            counter_num.setText("0");
            shared();
        }
    };

    View.OnClickListener decrease_number = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            num_string = counter_num.getText().toString();
            num = Integer.parseInt(num_string);
            if (num != 0){
                num--;
                counter_num.setText(num.toString());
                shared();
            }
        }
    };

    View.OnClickListener increase_number = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            num_string = counter_num.getText().toString();
            num = Integer.parseInt(num_string);
            num++;
            counter_num.setText(num.toString());
            shared();
        }
    };

    public void shared(){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("number", counter_num.getText().toString());
        edit.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        counter_num = view.findViewById(R.id.counter_num);
        counter_reset = view.findViewById(R.id.counter_reset);
        counter_min = view.findViewById(R.id.counter_min);
        counter_plus = view.findViewById(R.id.counter_plus);

        counter_reset.setOnClickListener(reset_number);
        counter_min.setOnClickListener(decrease_number);
        counter_plus.setOnClickListener(increase_number);

        sharedPreferences = getActivity().getSharedPreferences("number", Context.MODE_PRIVATE);
        num_string = sharedPreferences.getString("number", "0");
        counter_num.setText(num_string);
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_counter, container, false);
//
//        counter_num = v.findViewById(R.id.counter_num);
//        counter_reset = v.findViewById(R.id.counter_reset);
//        counter_min = v.findViewById(R.id.counter_min);
//        counter_plus = v.findViewById(R.id.counter_plus);
//
//        counter_reset.setOnClickListener(reset_number);
//        counter_min.setOnClickListener(decrease_number);
//        counter_plus.setOnClickListener(increase_number);
//
//        return v;
//    }
}