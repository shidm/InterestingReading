package com.sdm.interestingreading.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.presenter.adapter.ViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangChang on 2016/5/15.
 */
public class ContentFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private static final String TAG = "ContentFragment";
    private Toolbar toolbar;
    public static ViewPager viewPager;
    private View scrollView;
    private List<Fragment> list = new ArrayList<>();
    private float offset = 0;
    private float lastEndPosition = 0;
    private float floatOffset = 0;
    private int whichPosition = 0;
    private AppBarLayout appBarLayout;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        title = view.findViewById(R.id.app_title);
        appBarLayout = view.findViewById(R.id.appbar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float v = verticalOffset;
                float a = 1.0f + v / 168f;
                title.setAlpha(a);
            }
        });

        toolbar = view.findViewById(R.id.toolar);
        toolbar.setMinimumHeight(getStatusBarHeight());
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        scrollView = view.findViewById(R.id.floatscrollview);
        offset = getWindowWidth() / 8 - scrollView.getLayoutParams().width / 2;
        setScrollViewPosition((int) offset);

        list.add(new TextFragment());
        list.add(new PictureFragment());
        list.add(new VideoFragment());
        list.add(new AudioFragment());

        viewPager = view.findViewById(R.id.viewpager);
        ViewpagerAdapter adapter = new ViewpagerAdapter(getActivity().getSupportFragmentManager(), list);
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
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static ContentFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
