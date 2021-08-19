package ru.vasic2000.my_framework.core;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;

public class AudioFW {
    private AssetManager mAssetManager;
    private SoundPool mSoundPool;

    public AudioFW(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mAssetManager = activity.getAssets();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        } else {
            mSoundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
    }

    public MusicFW newMusic(String fileName) {
        try {
            return new MusicFW(mAssetManager.openFd(fileName));
        } catch (IOException e) {
            throw new RuntimeException("No music");
        }
    }

    public SoundFW newSound(String fileName) {
        try {
            AssetFileDescriptor assetFileDescriptor = mAssetManager.openFd(fileName);
            int sound = mSoundPool.load(assetFileDescriptor, 0);
            return new SoundFW(sound, mSoundPool);
        } catch (IOException e) {
            throw new RuntimeException("No sound");
        }
    }
}
