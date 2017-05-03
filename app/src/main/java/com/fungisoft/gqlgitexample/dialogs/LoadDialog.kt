package com.fungisoft.gqlgitexample.dialogs

import android.app.ProgressDialog
import android.content.Context

import com.fungisoft.gqlgitexample.R

class LoadDialog(context: Context) : ProgressDialog(context) {
    init {
        isIndeterminate = true
        setCancelable(false)
        setTitle(R.string.loading)
    }
}