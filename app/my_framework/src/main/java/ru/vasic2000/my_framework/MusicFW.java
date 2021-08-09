package ru.vasic2000.my_framework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MusicFW implements MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    boolean isSoundPrepeared = false;

    public MusicFW(AssetFileDescriptor assetFileDescriptor) {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isSoundPrepeared = true;
        mediaPlayer.setOnCompletionListener(this);
    }

    public void play(boolean looping, float volume) {
        if(mediaPlayer.isPlaying()) {
            return;
        }

        synchronized (this) {
            if(!isSoundPrepeared) {
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mediaPlayer.setLooping(looping);
            mediaPlayer.setVolume(volume, volume);
            mediaPlayer.start();
        }
    }

    public void stop() {
        mediaPlayer.stop();
        synchronized (this) {
            isSoundPrepeared = false;
        }
    }

    public void dispose() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            isSoundPrepeared = false;
        }
    }
}
