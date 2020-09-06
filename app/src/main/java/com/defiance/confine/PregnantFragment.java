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


public class PregnantFragment extends Fragment {

    private ImageButton not_pregnant_btn, pregnant_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pregnant, container, false);

        not_pregnant_btn = view.findViewById(R.id.no_pregnant_btn);
        pregnant_btn = view.findViewById(R.id.pregnant_btn);


        not_pregnant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.p = 0;
                NavigationDrawerActivity.map.put("isPregnant", "false");
                changeFragment();
            }
        });
        pregnant_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.p = 1;
                FormActivity.data.addtoString('P');
                NavigationDrawerActivity.map.put("isPregnant", "true");
                changeFragment();
            }
        });
        return view;
    }

    private void changeFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new SmokeFragment());
        transaction.commit();
    }
}