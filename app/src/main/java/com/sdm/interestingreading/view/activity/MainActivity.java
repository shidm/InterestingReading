package com.sdm.interestingreading.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.sdm.interestingreading.R;
import com.sdm.interestingreading.presenter.adapter.ViewpagerAdapter;
import com.sdm.interestingreading.utils.NetworkRequest;
import com.sdm.interestingreading.view.fragment.AudioFragment;
import com.sdm.interestingreading.view.fragment.ContentFragment;
import com.sdm.interestingreading.view.fragment.PersonCenterFragment;
import com.sdm.interestingreading.view.fragment.PictureFragment;
import com.sdm.interestingreading.view.fragment.TextFragment;
import com.sdm.interestingreading.view.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MainActivity-->";

    private static List<Fragment> fragments = new ArrayList<>();

    private static final String PERMISSIONS[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PERMISSION_CODE = 123;

    public static FrameLayout layout;
    public static RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);

        layout = findViewById(R.id.frag_layout);

        checkPermissions();

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
//        BadgeItem numberBadgeItem = new BadgeItem()
//                .setBorderWidth(4)
//                .setBackgroundColor(Color.RED)
//                .setText("5")
//                .setHideOnSelect(true);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.content, "内容").setActiveColorResource(R.color.title))
                .addItem(new BottomNavigationItem(R.drawable.my, "我的").setActiveColorResource(R.color.title))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            if (!fragments.get(i).isAdded()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.layFrame, fragments.get(i));
                transaction.commit();
            }
        }
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        hide(0, getSupportFragmentManager().beginTransaction());
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ContentFragment.newInstance("内容"));
        fragments.add(PersonCenterFragment.newInstance("我的"));
        return fragments;
    }

    @Override
    public void onTabSelected(int position) {
        hide(position, getSupportFragmentManager().beginTransaction());

    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {

    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,PERMISSIONS,PERMISSION_CODE);
            }
        }
    }

    private void hide(int a, FragmentTransaction t){
        Log.d(TAG, "hide: "+a);
        for (int i = 0; i<fragments.size(); i++) {
            Log.d(TAG, "hide: position"+i);
            t.hide(fragments.get(i));
        }
        t.show(fragments.get(a));
        t.commit();
    }
}
