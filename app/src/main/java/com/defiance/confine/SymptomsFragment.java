package com.defiance.confine;

import android.os.Bundle;
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

public class SymptomsFragment extends Fragment {

    private Button button;
    private CheckBox cough, muffled, breath, fever, nausea, diarrhoea, sense;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);

        NavigationDrawerActivity.map.put("hasCough", "false");
        NavigationDrawerActivity.map.put("isMuffled", "false");
        NavigationDrawerActivity.map.put("hasShortness", "false");
        NavigationDrawerActivity.map.put("hasFever", "false");
        NavigationDrawerActivity.map.put("hasNausea", "false");
        NavigationDrawerActivity.map.put("hasDiarrhoea", "false");
        NavigationDrawerActivity.map.put("hasSenseLoss", "false");


        button = view.findViewById(R.id.next_symptoms);
        cough = view.findViewById(R.id.cough);
        muffled = view.findViewById(R.id.muffled);
        breath = view.findViewById(R.id.breath);
        fever = view.findViewById(R.id.fever);
        nausea = view.findViewById(R.id.nausea);
        diarrhoea = view.findViewById(R.id.diarrhoea);
        sense = view.findViewById(R.id.sense);


        cough.setOnCheckedChangeListener(myCheckBoxListener);
        muffled.setOnCheckedChangeListener(myCheckBoxListener);
        breath.setOnCheckedChangeListener(myCheckBoxListener);
        fever.setOnCheckedChangeListener(myCheckBoxListener);
        nausea.setOnCheckedChangeListener(myCheckBoxListener);
        diarrhoea.setOnCheckedChangeListener(myCheckBoxListener);
        sense.setOnCheckedChangeListener(myCheckBoxListener);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame, new DiseaseFragment());
                transaction.commit();
        }
        });
        return view;
    }

    private CompoundButton.OnCheckedChangeListener myCheckBoxListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.cough:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasCough", "true");
                        FormActivity.l += 1;
                        FormActivity.data.addtoString('L');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasCcough", "false");
                        FormActivity.l -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.muffled:
                    if (b) {
                        NavigationDrawerActivity.map.put("isMuffled", "true");
                        FormActivity.l += 1;
                        FormActivity.data.addtoString('L');
                    }
                    else {
                        NavigationDrawerActivity.map.put("isMuffled", "true");
                        FormActivity.l -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.breath:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasShortness", "true");
                        FormActivity.m += 1;
                        FormActivity. data.addtoString('M');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasShortness", "false");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.fever:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasFever", "true");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasFever", "false");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.nausea:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasNausea", "true");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasNausea", "false");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.diarrhoea:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasDiarrhoea", "true");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasDiarrhoea", "false");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
                case R.id.sense:
                    if (b) {
                        NavigationDrawerActivity.map.put("hasSenseLoss", "true");
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                    }
                    else {
                        NavigationDrawerActivity.map.put("hasSenseLoss", "false");
                        FormActivity.m -= 1;
                        FormActivity.data.removeFromString();
                    }
                    break;
            }
        }
    };
}