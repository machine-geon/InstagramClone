package com.example.homeactivity.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.homeactivity.R;
import com.example.homeactivity.Utils.BottomNavigationViewHelper;
import com.example.homeactivity.Utils.SectionPagerAdapter;
import com.example.homeactivity.Utils.UniversalImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0 ;

    private Context mContext = HomeActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        initImageLoader();
        setupBotttomNavigationView();
        setupViewPager();
    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    // Responsible for adding the 3 tabs: Camera, Home, Messages
    private void setupViewPager(){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager()) ;
        adapter.addFragment(new CameraFragment()); //index 0
        adapter.addFragment(new HomeFragment()); //index 1
        adapter.addFragment(new MessagesFragment()); //index 2
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
    }

    //BottomNavigationView setup
    private void setupBotttomNavigationView(){
        Log.d(TAG, "setupBotttomNavigationView: setting up BottomNavigationView") ;
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar) ;
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx) ;
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx) ;
        Menu menu = bottomNavigationViewEx.getMenu() ;
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM) ;
        menuItem.setChecked(true) ;
    }

}
