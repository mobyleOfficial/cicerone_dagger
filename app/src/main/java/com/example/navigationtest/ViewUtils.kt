package com.example.navigationtest

import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

fun View.clicks(): Observable<Unit> = clicks().throttleFirst(300, TimeUnit.MILLISECONDS)