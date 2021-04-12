package com.example.mobilemoviebase.ui.details;

import com.example.mobilemoviebase.ui.Presenter;

public class DetailsPresenter extends Presenter<DetailsScreen> {
    private static DetailsPresenter instance = null;

    public static DetailsPresenter getInstance() {
        if (instance == null) {
            instance = new DetailsPresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(DetailsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }
}
