package com.example.busstation.callback;

import javax.security.auth.callback.Callback;

public interface RecyclerViewCallback<T> extends Callback {
    void onComplete(T result);
}
