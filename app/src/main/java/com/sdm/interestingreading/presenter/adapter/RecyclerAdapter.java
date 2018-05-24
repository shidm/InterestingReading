package com.sdm.interestingreading.presenter.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.sdm.interestingreading.R;
import com.sdm.interestingreading.model.pojo.AudioEntity;
import com.sdm.interestingreading.model.pojo.CommentEntity;
import com.sdm.interestingreading.model.pojo.PictureEntity;
import com.sdm.interestingreading.model.pojo.TextEntity;
import com.sdm.interestingreading.model.pojo.VideoEntity;
import com.sdm.interestingreading.view.BaseInterface;
import com.sdm.interestingreading.view.IAudioFragment;
import com.sdm.interestingreading.view.IPictureFragment;
import com.sdm.interestingreading.view.ITextFragment;
import com.sdm.interestingreading.view.IVideoFragment;
import com.sdm.interestingreading.view.fragment.PictureDetailFragment;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by shidongming on 18-2-10.
 */

public class RecyclerAdapter<T> extends RecyclerView.Adapter {

    private List<T> list;
    private Context context;
    private String whichMsg;
    private BaseInterface back;


    public RecyclerAdapter(Context context, List<T> list, String whichMsg, BaseInterface back) {
        this.context = context;
        this.list = list;
        this.whichMsg = whichMsg;
        this.back = back;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder = null;
        switch (whichMsg) {
            case "段子":
                view = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
                viewHolder = new TextViewHolder(view);
                break;
            case "图片":
                view = LayoutInflater.from(context).inflate(R.layout.item_picture, parent, false);
                viewHolder = new PictureViewHolder(view);
                break;
            case "视频":
                view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
                viewHolder = new VideoViewHolder(view);
                break;
            case "声音":
                view = LayoutInflater.from(context).inflate(R.layout.item_audio, parent, false);
                viewHolder = new AudioViewHolder(view);
                break;
            case "评论":
                view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
                viewHolder = new CommentViewHolder(view);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (whichMsg) {
            case "段子":
                ((TextViewHolder) holder).username.setText(((TextEntity) list.get(position)).getUserName());
                ((TextViewHolder) holder).userCreateTime.setText(((TextEntity) list.get(position)).getUserCreateTime());
                ((TextViewHolder) holder).joke_content.setText(((TextEntity) list.get(position)).getJoke_content());
                ((TextViewHolder) holder).joke_like_num.setText(((TextEntity) list.get(position)).getJoke_like_num());
                ((TextViewHolder) holder).joke_unlike_num.setText(((TextEntity) list.get(position)).getJoke_unlike_num());
                ((TextViewHolder) holder).joke_comment_num.setText(((TextEntity) list.get(position)).getJoke_comment_num());
                Picasso.with(context).load(((TextEntity) list.get(position)).getUserIcon())
                        .placeholder(R.drawable.wait)
                        .error(R.drawable.wait)
                        .into(((TextViewHolder) holder).userIcon);

                final TextViewHolder h = (TextViewHolder) holder;
                h.like.setColorFilter(R.color.black);
                h.unlike.setColorFilter(R.color.black);
                h.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h.like.setColorFilter(context.getResources().getColor(R.color.title));
                        h.unlike.setClickable(false);
                        int num = Integer.valueOf(h.joke_like_num.getText().toString().trim());
                        h.joke_like_num.setText(String.valueOf(++num));
                    }
                });

