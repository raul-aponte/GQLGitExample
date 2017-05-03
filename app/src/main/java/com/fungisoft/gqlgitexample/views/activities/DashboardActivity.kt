package com.fungisoft.gqlgitexample.views.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.fungisoft.gqlgitexample.R
import com.fungisoft.gqlgitexample.ViewerInfo
import com.fungisoft.gqlgitexample.network.ApiClient
import com.fungisoft.gqlgitexample.network.response.UserInfo
import com.fungisoft.gqlgitexample.views.presenters.DashboardPresenter
import com.fungisoft.gqlgitexample.views.presenters.impl.DashboardPresenterImpl

class DashboardActivity : BaseActivity(), DashboardPresenter.DashboardView {
    private var presenter: DashboardPresenter? = null

    private var textViewName: TextView? = null
    private var buttonRepositories: Button? = null
    private var textViewInfo: TextView? = null
    private var listViewRepositories: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        linkControls()
        linkActions()

        presenter = DashboardPresenterImpl(this, ApiClient())
        presenter?.requestInfo()
    }

    override fun onStart() {
        super.onStart()
        presenter?.start()
    }

    override fun onStop() {
        super.onStop()
        presenter?.stop()
    }

    private fun linkControls() {
        textViewName = findViewById(R.id.text_view_user_name) as TextView?
        buttonRepositories = findViewById(R.id.button_repositories) as Button?
        textViewInfo = findViewById(R.id.text_view_user_info) as TextView?
        listViewRepositories = findViewById(R.id.list_view_repositories) as ListView?
    }

    private fun linkActions() {
        buttonRepositories?.setOnClickListener {
            presenter?.requestRepositories()
        }
    }

    override fun showUserInfo(userInfo: UserInfo) {
        textViewName?.text = userInfo.name
    }

    override fun showRepoInfo(viewer: ViewerInfo.Data.Viewer) {
        textViewInfo?.text = "Name: ${viewer.name()}\nLogin: ${viewer.login()}"
        val repos = viewer.repositories().nodes()?.map { "Name: ${it.name()}, Path: ${it.path()}" }
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, repos)
        listViewRepositories?.adapter = adapter
    }
}
