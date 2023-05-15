package com.yang.study_coroutine;

import androidx.annotation.NonNull;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/**
 * Create by Yang Yang on 2023/5/15
 */
public class TestCoroutine {

    public static void main(String[] args) {
        CoroutineDispatcherKt.hello(new Continuation<Integer>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o) {

            }
        });
    }
}
