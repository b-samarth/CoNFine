package com.defiance.confine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.defiance.confine.CombinationData;
import com.defiance.confine.R;
import com.defiance.confine.WarningFragment;

public class FormActivity extends AppCompatActivity {

    public static int b,g,s,p ,a;
    public static int l=0, m=0, h=0;
    public static CombinationData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        data = new CombinationData();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_frame, new WarningFragment());
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        data = new CombinationData();
    }
}