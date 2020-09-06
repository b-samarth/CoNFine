package com.defiance.confine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class GenderFragment extends Fragment {

    private ImageButton malebtn, femalebtn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);

        malebtn = view.findViewById(R.id.male_img_btn);
        femalebtn = view.findViewById(R.id.female_img_btn);

        malebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.b = 1;
                FormActivity.data.addtoString('B');
                NavigationDrawerActivity.map.put("gender", "male");
                NavigationDrawerActivity.map.put("isPregnant", "NA");
                changeFragment();
            }
        });
        femalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.g = 1;
                FormActivity.data.addtoString('G');
                NavigationDrawerActivity.map.put("gender", "female");
                changeFragment();
            }
        });
        return view;
    }

    private void changeFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new AgeFragment());
        transaction.commit();
    }
}