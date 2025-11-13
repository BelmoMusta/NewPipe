package org.schabi.newpipe.local.history;

public interface OpenDialogAction {
    void open(String url);
    default void enqueue(String url) {
    }
}
