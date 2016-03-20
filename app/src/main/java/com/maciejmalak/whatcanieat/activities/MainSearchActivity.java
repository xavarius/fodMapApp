package com.maciejmalak.whatcanieat.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.maciejmalak.whatcanieat.R;
import com.maciejmalak.whatcanieat.foodList.FoodAdapter;
import com.maciejmalak.whatcanieat.foodList.FoodPojo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainSearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) protected Toolbar mToolbar;
    @Bind(R.id.drawer_layout) protected DrawerLayout mDrawer;
    @Bind(R.id.nav_view) protected NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        ButterKnife.bind(this);

        initDefaultViews();
        initListView();
    }

    private void initDefaultViews() {
        setSupportActionBar(mToolbar);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initListView() {
        final ArrayList<FoodPojo> list = new ArrayList<>();
        list.add(new FoodPojo("Banan", "Owoc", false));
        list.add(new FoodPojo("Gruszka", "Owoc", true));
        list.add(new FoodPojo("Cebula", "Warzywo", false));

        final FoodAdapter foodAdapter = new FoodAdapter(this, list);
        final ListView listView = (ListView) findViewById(R.id.food_list);
        listView.setAdapter(foodAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
