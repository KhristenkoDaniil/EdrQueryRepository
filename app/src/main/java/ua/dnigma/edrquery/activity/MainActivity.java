package ua.dnigma.edrquery.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

import ua.dnigma.edrquery.R;
import ua.dnigma.edrquery.fragment.EdrQueryFragment;

public class MainActivity extends AppCompatActivity {

    EdrQueryFragment edrQueryFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        edrQueryFragment = new EdrQueryFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.cotainer_id, edrQueryFragment);
        fragmentTransaction.commit();

    }

}
