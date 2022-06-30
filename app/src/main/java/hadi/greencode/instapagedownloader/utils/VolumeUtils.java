package hadi.greencode.instapagedownloader.utils;

import android.content.Context;
import android.media.AudioManager;

public class VolumeUtils {

    private final AudioManager audioManager;
    private       int          maxVolume;
    private       int          currentVolume = -1;

    public VolumeUtils(Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        updateCurrentVolume();
    }

    public void volDown() {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
    }


    public void volUp() {
        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);
    }

    public int getCurrentVolumePercent() {
        int volumeLevel    = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolumeLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return (int) (((float) volumeLevel / maxVolumeLevel) * 100);
    }

    public int onVolumeSlide(float microPercent) {
//        float p = microPercent / 1.3f;
        int finalVolumeLevel = (int) (currentVolume + (microPercent * maxVolume));

        if (finalVolumeLevel > maxVolume) {
            finalVolumeLevel = maxVolume;

        } else if (finalVolumeLevel < 0) {
            finalVolumeLevel = 0;
        }

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, finalVolumeLevel, 0);

        return finalVolumeLevel;
    }

    public void updateCurrentVolume() {
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume < 0) {
            currentVolume = 0;
        }
    }
}
