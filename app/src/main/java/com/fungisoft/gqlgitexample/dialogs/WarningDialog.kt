package com.fungisoft.gqlgitexample.dialogs

import android.app.AlertDialog
import android.content.Context

object WarningDialog {
    fun showAlert(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)
        val dialog = builder
                .setMessage(message)
                .create()
        dialog.show()
    }
}
