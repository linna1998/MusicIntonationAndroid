package com.musicintonation.core;


import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class GeneralInstrumentAndroid implements Instrumental {
  private static final int SAMPLE_RATE = 44100;

  // https://riptutorial.com/android/example/28432/generate-tone-of-a-specific-frequency
  private static void tone(double hertz, int msecs) {

    final int numSamples = msecs / 1000 * SAMPLE_RATE;

    final double[] samples = new double[numSamples];
    final short[] buffer = new short[numSamples];
    for (int i = 0; i < numSamples; ++i) {
      samples[i] = Math.sin(2 * Math.PI * i / (SAMPLE_RATE / hertz));
      buffer[i] = (short) (samples[i] * Short.MAX_VALUE);
    }

    AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
            SAMPLE_RATE, AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT, buffer.length,
            AudioTrack.MODE_STATIC);

    audioTrack.write(buffer, 0, buffer.length);
    audioTrack.play();
  }

  @Override
  public void beep(double hertz) {
    tone(hertz, 1000);
  }
}