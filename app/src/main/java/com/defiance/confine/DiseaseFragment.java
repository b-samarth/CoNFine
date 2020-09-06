package com.defiance.confine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class DiseaseFragment extends Fragment {

    private Button button;
    private CheckBox diabetes, hyper, lungs, heart, kidney, liver, blood, immune;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease, container, false);

        NavigationDrawerActivity.map.put("hasDiabetes", "false");
        NavigationDrawerActivity.map.put("hasHypertension", "false");
        NavigationDrawerActivity.map.put("hasLungDisease", "false");
        NavigationDrawerActivity.map.put("hasHeartDisease", "false");
        NavigationDrawerActivity.map.put("hasKidneyDisease", "false");
        NavigationDrawerActivity.map.put("hasLiverDisease", "false");
        NavigationDrawerActivity.map.put("hasBloodDisease", "false");
        NavigationDrawerActivity.map.put("hasImmunoDeficiency", "false");

        button = view.findViewById(R.id.next_disease);

        diabetes = view.findViewById(R.id.diabetes);
        hyper = view.findViewById(R.id.hypertension);
        lungs = view.findViewById(R.id.lungs);
        heart = view.findViewById(R.id.heart);
        kidney = view.findViewById(R.id.kidney);
        liver = view.findViewById(R.id.liver);
        blood = view.findViewById(R.id.blood);
        immune = view.findViewById(R.id.immune);

        diabetes.setOnCheckedChangeListener(myCheckBoxListener);
        hyper.setOnCheckedChangeListener(myCheckBoxListener);
        lungs.setOnCheckedChangeListener(myCheckBoxListener);
        heart.setOnCheckedChangeListener(myCheckBoxListener);
        kidney.setOnCheckedChangeListener(myCheckBoxListener);
        liver.setOnCheckedChangeListener(myCheckBoxListener);
        blood.setOnCheckedChangeListener(myCheckBoxListener);
        immune.setOnCheckedChangeListener(myCheckBoxListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, new TravelFragment());
                transaction.commit();
            }
        });


        return view;
    }

    private CompoundButton.OnCheckedChangeListener myCheckBoxListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.diabetes:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasDiabetes", "true");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasDiabetes", "false");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.hypertension:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasHypertension", "true");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasHypertension", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.lungs:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasLungDisease", "true");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasLungDisease", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.heart:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasHeartDisease", "true");
                        FormActivity.h += 1;
                        FormActivity. data.addtoString('H');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasHeartDisease", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.kidney:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasKidneyDisease", "true");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasKidneyDisease", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.liver:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasLiverDisease", "true");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasLiverDisease", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.blood:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasBloodDisease", "true");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasBloodDisease", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.immune:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasImmunoDeficiency", "true");
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                    }
                    else{
                        NavigationDrawerActivity.map.put("hasImmunoDeficiency", "false");
                        FormActivity.h -= 1;
                        FormActivity.data.removeFromString();
                        }
                    break;
            }
        }
    };


    public boolean hasImmunoDeficiency() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasImmunoDeficiency"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasBloodDisease() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasBloodDisease"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasLiverDisease() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasLiverDisease"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasKidneyDisease() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasKidneyDisease"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasHeartDisease() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasHeartDisease"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasLungDisease() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasLungDisease"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasHypertension() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasHypertension"), "true"))
            return true;
        else
            return false;
    }

    public boolean hasDiabetes() {
        if (TextUtils.equals(NavigationDrawerActivity.map.get("hasDiabetes"), "true"))
            return true;
        else
            return false;
    }
}
