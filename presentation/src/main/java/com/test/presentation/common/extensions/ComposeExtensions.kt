package com.test.presentation.common.extensions

import androidx.compose.ui.Modifier

fun Modifier.thenIf(condition: Boolean, modifier: Modifier.() -> Modifier) =
    if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }