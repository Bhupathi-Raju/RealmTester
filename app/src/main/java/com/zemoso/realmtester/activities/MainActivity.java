package com.zemoso.realmtester.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zemoso.realmtester.adapters.RecyclerAdapter;
import com.zemoso.realmtester.fragments.DetailsFragment;
import com.zemoso.realmtester.fragments.HomeFragment;
import com.zemoso.realmtester.R;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.AdapterInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack("home")
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void openFragment(int id) {
        Fragment detailsFragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,detailsFragment)
                .addToBackStack("details")
                .commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count>1)
        {
            getSupportFragmentManager().popBackStackImmediate();
            Log.d("count",count + "frags");
        }
        else
        {
            Log.d("count else",count + "frags");
            finish();
        }
    }
}
