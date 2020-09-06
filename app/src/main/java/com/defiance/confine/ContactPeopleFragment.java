package com.defiance.confine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ContactPeopleFragment extends Fragment {

    private RadioGroup contact_people_group;
    private Button button;
    private int counter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_people, container, false);

        contact_people_group = view.findViewById(R.id.contact_people_group);
        button = view.findViewById(R.id.next_contact_people);

        contact_people_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.family_friend:
                        counter = 1;
                        NavigationDrawerActivity.map.put("contactPeople", "family/friend");
                        break;
                    case R.id.health_worker:
                        counter = 1;
                        NavigationDrawerActivity.map.put("contactPeople", "healthWorker");
                        break;
                    case R.id.none_contact_people:
                        counter = 1;
                        NavigationDrawerActivity.map.put("contactPeople", "none");
                        break;
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter == 1) {
                    if (!TextUtils.equals(NavigationDrawerActivity.map.get("contactPeople"), "none")) {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_frame, new ContactDaysFragment());
                        transaction.commit();
                    } else {
                        Intent intent = new Intent(getActivity(), ResultActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
                
                else {
                    Toast.makeText(getActivity(), "Select atleast one option!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return  view;
    }
}