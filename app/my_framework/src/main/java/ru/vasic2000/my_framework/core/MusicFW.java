package ru.vasic2000.my_framework.core;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MusicFW implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mMediaPlayer;
    private boolean mIsSoundPrepeared;

    public MusicFW(AssetFileDescriptor assetFileDescriptor) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIsSoundPrepeared = true;
        mMediaPlayer.setOnCompletionListener(this);
    }

    public void play(boolean looping, float volume) {
        if(!mMediaPlayer.isPlaying()) {
            synchronized (this) {
                if(!mIsSoundPrepeared) {
                    try {
                        mMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mMediaPlayer.setLooping(looping);
                mMediaPlayer.setVolume(volume, volume);
                mMediaPlayer.start();
            }
        }
    }

    public void stop() {
        mMediaPlayer.stop();
        synchronized (this) {
            mIsSoundPrepeared = false;
        }
    }

    public void pause() {
        mMediaPlayer.stop();
    }

    public void dispose() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            mIsSoundPrepeared = false;
        }
    }
}
