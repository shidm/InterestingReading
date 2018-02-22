package com.sdm.interestingreading.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.sdm.interestingreading.R;
import com.sdm.interestingreading.presenter.IGetDataPresenter;
import com.sdm.interestingreading.presenter.adapter.RecyclerAdapter;
import com.sdm.interestingreading.model.pojo.TextEntity;
import com.sdm.interestingreading.presenter.impl.GetDataPresenterImpl;
import com.sdm.interestingreading.view.ITextFragment;
import com.sdm.interestingreading.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TextFragment extends Fragment implements ITextFragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private int lastX,lastY;
    private List<TextEntity> list = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_text, container, false);
        init();
        return view;
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new RecyclerAdapter<TextEntity>(getContext(), list, "段子");

        recyclerView = view.findViewById(R.id.textrecycler);
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
                        MainActivity.viewPager.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int deltaX = x-lastX;
                        int deltaY = y-lastY;
                        if(Math.abs(deltaX)>Math.abs(deltaY)){
                            MainActivity.viewPager.requestDisallowInterceptTouchEvent(false);
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
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addData();
        IGetDataPresenter presenter = new GetDataPresenterImpl(this);
    }

    private void addData() {
        for (int i = 0; i < 70; i++) {
            list.add(new TextEntity());
        }
        adapter.update(list);
    }


}
