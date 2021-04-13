package com.example.mobilemoviebase.ui.about;

import android.app.AppComponentFactory;
import android.os.Bundle;

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
    public void ShowAboutScreen() {
        aboutPresenter.attachScreen(this);
    }
}
