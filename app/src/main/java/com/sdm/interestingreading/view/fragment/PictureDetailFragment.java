package com.sdm.interestingreading.view.fragment;

import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.PictureEntity;
import com.sdm.interestingreading.presenter.adapter.RecyclerAdapter;
import com.sdm.interestingreading.view.activity.MainActivity;

import java.io.File;

public class PictureDetailFragment extends Fragment {

    private View view;
    private ImageView back, edit, picture_detail_img_normal;
    private SubsamplingScaleImageView imageView;
    public static PictureEntity pictureEntity;

    public static CommentFragment commentFragment;

    public static PictureDetailFragment newInstance(PictureEntity entity) {
        pictureEntity = entity;
        return new PictureDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_picture_detail, container, false);
        init();
        return view;
    }

    private void init() {
        edit = view.findViewById(R.id.picture_detail_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        edit.setColorFilter(getContext().getResources().getColor(R.color.title));

        back = view.findViewById(R.id.picture_detail_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(PictureFragment.fragment);
                transaction.commit();
                MainActivity.layout.setVisibility(View.GONE);
                MainActivity.mainLayout.setVisibility(View.VISIBLE);
            }
        });
        back.setColorFilter(getContext().getResources().getColor(R.color.title));

        picture_detail_img_normal = view.findViewById(R.id.picture_detail_img_normal);
        imageView = (SubsamplingScaleImageView) view.findViewById(R.id.picture_detail_img);
        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setMinScale(1.0F);
        //最小显示比例
        imageView.setMaxScale(10.0F);

        Log.d("Pic", "init: "+pictureEntity.getPicture_height());
        if (Integer.valueOf(pictureEntity.getPicture_height()) < 1080 || "1".equals(pictureEntity.getIs_gif())) {
            imageView.setVisibility(View.GONE);
            picture_detail_img_normal.setVisibility(View.VISIBLE);
            Glide.with(this).load(pictureEntity.getPicture_content_img()).into(picture_detail_img_normal);
        } else {
            imageView.setVisibility(View.VISIBLE);
            picture_detail_img_normal.setVisibility(View.GONE);
            Glide.with(this)
                    .load(pictureEntity.getPicture_content_img())
                    .downloadOnly(new SimpleTarget<File>() {
                        @Override
                        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                            // 将保存的图片地址给SubsamplingScaleImageView,这里注意设置ImageViewState设置初始显示比例
                            imageView.setImage(ImageSource.uri(Uri.fromFile(resource)), new ImageViewState(1.0F, new PointF(0, 0), 0));
                        }
                    });
        }
    }


}
