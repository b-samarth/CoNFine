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


public class TravelFragment extends Fragment {

    private ImageButton yes_travel_btn, no_travel_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);

        no_travel_btn = view.findViewById(R.id.no_travel_btn);
        yes_travel_btn = view.findViewById(R.id.yes_travel_btn);


        no_travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavigationDrawerActivity.map.put("isTravel", "false");
                changeFragment();
            }
        });
        yes_travel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormActivity.h += 1;
                NavigationDrawerActivity.map.put("isTravel", "true");
                FormActivity.data.addtoString('H');
                changeFragment();
            }
        });
        return view;
    }

    private void changeFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, new ContactPeopleFragment());
        transaction.commit();
    }
}