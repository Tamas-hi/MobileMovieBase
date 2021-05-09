package com.example.mobilemoviebase;

import com.example.mobilemoviebase.network.NetworkModule;
import com.example.mobilemoviebase.ui.UIModule;
import com.example.mobilemoviebase.ui.about.AboutActivity;
import com.example.mobilemoviebase.ui.details.DetailsActivity;
import com.example.mobilemoviebase.ui.movies.MovieActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UIModule.class, NetworkModule.class})
public interface MobileMovieBaseApplicationComponent {

    void inject(MovieActivity movieActivity);

    void inject(DetailsActivity detailsActivity);

    void inject(AboutActivity aboutActivity);

}
