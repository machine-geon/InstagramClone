package com.example.homeactivity.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.homeactivity.Login.LoginActivity;
import com.example.homeactivity.R;
import com.example.homeactivity.Utils.BottomNavigationViewHelper;
import com.example.homeactivity.Utils.SectionPagerAdapter;
import com.example.homeactivity.Utils.UniversalImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0 ;

    private Context mContext = HomeActivity.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        setupFirebaseAuth();

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
        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx) ;
        Menu menu = bottomNavigationViewEx.getMenu() ;
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM) ;
        menuItem.setChecked(true) ;
    }

    /*
============================================= Firebase =================================================
 */

    /**
     * checks to see if the @param 'user' is logged in
     * @param user
     */
    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

        if(user == null){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }
    }

    /**
     *  Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                // check if the user is logged in
                checkCurrentUser(user);

                if(user != null){
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged: signed_in:"+ user.getUid());
                }else {
                    // User is singed out
                    Log.d(TAG, "onAuthStateChanged: singed_out");
                }
                //...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
