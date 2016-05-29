package com.maciejmalak.whatcanieat.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.maciejmalak.whatcanieat.R;
import com.maciejmalak.whatcanieat.foodList.FoodAdapter;
import com.maciejmalak.whatcanieat.foodList.FoodPojo;

import butterknife.Bind;
import butterknife.ButterKnife;

/* TODO : What will I place in activity about fodmap and application itself? */
/* TODO : Provide db - implement greendao as a model layer. */
/* TODO : Choose color for app, especially for nav drawer. */
/* TODO : Maybe "mini" nav drawer will be better option than regular(big) */
/* TODO : Add exception for butterknife when adding Proguard. */
/* TODO : Find someone who will help with preparing icon for application and icons for drawer. " */

public class MainSearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) protected Toolbar mToolbar;
    @Bind(R.id.drawer_layout) protected DrawerLayout mDrawer;
    @Bind(R.id.nav_view) protected NavigationView mNavigationView;

    private SearchView mSearchView;
    private FoodAdapter mAdapter;

    private final SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            /*It's supported in onNewIntent */
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            mAdapter.getFilter().filter(query);
            return false;
        }
    };

    private final AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FoodPojo item = (FoodPojo) mAdapter.getItem(position);
            if (item.isAllowed() == 0 && !item.getDesc().isEmpty()) {
                Toast.makeText(parent.getContext(), item.getDesc(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initDefaultViews();
        initListView();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.search_toolbar);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final ComponentName componentName = getComponentName();
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return super.onCreateOptionsMenu(menu);
    }

    private void initDefaultViews() {

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initListView() {
        mAdapter = new FoodAdapter();
        final ListView listView = (ListView) findViewById(R.id.food_list);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(mOnClickListener);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntents(intent);
    }

    private void handleIntents(final Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            /* MM: setQuery triggers onQueryTextChange in the background */
            mSearchView.setQuery(query, false);
        }
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