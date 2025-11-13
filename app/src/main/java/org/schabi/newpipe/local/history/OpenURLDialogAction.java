package org.schabi.newpipe.local.history;

import org.schabi.newpipe.player.Player;
import org.schabi.newpipe.player.PlayerType;
import org.schabi.newpipe.util.NavigationHelper;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OpenURLDialogAction implements OpenDialogAction {
    private final ActivityWithDisposables activity;
    public OpenURLDialogAction(ActivityWithDisposables activity) {
        this.activity = activity;
    }
    @Override
    public void open(String url) {
        activity.disposables().add(Observable
                .fromCallable(() -> NavigationHelper.getIntentByLink(activity.activity(), url))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activity::startActivity, throwable -> {}));
    }
    @Override
    public void enqueue(String url) {
        activity.disposables().add(Observable
                .fromCallable(() -> NavigationHelper.getIntentByLink(activity.activity(), url))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(intent -> {

                    intent.putExtra(Player.PLAYER_TYPE, PlayerType.AUDIO);
                    activity.startActivity(intent);
                }, throwable -> {}));
    }
}
