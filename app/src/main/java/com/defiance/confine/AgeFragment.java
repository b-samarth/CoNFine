package com.defiance.confine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AgeFragment extends Fragment {

    private RadioGroup age_group;
    private Button button;
    private int counter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);

        age_group = view.findViewById(R.id.age_group);
        button = view.findViewById(R.id.next_age);

        age_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.more10:
                        FormActivity.a = 1;
                        counter = 1;
                        FormActivity.data.addtoString('L');
                        NavigationDrawerActivity.map.put("ageGroup", "10-39");
                        break;
                    case R.id.more40:
                        FormActivity.a = 2;
                        counter = 1;
                        FormActivity.data.addtoString('M');
                        NavigationDrawerActivity.map.put("ageGroup", "40-79");
                        break;
                    case R.id.more80:
                        FormActivity.a = 3;
                        counter = 1;
                        FormActivity.data.addtoString('H');
                        NavigationDrawerActivity.map.put("ageGroup", ">80");
                        break;
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter != 0) {
                    if (FormActivity.b == 1) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_frame, new SmokeFragment());
                        transaction.commit();
                    } else if (FormActivity.g == 1) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_frame, new PregnantFragment());
                        transaction.commit();
                    }
                }
                else
                    Toast.makeText(getActivity(), "Select at least one option", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}