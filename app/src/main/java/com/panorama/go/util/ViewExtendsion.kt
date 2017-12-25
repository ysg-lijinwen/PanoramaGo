package com.panorama.go.util

import android.content.Context
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import org.jetbrains.anko.layoutInflater

/**
 * Description:
 * Created by Kevin.Li on 2017-12-25.
 */
fun View.singleClick(l: (android.view.View?) -> Unit) {
    setOnClickListener(l)
}

fun ArrayList<View>.singleClick(l: (android.view.View?) -> Unit) {
    this.forEach {
        it.setOnClickListener(l)
    }
}

fun View.visible(show: Boolean = true) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return context.layoutInflater.inflate(layoutRes, this, attachToRoot)
}

fun EditText.hideSoftInputFromWindow(){
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
}
