package com.example.mobilemoviebase.ui.about;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemoviebase.R;

import javax.inject.Inject;

import static com.example.mobilemoviebase.MobileMovieBaseApplication.injector;

public class AboutActivity extends AppCompatActivity implements AboutScreen {

    @Inject
    AboutPresenter aboutPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        injector.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        aboutPresenter.attachScreen(this);
    }

    @Override
    public void onStop() {
        aboutPresenter.detachScreen();
        super.onStop();
    }

    @Override
    public void showError(String message) {
        System.out.println(message);
    }
}
