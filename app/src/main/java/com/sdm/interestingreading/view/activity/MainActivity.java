package com.sdm.interestingreading.view.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.presenter.adapter.ViewpagerAdapter;
import com.sdm.interestingreading.view.fragment.AudioFragment;
import com.sdm.interestingreading.view.fragment.PictureFragment;
import com.sdm.interestingreading.view.fragment.TextFragment;
import com.sdm.interestingreading.view.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity-->";
    private Toolbar toolbar;
    public static ViewPager viewPager;
    private View scrollView;
    private List<Fragment> list = new ArrayList<>();
    private float offset = 0;
    private float lastEndPosition = 0;
    private float floatOffset = 0;
    private int whichPosition = 0;

    private int lastX,lastY;
    private static int TAB_MARGIN_DIP = 70;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
    }



    private void init() {
        toolbar = findViewById(R.id.toolar);
        toolbar.setMinimumHeight(getStatusBarHeight());
        setSupportActionBar(toolbar);

        scrollView = findViewById(R.id.floatscrollview);
        offset = getWindowWidth() / 8 - scrollView.getLayoutParams().width / 2;
        setScrollViewPosition((int) offset);

        list.add(new TextFragment());
        list.add(new PictureFragment());
        list.add(new VideoFragment());
        list.add(new AudioFragment());

        viewPager = findViewById(R.id.viewpager);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(this);
    }

    private int getWindowWidth() {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    private void setScrollViewPosition(int positionXY) {
        AppBarLayout.MarginLayoutParams layoutParams = new AppBarLayout.MarginLayoutParams(scrollView.getLayoutParams());
        layoutParams.leftMargin = positionXY;
        scrollView.setLayoutParams(new AppBarLayout.LayoutParams(layoutParams));
    }

    //获取状态栏高度
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled: " + position + "\tpositionOffset：" + positionOffset);
        if (whichPosition == position) {
            floatOffset = positionOffset * getWindowWidth() / 4
                    + offset
                    + getWindowWidth() * position / 4;
            Log.d(TAG, "floatOffset: " + floatOffset);
            setScrollViewPosition((int) floatOffset);
            if (floatOffset > getWindowWidth() / 4) {
                setTittleColor(1);
            } else {
                setTittleColor(0);
            }
        } else {
            whichPosition = position;
            lastEndPosition = floatOffset;
        }
    }

    private void setTittleColor(int i) {
    }

    @Override
    public void onPageSelected(int position) {
        viewPager.setCurrentItem(position);
        switch (position) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;

            case 3:

                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
