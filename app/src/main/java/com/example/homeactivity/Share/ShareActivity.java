package com.example.homeactivity.Share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.homeactivity.R;
import com.example.homeactivity.Utils.BottomNavigationViewHelper;
import com.example.homeactivity.Utils.Permissions;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity {
    private static final String TAG = "ShareActivity" ;

    //constants
    private static final int ACTIVITY_NUM = 2 ;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    private Context mContext =ShareActivity.this ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: starting.");

        if (checkPermissionArray(Permissions.PERMISSIONS)){

        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }

        //setupBotttomNavigationView();
    }

    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                ShareActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionArray(String[] permissions){
        Log.d(TAG, "checkPermissionArray: checking permissions array");

        for(int i =0; i< permissions.length; i++){
            String check = permissions[i];
            if(!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }

    /**
     * Check a single permission is it has been verified
     * @param permission
     * @return
     */
    public  boolean checkPermissions(String permission){
        Log.d(TAG, "checkPermissions: checking permission: "+ permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(ShareActivity.this, permission);
        
        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }else {
            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }

    /**
     * BottomNavigationView setup
     */
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
