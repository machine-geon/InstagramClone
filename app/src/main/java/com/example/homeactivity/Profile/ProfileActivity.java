package com.example.homeactivity.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homeactivity.R;
import com.example.homeactivity.Utils.BottomNavigationViewHelper;
import com.example.homeactivity.Utils.GridImageAdapter;
import com.example.homeactivity.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity" ;
    private static final int ACTIVITY_NUM = 4 ;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this ;

    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        setupBotttomNavigationView();
        setupToolbar();
        setupActivityWidgets();
        setProfileImage();

        tempGridSetup();
    }

    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://i.pinimg.com/originals/eb/da/b2/ebdab23a52a706c3d4e7a3df692cc00f.jpg");
        imgURLs.add("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-1/18766097_1357487540993465_1337253037853995013_n.jpg?_nc_cat=102&_nc_oc=AQnkp6HLWvWULOR-Grtx_hnDuxD6gfGGYXCU2uz3NYh11nRxay3v-y9pGLbqKgYFYPE&_nc_ht=scontent-icn1-1.xx&oh=9001a058b488b605636ad025b84a8a4d&oe=5E1CC7C8");
        imgURLs.add("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/19105679_2302241063334796_1590100028586343462_n.jpg?_nc_cat=108&_nc_oc=AQmr77I4e-2hadYoznKD44cwkW0Fw1ExkRz-T1N0hKqHvUH5itYY2CEVZQQVazXEDS8&_nc_ht=scontent-icn1-1.xx&oh=71f8f38f669a1d321472e544a4a90d42&oe=5E1F1DB0");
        imgURLs.add("https://post-phinf.pstatic.net/MjAxODAzMTlfMTEz/MDAxNTIxNDQ2OTU0OTU5.jqblgbUhPanTbydQUQA2yxJ0bKAz4mfituswKr1O390g.NinedvMuMH_5zHGPeZ6oinaiWyAWa_QTI1l-3jA7rfYg.JPEG/1.jpg?type=w1200");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSG0hx44lbTtcxZg_hR2Ay7eDzkID7-FfVrpA0WQtwE4IwAHMKbQg");
        imgURLs.add("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/43719917_1774224112685907_54909494727016448_n.jpg?_nc_cat=104&_nc_oc=AQkYaphINTcgD6EeyzjFO1DOgqtITCm_oX4GChWFx-PBUHMUSZWTo5U1BsxdMUKs64M&_nc_ht=scontent-icn1-1.xx&oh=b0f082bb981eec62d8bed6c9281875be&oe=5E1B4622");
        imgURLs.add("https://pds.joins.com/news/component/htmlphoto_mmdata/201905/09/c9dd60df-fe93-4cd5-beca-6da5fccf12c0.jpg");
        imgURLs.add("https://i.pinimg.com/736x/d9/6a/7f/d96a7fbe9c6004f19665e135a6b147a5.jpg");
        imgURLs.add("https://img.insight.co.kr/static/2019/06/18/700/y43ff708n3yb6panslut.jpg");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTxcols5p2gsm7E_LUiOII-408yiZo1RdjjFhz0BxaEszLjAeJoOA");
        imgURLs.add("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/23795035_830277280486128_8498404306845513272_n.jpg?_nc_cat=102&_nc_oc=AQn2qithDAQZ0B3ZnF8ctSabTnzYpXr7MhpE5N8pWWMqohbq6tpoxQJH_il750BPSho&_nc_ht=scontent-icn1-1.xx&oh=fb1b2064e6f4df8e98aa4aa5ddc8f60e&oe=5E1A1397");
        imgURLs.add("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/17634861_765294500313025_6263140390560036902_n.jpg?_nc_cat=106&_nc_oc=AQmxML-DTEDZagIqGNcWFq-9G7f5n9m51ub7BPbccejQVtoOaER_VkVAFAFH9O_4Bu8&_nc_ht=scontent-icn1-1.xx&oh=9c748695ada508ffdb7e6ab7339264d9&oe=5E2C0C70");

        setupImageGrid(imgURLs);

    }

    private  void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView) findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview,"", imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile photo");
        String imgURL = "ksassets.timeincuk.net/wp/uploads/sites/54/2016/09/android-7-0-nougat-13-2-1220x686.jpg";
        UniversalImageLoader.setImage(imgURL,profilePhoto, mProgressBar, "https://");
    }

    private void setupActivityWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    // Responsible for setting up the profile toolbar
    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings.");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
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
