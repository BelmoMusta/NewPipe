package org.schabi.newpipe.local.history;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public interface ActivityWithDisposables {
    CompositeDisposable disposables();

    AppCompatActivity activity();

    void startActivity(@NonNull Intent intent);
}
