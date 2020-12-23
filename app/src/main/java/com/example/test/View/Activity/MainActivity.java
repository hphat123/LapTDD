package com.example.test.View.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.Adapter.ItemSelectedCategories;
import com.example.test.Adapter.ListCategoriesAdapter;
import com.example.test.Adapter.OnItemClick;
import com.example.test.Condition.Contain;
import com.example.test.Model.Categories;
import com.example.test.Preferences.ManagerPreferences;
import com.example.test.R;
import com.example.test.View.Dialog.ConfrimDialog;
import com.example.test.View.Fragment.Fragment_News;
import com.example.test.View.Fragment.Fragment_Personal;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener,
        ConfrimDialog.OnAceptListener, View.OnClickListener, OnItemClick,
        ItemSelectedCategories  {

    private Toolbar mToolbar;

    private DrawerLayout mDrawer;

    private NavigationView mNaviLeft;

    private BottomNavigationView mBottomNavigationView;

    private ImageView mImgCloseLeft;

    private ImageView mImgPersonal;

    private View mViewNavigationLeft;

    private ImageView mImgHomeLeft;

    private Fragment mFragment;

    private RecyclerView mRecycleCategories;

    private ImageView mImgModeRead;

    private ListCategoriesAdapter mListCategoriesAdapter;

    public static List<Categories> mListCategories;

    private TextView mTvTitleCategories;

    private ManagerPreferences mManagerPreferences;


    private void closeDrawerLeft() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }

    public Fragment getActiveFragment() {
        Fragment f = null;
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            f = new Fragment_News();
        } else {
            String tag = getSupportFragmentManager().
                    getBackStackEntryAt((getSupportFragmentManager().
                            getBackStackEntryCount()) - 1).getName();
            f = getSupportFragmentManager().findFragmentByTag(tag);
        }
        return f;
    }
    @SuppressLint("RestrictedApi")
    private void setSelectedItem(int actionId) {
        Menu menu = mBottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem menuItem = menu.getItem(i);
            ((MenuItemImpl) menuItem).setExclusiveCheckable(false);
            menuItem.setChecked(menuItem.getItemId() == actionId);
            ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
        }
    }

    private void backStackFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getActiveFragment() instanceof Fragment_News) {
                        setSelectedItem(R.id.item_news);
                        setTitleToolBar(getResources().getString(R.string.news));
                    } else {
                        if (getActiveFragment() instanceof Fragment_Personal) {
                            setSelectedItem(R.id.item_personal);
                            setTitleToolBar(getResources().getString(R.string.personal));
                        }

                    }
                }
            }, Contain.TIME_DELAY_BACK_FRAGMENT);
        } else {
            ConfrimDialog confrimDialog = new ConfrimDialog(this);
            confrimDialog.show();
        }
    }

    private void setTitleToolBar(final String title) {
        getSupportActionBar().setTitle(title);
    }
    private void initView() {
//        init view for main_activity
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setTitleToolBar(getResources().getString(R.string.news));

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, mDrawer, mToolbar, Contain.NUMBER_ZERO, Contain.NUMBER_ZERO);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mImgPersonal = findViewById(R.id.img_personal_toolbar);
        mImgPersonal.setOnClickListener(this);

        initViewNavigationLeft();

    }
    private void initViewNavigationLeft(){
        mNaviLeft = findViewById(R.id.nav_view_left);

        mViewNavigationLeft = findViewById(R.id.layout_navigation_left);

        mImgCloseLeft = mViewNavigationLeft.findViewById(R.id.img_close_left);
        mImgCloseLeft.setOnClickListener(this);

        mImgHomeLeft = mViewNavigationLeft.findViewById(R.id.img_Home_left);
        mImgHomeLeft.setOnClickListener(this);
    }

    private void changeFragment(Fragment fragment) {
        if (!fragment.getClass().getName().toString().
                equals(getActiveFragment().getClass().getName().toString())) {
            getSupportFragmentManager().beginTransaction().setAllowOptimization(true).
                    replace(R.id.frame_content, fragment, fragment.getClass().getName()).
                    addToBackStack(fragment.getClass().getName()).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mFragment = new Fragment_News();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_content, mFragment).commit();
}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close_left:
                ConfrimDialog confrimDialog = new ConfrimDialog(this);
                confrimDialog.show();
                break;
            case R.id.img_Home_left:
                if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                    mDrawer.closeDrawer(GravityCompat.START);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            backStackFragment();
        }
    }

    @Override
    public void setOnItemSelectedItemCategories(int postion) {

    }

    @Override
    public void onClickListListener(int i) {
        closeDrawerLeft();
        if (!(mFragment instanceof Fragment_News)) {
            mFragment = new Fragment_News();
            changeFragment(mFragment);
            setSelectedItem(R.id.item_news);
        }
    }

    @Override
    public void onClickGridListener(int i) {

    }

    @Override
    public void setOnAceptListener() {
         finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {
            case R.id.item_news:
                mFragment = new Fragment_News();
                setTitleToolBar(getResources().getString(R.string.news));
                changeFragment(mFragment);
                break;
            case R.id.item_personal:
                mFragment = new Fragment_Personal();
                setTitleToolBar(getResources().getString(R.string.personal));
                changeFragment(mFragment);
                break;
        }
        return true;
    }
}