package com.kuforiji.lei.utils

import android.view.View

fun View.IsVisible(boolean: Boolean) = if (boolean) {
    View.VISIBLE
} else {
    View.INVISIBLE
}