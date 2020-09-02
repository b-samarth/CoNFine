package com.defiance.confine;

import android.content.Intent;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


public class ContactDaysFragment extends Fragment {

    private RadioGroup contact_days_group;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private Button button;
    private Map<String,String> map = new HashMap<>();
    private int checked = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_days, container, false);

        contact_days_group = view.findViewById(R.id.contact_days_group);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        button = view.findViewById(R.id.next_contact_days);

        contact_days_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.less5:
                        checked = 1;
                        map.put("contactDays", "lessThan5");
                        break;
                    case R.id.more5:
                        checked = 2;
                        map.put("contactDays", "moreThan5");
                        break;
                    case R.id.more14:
                        checked = 3;
                        map.put("contactDays", "moreThan14");
                        break;
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch(checked) {
                    case 1:
                        FormActivity.h += 1;
                        FormActivity.data.addtoString('H');
                        changeActivity();
                        break;
                    case 2:
                        FormActivity.m += 1;
                        FormActivity.data.addtoString('M');
                        changeActivity();
                        break;
                    case 3:
                        FormActivity.l += 1;
                        FormActivity.data.addtoString('L');
                        changeActivity();
                        break;
                    case 0:
                        Toast.makeText(getActivity(), "Please select atleast one option", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        return view;
    }

    private void changeActivity() {
        db.collection("Users")
                .document(user.getUid())
                .set(map, SetOptions.merge());
        Intent intent = new Intent(getActivity(), ResultActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}