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

public class SmokeFragment extends Fragment {

    private ImageButton smoke_btn, no_smoke_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smoke, container, false);

        no_smoke_btn = view.findViewById(R.id.no_smoke_btn);
        smoke_btn = view.findViewById(R.id.smoke_btn);

        no_smoke_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.s = 0;
                NavigationDrawerActivity.map.put("isSmoking", "false");
                changeFragment();
            }
        });
        smoke_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.s = 1;
                FormActivity.   data.addtoString('S');
                NavigationDrawerActivity.map.put("isSmoking", "true");
                changeFragment();
            }
        });
        return view;
    }

    private void changeFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new SymptomsFragment());
        transaction.commit();
    }
}