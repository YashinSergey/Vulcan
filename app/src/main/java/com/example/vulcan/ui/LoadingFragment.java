package com.example.vulcan.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vulcan.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import me.itangqi.waveloadingview.WaveLoadingView;

public class LoadingFragment extends Fragment {

    private WaveLoadingView mWaveLoadingView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading, container, false);

        mWaveLoadingView = (WaveLoadingView) view.findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        //mWaveLoadingView.setTopTitle("Top Title");
        //mWaveLoadingView.setCenterTitleColor(Color.GREEN);
        //mWaveLoadingView.setBottomTitleSize(18);
        mWaveLoadingView.setProgressValue(50);
        // mWaveLoadingView.setBorderWidth(10);
        mWaveLoadingView.setAmplitudeRatio(60);
        //mWaveLoadingView.setWaveColor(Color.RED);
        //mWaveLoadingView.setBorderColor(Color.RED);
        mWaveLoadingView.setTopTitleStrokeColor(Color.GREEN);
        mWaveLoadingView.setTopTitleStrokeWidth(3);
        mWaveLoadingView.setAnimDuration(3000);
        // mWaveLoadingView.pauseAnimation();
        // mWaveLoadingView.resumeAnimation();
        // mWaveLoadingView.cancelAnimation();
        mWaveLoadingView.startAnimation();

        ((DiscreteSeekBar) view.findViewById(R.id.seekbar_progress)).setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mWaveLoadingView.setProgressValue(value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
        return view;
    }
}
