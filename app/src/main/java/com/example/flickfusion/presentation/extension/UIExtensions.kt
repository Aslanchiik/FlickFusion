package com.example.flickfusion.presentation.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToastLong(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}