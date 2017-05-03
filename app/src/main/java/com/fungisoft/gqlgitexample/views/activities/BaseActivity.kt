package com.fungisoft.gqlgitexample.views.activities

import android.app.Activity
import android.os.Bundle

import com.fungisoft.gqlgitexample.dialogs.LoadDialog
import com.fungisoft.gqlgitexample.dialogs.WarningDialog
import com.fungisoft.gqlgitexample.views.BaseView

abstract class BaseActivity : Activity(), BaseView {
    private var progressDialog: LoadDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressDialog = LoadDialog(this)
    }

    override fun showProgress() {
        progressDialog?.show()
    }

    override fun hideProgress() {
        progressDialog?.hide()
    }

    override fun showError(message: String) {
        WarningDialog.showAlert(this, message)
    }
}
