package com.sdm.interestingreading.view.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.AudioEntity;
import com.sdm.interestingreading.model.pojo.VideoEntity;
import com.sdm.interestingreading.presenter.IGetDataPresenter;
import com.sdm.interestingreading.presenter.adapter.RecyclerAdapter;
import com.sdm.interestingreading.presenter.impl.GetDataPresenterImpl;
import com.sdm.interestingreading.view.IVideoFragment;
import com.sdm.interestingreading.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shidongming on 18-2-10.
 */

public class VideoFragment extends Fragment implements IVideoFragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private int lastX,lastY;
    private List<VideoEntity> list = new ArrayList();
    private FloatingActionButton jumpFirstBtn;
    private static boolean isJumpToFirstShow = false;
    public static CommentFragment commentFragment;

    private static String TAG = "VideoFrag";

    private int page = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
            Log.d(TAG, "handleMessage: ");
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        init();
        return view;
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new RecyclerAdapter<VideoEntity>(getContext(), list, "视频", this);

        jumpFirstBtn = view.findViewById(R.id.video_jump_First);
        jumpFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        recyclerView = view.findViewById(R.id.video_recycler);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //viewpager是否拦截滑动事件

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        ContentFragment.viewPager.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = x-lastX;
                        int deltaY = y-lastY;
                        if(Math.abs(deltaX)>Math.abs(deltaY)){
                            ContentFragment.viewPager.requestDisallowInterceptTouchEvent(false);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                lastX = x;
                lastY = y;
                return false;
            }
        });
        IGetDataPresenter presenter = new GetDataPresenterImpl(this);
        presenter.getVideoData(String.valueOf(page));

        final IGetDataPresenter p = presenter;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    int refrushPosition = list.size() - 5;
                    if (lastItemPosition >= 7 && !isJumpToFirstShow) {
                        jumpFirstBtn.setVisibility(View.VISIBLE);
                        ObjectAnimator.ofFloat(jumpFirstBtn, "translationX", dp2px(getContext(), 80), 0)
                                .setDuration(300)
                                .start();
                        isJumpToFirstShow = true;
                    } else if (lastItemPosition < 7 && isJumpToFirstShow){
                        isJumpToFirstShow = false;
                        ObjectAnimator animator = ObjectAnimator.ofFloat(jumpFirstBtn, "translationX", 0, dp2px(getContext(), 80));
                        animator.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                jumpFirstBtn.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                        animator.setDuration(300).start();

                    }
                    if (lastItemPosition >= refrushPosition) {
                        p.getVideoData(String.valueOf(++page));
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void update(List<VideoEntity> list) {
        this.list.addAll(list);
        handler.sendMessage(Message.obtain());
    }

    @Override
    public void showComment(String which, String data_id, String usericon) {
        commentFragment = CommentFragment.newInstance(which, data_id, usericon);
        MainActivity.layout.setVisibility(View.VISIBLE);
        MainActivity.mainLayout.setVisibility(View.GONE);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frag_layout, commentFragment);
        transaction.commit();
    }
}
