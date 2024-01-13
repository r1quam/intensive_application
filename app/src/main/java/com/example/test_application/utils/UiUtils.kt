package com.example.test_application.utils

import android.content.res.Resources
import kotlin.LazyThreadSafetyMode.NONE

inline val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun <T> lazyUi(block: () -> T): Lazy<T> = lazy(mode = NONE, initializer = block)