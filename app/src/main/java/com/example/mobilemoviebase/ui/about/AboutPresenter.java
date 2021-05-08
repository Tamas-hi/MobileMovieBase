package com.example.mobilemoviebase.ui.about;

import com.example.mobilemoviebase.interactor.movies.event.GetMoviesEvent;
import com.example.mobilemoviebase.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class AboutPresenter extends Presenter<AboutScreen> {

    @Inject
    public AboutPresenter(){

    }

    @Override
    public void attachScreen(AboutScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetMoviesEvent event) {
        if (event.getMessage() != null) {
            screen.showError(event.getMessage());
        }
    }
}