                h.unlike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h.unlike.setColorFilter(context.getResources().getColor(R.color.title));
                        h.like.setClickable(false);
                        int num = Integer.valueOf(h.joke_unlike_num.getText().toString().trim());
                        h.joke_unlike_num.setText(String.valueOf(++num));
                    }
                });

                h.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                        ((ITextFragment) back).showComment("段子", ((TextEntity) list.get(position)).getJoke_id(), ((TextEntity) list.get(position)).getUserIcon());
                    }
                });

                h.joke_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                    }
                });
                break;
            case "图片":
                ((PictureViewHolder) holder).username.setText(((PictureEntity) list.get(position)).getUserName());
                ((PictureViewHolder) holder).userCreateTime.setText(((PictureEntity) list.get(position)).getUserCreateTime());
                ((PictureViewHolder) holder).pic_content.setText(((PictureEntity) list.get(position)).getPicture_content());
                ((PictureViewHolder) holder).pic_like_num.setText(((PictureEntity) list.get(position)).getPicture_like_num());
                ((PictureViewHolder) holder).pic_unlike_num.setText(((PictureEntity) list.get(position)).getPicture_unlike_num());
                ((PictureViewHolder) holder).pic_comment_num.setText(((PictureEntity) list.get(position)).getPicture_comment_num());
                if (!TextUtils.isEmpty(((PictureEntity) list.get(position)).getUserIcon())) {
                    Picasso.with(context).load(((PictureEntity) list.get(position)).getUserIcon())
                            .placeholder(R.drawable.wait)
                            .error(R.drawable.wait)
                            .into(((PictureViewHolder) holder).userIcon);
                }

                final String is_gif = ((PictureEntity) list.get(position)).getIs_gif();
                if ("0".equals(is_gif)) {
                    ((PictureViewHolder) holder).pic_content_img.setAdjustViewBounds(false);
                    ((PictureViewHolder) holder).pic_content_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    if (Integer.valueOf(((PictureEntity) list.get(position)).getPicture_height()) > dp2px(context, 360)) {
                        ((PictureViewHolder) holder).pic_content_img.getLayoutParams().height = dp2px(context, 360);
                    }

                    //显示自定义区域图片
//                    BitmapDrawable bitmapDrawable=(BitmapDrawable)((PictureViewHolder)holder).pic_content_img.getDrawable();
//                    ((PictureViewHolder)holder).pic_content_img.setImageBitmap(Bitmap.createBitmap(bitmapDrawable.getBitmap(),0,0,width,height));
                } else {
                    ((PictureViewHolder) holder).pic_content_img.setAdjustViewBounds(true);
                    ((PictureViewHolder) holder).pic_content_img.setScaleType(ImageView.ScaleType.FIT_XY);
                    ((PictureViewHolder) holder).pic_content_img.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
                final String img_url = ((PictureEntity) list.get(position)).getPicture_content_img();
                Glide.with(context).load(img_url)
                        .into(((PictureViewHolder) holder).pic_content_img);

                ((PictureViewHolder) holder).pic_content_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((IPictureFragment) back).showDetailPicture((PictureEntity) list.get(position));
                    }
                });
                final PictureViewHolder h1 = (PictureViewHolder) holder;
                h1.like.setColorFilter(R.color.black);
                h1.unlike.setColorFilter(R.color.black);
                h1.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h1.like.setColorFilter(context.getResources().getColor(R.color.title));
                        h1.unlike.setClickable(false);
                        int num = Integer.valueOf(h1.pic_like_num.getText().toString().trim());
                        h1.pic_like_num.setText(String.valueOf(++num));
                    }
                });

                h1.unlike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h1.unlike.setColorFilter(context.getResources().getColor(R.color.title));
                        h1.like.setClickable(false);
                        int num = Integer.valueOf(h1.pic_unlike_num.getText().toString().trim());
                        h1.pic_unlike_num.setText(String.valueOf(++num));
                    }
                });

                h1.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                        ((IPictureFragment) back).showComment("图片", ((PictureEntity) list.get(position)).getPicture_id(), ((PictureEntity) list.get(position)).getUserIcon());
                    }
                });

                h1.pic_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                    }
                });
                break;
            case "视频":
                final VideoViewHolder h2 = (VideoViewHolder) holder;

                ((VideoViewHolder) holder).username.setText(((VideoEntity) list.get(position)).getUserName());
                ((VideoViewHolder) holder).userCreateTime.setText(((VideoEntity) list.get(position)).getUserCreateTime());
                ((VideoViewHolder) holder).video_content.setText(((VideoEntity) list.get(position)).getVideo_content());
                ((VideoViewHolder) holder).video_like_num.setText(((VideoEntity) list.get(position)).getVideo_like_num());
                ((VideoViewHolder) holder).video_unlike_num.setText(((VideoEntity) list.get(position)).getVideo_unlike_num());
                ((VideoViewHolder) holder).video_comment_num.setText(((VideoEntity) list.get(position)).getVideo_comment_num());
                if (!TextUtils.isEmpty(((VideoEntity) list.get(position)).getUserIcon())) {
                    Picasso.with(context).load(((VideoEntity) list.get(position)).getUserIcon())
                            .placeholder(R.drawable.wait)
                            .error(R.drawable.wait)
                            .into(((VideoViewHolder) holder).userIcon);
                }
                String video_img_url = ((VideoEntity) list.get(position)).getVideo_img();
                Glide.with(context).load(video_img_url)
                        .into(((VideoViewHolder) holder).video_content_img);

                h2.play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (h2.videoView.isPlaying()) {
                            h2.videoView.pause();
                            h2.play.setImageResource(R.drawable.play);
                            h2.play.setColorFilter(context.getResources().getColor(R.color.title));
                        } else {
                            h2.play.setImageResource(R.drawable.stop);
                            h2.play.setColorFilter(context.getResources().getColor(R.color.title));
                            if (h2.videoView.getCurrentPosition() != 0) {
                                h2.videoView.start();
                            } else {
                                h2.video_content_img.setVisibility(View.INVISIBLE);
                                h2.videoView.setVisibility(View.VISIBLE);
                                loadView(((VideoEntity) list.get(position)).getVideo_content_url(), h2.videoView, h2.video_seekbar
                                        , h2.thisTime, h2.totalTime, h2.video_content_img, h2.play);
                            }
                        }
                    }
                });
                h2.play.setColorFilter(context.getResources().getColor(R.color.title));
                h2.like.setColorFilter(R.color.black);
                h2.unlike.setColorFilter(R.color.black);
                h2.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h2.like.setColorFilter(context.getResources().getColor(R.color.title));
                        h2.unlike.setClickable(false);
                        int num = Integer.valueOf(h2.video_like_num.getText().toString().trim());
                        h2.video_like_num.setText(String.valueOf(++num));
                    }
                });

                h2.unlike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h2.unlike.setColorFilter(context.getResources().getColor(R.color.title));
                        h2.like.setClickable(false);
                        int num = Integer.valueOf(h2.video_unlike_num.getText().toString().trim());
                        h2.video_unlike_num.setText(String.valueOf(++num));
                    }
                });

                h2.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                        ((IVideoFragment) back).showComment("视频", ((VideoEntity) list.get(position)).getVideo_id(), ((VideoEntity) list.get(position)).getUserIcon());
                    }
                });

                h2.video_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                    }
                });
                break;
            case "声音":
                final MediaPlayer mMediaPlayer = new MediaPlayer();

                ((AudioViewHolder) holder).username.setText(((AudioEntity) list.get(position)).getUserName());
                ((AudioViewHolder) holder).userCreateTime.setText(((AudioEntity) list.get(position)).getUserCreateTime());
                ((AudioViewHolder) holder).audio_content.setText(((AudioEntity) list.get(position)).getAudio_content());
                ((AudioViewHolder) holder).audio_like_num.setText(((AudioEntity) list.get(position)).getAudio_like_num());
                ((AudioViewHolder) holder).audio_unlike_num.setText(((AudioEntity) list.get(position)).getAudio_unlike_num());
                ((AudioViewHolder) holder).audio_comment_num.setText(((AudioEntity) list.get(position)).getAudio_comment_num());
                ((AudioViewHolder) holder).audio_time.setText(getTime(((AudioEntity) list.get(position)).getAudio_time()));
                ((AudioViewHolder) holder).audio_this_time.setText("00:00");
                if (!TextUtils.isEmpty(((AudioEntity) list.get(position)).getUserIcon())) {
                    Picasso.with(context).load(((AudioEntity) list.get(position)).getUserIcon())
                            .placeholder(R.drawable.wait)
                            .error(R.drawable.wait)
                            .into(((AudioViewHolder) holder).userIcon);
                }
                String audio_img_url = ((AudioEntity) list.get(position)).getAudio_content_img();
                Glide.with(context).load(audio_img_url)
                        .into(((AudioViewHolder) holder).audio_content_img);
                final AudioViewHolder h3 = (AudioViewHolder) holder;

                h3.play.setColorFilter(context.getResources().getColor(R.color.title));
                h3.play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mMediaPlayer.isPlaying()) {
                            mMediaPlayer.pause();
                            h3.play.setImageResource(R.drawable.play);
                            h3.play.setColorFilter(context.getResources().getColor(R.color.title));
                        } else {
                            h3.play.setImageResource(R.drawable.stop);
                            h3.play.setColorFilter(context.getResources().getColor(R.color.title));
                            if (!"00:00".equals(h3.audio_this_time.getText().toString().trim())) {
                                mMediaPlayer.start();
                            } else
                                loadAudio(((AudioEntity) list.get(position)).getAudio_content_url(), h3, mMediaPlayer);
                        }
                    }
                });
                h3.like.setColorFilter(R.color.black);
                h3.unlike.setColorFilter(R.color.black);
                h3.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h3.like.setColorFilter(context.getResources().getColor(R.color.title));
                        h3.unlike.setClickable(false);
                        int num = Integer.valueOf(h3.audio_like_num.getText().toString().trim());
                        h3.audio_like_num.setText(String.valueOf(++num));
                    }
                });

                h3.unlike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        h3.unlike.setColorFilter(context.getResources().getColor(R.color.title));
                        h3.like.setClickable(false);
                        int num = Integer.valueOf(h3.audio_unlike_num.getText().toString().trim());
                        h3.audio_unlike_num.setText(String.valueOf(++num));
                    }
                });

                h3.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                        ((IAudioFragment) back).showComment("声音", ((AudioEntity) list.get(position)).getAudio_id(), ((AudioEntity) list.get(position)).getUserIcon());
                    }
                });

                h3.audio_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转
                    }
                });
                break;

            case "评论":
                ((CommentViewHolder) holder).username.setText(((CommentEntity) list.get(position)).getUser().getUser_name());
                ((CommentViewHolder) holder).comment_content.setText(((CommentEntity) list.get(position)).getComment_content());
                ((CommentViewHolder) holder).comment_like_num.setText(((CommentEntity) list.get(position)).getComment_like_num());

                if (!TextUtils.isEmpty(((CommentEntity) list.get(position)).getUser().getUser_icon())) {
                    Log.d("Adapter", "评论: "+((CommentEntity) list.get(position)).getUser().getUser_icon());
                    Picasso.with(context).load(((CommentEntity) list.get(position)).getUser().getUser_icon())
                            .placeholder(R.drawable.wait)
                            .error(R.drawable.wait)
                            .into(((CommentViewHolder) holder).comment_userIcon);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static String getTime(String time) {
        int t = Integer.valueOf(time);
        int m = t / 60;
        int s = t % 60;
        String s_m = null;
        String s_s = null;
        if (m < 10) {
            s_m = "0" + m;
        } else
            s_m = String.valueOf(m);
        if (s < 10) {
            s_s = "0" + s;
        } else
            s_s = String.valueOf(s);
        return s_m + ":" + s_s;
    }

    public void loadAudio(String audioURL, final AudioViewHolder h, final MediaPlayer mMediaPlayer) {
        Log.d("Adapter", "loadAudio: "+audioURL);
        h.audio_this_time.setVisibility(View.VISIBLE);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                h.audio_this_time.setText(getTime(String.valueOf(msg.obj)));
            }
        };

        final Timer mTimer = new Timer();
        try {
            mMediaPlayer.setDataSource(audioURL);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    h.seekBar.setMax(mediaPlayer.getDuration());
                    h.audio_time.setText(getTime(String.valueOf(mediaPlayer.getDuration() / 1000)));

                    TimerTask mTimerTask = new TimerTask() {
                        @Override
                        public void run() {
                            int crrunt = mediaPlayer.getCurrentPosition();
                            h.seekBar.setProgress(crrunt);
                            Message message = Message.obtain();
                            message.obj = crrunt / 1000;
                            handler.sendMessage(message);
                        }
                    };
                    mTimer.schedule(mTimerTask, 0, 10);
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mTimer.cancel();
                    h.seekBar.setProgress(0);
                    mediaPlayer.stop();
                    mediaPlayer.seekTo(0);
                    h.audio_this_time.setText("00:00");
                    h.audio_this_time.setVisibility(View.INVISIBLE);
                    h.play.setImageResource(R.drawable.play);
                    h.play.setColorFilter(context.getResources().getColor(R.color.title));
                }
            });

            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String path, final VideoView videoview, final SeekBar seekBar, final TextView thisTime, final TextView totalTime
            , final ImageView img, final ImageView play) {
        final boolean[] isUserDo = {false};
        thisTime.setVisibility(View.VISIBLE);
        Uri uri = Uri.parse(path);
        videoview.setVideoURI(uri);
//        videoview.setMediaController(new MediaController(context));
        videoview.start();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                thisTime.setText(getTime(String.valueOf(msg.obj)));
            }
        };
        final Timer timer = new Timer();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (isUserDo[0]) {
                    thisTime.setText(getTime(String.valueOf(i / 1000)));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("Adapter", "onStartTrackingTouch: ");
                videoview.pause();
                isUserDo[0] = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Log.d("Adapter", "onStopTrackingTouch: " + progress);
                videoview.seekTo(seekBar.getProgress());
                videoview.start();
                isUserDo[0] = false;
            }
        });
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //         mp.setLooping(true);
                //mp.start();// 播放
                int allTime = mp.getDuration();
                Log.d("Adapter", "onPrepared: " + allTime);
                seekBar.setMax(allTime);
                totalTime.setText(getTime(String.valueOf(allTime / 1000)));
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int crruntTime = videoview.getCurrentPosition();
                        Message message = Message.obtain();
                        message.obj = crruntTime / 1000;
                        handler.sendMessage(message);
                        seekBar.setProgress(crruntTime);
                    }
                }, 10, 10);
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
                videoview.seekTo(0);
                seekBar.setProgress(0);
                videoview.setVisibility(View.INVISIBLE);
                img.setVisibility(View.VISIBLE);
                play.setImageResource(R.drawable.play);
                play.setColorFilter(context.getResources().getColor(R.color.title));
                thisTime.setText("00:00");
                thisTime.setVisibility(View.INVISIBLE);
            }
        });
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private CircleImageView userIcon;
        private TextView userCreateTime;
        private TextView joke_content;
        private TextView joke_like_num;
        private TextView joke_unlike_num;
        private TextView joke_comment_num;
        private ImageView like;
        private ImageView unlike;
        private ImageView comment;


        public TextViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.joke_user);
            userIcon = itemView.findViewById(R.id.joke_icon);
            userCreateTime = itemView.findViewById(R.id.joke_createtime);
            joke_content = itemView.findViewById(R.id.joke_content);
            joke_like_num = itemView.findViewById(R.id.joke_like_num);
            joke_unlike_num = itemView.findViewById(R.id.joke_unlike_num);
            joke_comment_num = itemView.findViewById(R.id.joke_comment_num);

            like = itemView.findViewById(R.id.joke_like);
            unlike = itemView.findViewById(R.id.joke_unlike);
            comment = itemView.findViewById(R.id.joke_comment);
        }
    }

    class PictureViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private CircleImageView userIcon;
        private TextView userCreateTime;
        private TextView pic_content;
        private TextView pic_like_num;
        private TextView pic_unlike_num;
        private TextView pic_comment_num;
        private ImageView like;
        private ImageView unlike;
        private ImageView comment;
        private ImageView pic_content_img;


        public PictureViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.pic_user);
            userIcon = itemView.findViewById(R.id.pic_icon);
            userCreateTime = itemView.findViewById(R.id.pic_createtime);
            pic_content = itemView.findViewById(R.id.pic_content);
            pic_like_num = itemView.findViewById(R.id.pic_like_num);
            pic_unlike_num = itemView.findViewById(R.id.pic_unlike_num);
            pic_comment_num = itemView.findViewById(R.id.pic_comment_num);
            pic_content_img = itemView.findViewById(R.id.pic_content_img);

            like = itemView.findViewById(R.id.pic_like);
            unlike = itemView.findViewById(R.id.pic_unlike);
            comment = itemView.findViewById(R.id.pic_comment);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private CircleImageView userIcon;
        private TextView userCreateTime;
        private TextView video_content;
        private TextView video_like_num;
        private TextView video_unlike_num;
        private TextView video_comment_num;
        private ImageView like;
        private ImageView unlike;
        private ImageView comment;
        private ImageView video_content_img;
        private VideoView videoView;
        private ImageView play;
        private SeekBar video_seekbar;
        private TextView totalTime;
        private TextView thisTime;


        public VideoViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.video_user);
            userIcon = itemView.findViewById(R.id.video_icon);
            userCreateTime = itemView.findViewById(R.id.video_createtime);
            video_content = itemView.findViewById(R.id.video_content);
            video_like_num = itemView.findViewById(R.id.video_like_num);
            video_unlike_num = itemView.findViewById(R.id.video_unlike_num);
            video_comment_num = itemView.findViewById(R.id.video_comment_num);
            video_content_img = itemView.findViewById(R.id.video_content_img);
            videoView = itemView.findViewById(R.id.video_view);
            play = itemView.findViewById(R.id.video_play);
            video_seekbar = itemView.findViewById(R.id.video_seekbar);
            totalTime = itemView.findViewById(R.id.video_total_time);
            thisTime = itemView.findViewById(R.id.video_this_time);

            like = itemView.findViewById(R.id.video_like);
            unlike = itemView.findViewById(R.id.video_unlike);
            comment = itemView.findViewById(R.id.video_comment);
        }
    }

    class AudioViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private CircleImageView userIcon;
        private TextView userCreateTime;
        private TextView audio_content;
        private TextView audio_like_num;
        private TextView audio_unlike_num;
        private TextView audio_comment_num;
        private ImageView like;
        private ImageView unlike;
        private ImageView comment;
        private ImageView audio_content_img;
        private TextView audio_time;
        private TextView audio_this_time;
        private ImageView play;
        private SeekBar seekBar;


        public AudioViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.audio_user);
            userIcon = itemView.findViewById(R.id.audio_icon);
            userCreateTime = itemView.findViewById(R.id.audio_createtime);
            audio_content = itemView.findViewById(R.id.audio_content);
            audio_like_num = itemView.findViewById(R.id.audio_like_num);
            audio_unlike_num = itemView.findViewById(R.id.audio_unlike_num);
            audio_comment_num = itemView.findViewById(R.id.audio_comment_num);
            audio_content_img = itemView.findViewById(R.id.audio_content_img);
            audio_time = itemView.findViewById(R.id.audio_total_time);
            audio_this_time = itemView.findViewById(R.id.audio_this_time);
            play = itemView.findViewById(R.id.audio_play);
            seekBar = itemView.findViewById(R.id.audio_seekbar);

            like = itemView.findViewById(R.id.audio_like);
            unlike = itemView.findViewById(R.id.audio_unlike);
            comment = itemView.findViewById(R.id.audio_comment);
        }
    }


    class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private CircleImageView comment_userIcon;
        private TextView comment_content;
        private TextView comment_like_num;


        public CommentViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.comment_user);
            comment_content = itemView.findViewById(R.id.comment_content);
            comment_like_num = itemView.findViewById(R.id.comment_like_num);
            comment_userIcon = itemView.findViewById(R.id.comment_icon);
        }
    }
}
