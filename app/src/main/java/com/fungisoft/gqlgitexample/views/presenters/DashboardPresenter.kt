package com.fungisoft.gqlgitexample.views.presenters


import com.fungisoft.gqlgitexample.ViewerInfo
import com.fungisoft.gqlgitexample.network.response.UserInfo
import com.fungisoft.gqlgitexample.views.BaseView
import com.fungisoft.gqlgitexample.views.presenters.base.BasePresenter

interface DashboardPresenter : BasePresenter {
    fun requestInfo()

    fun requestRepositories()

    interface DashboardView : BaseView {
        fun showUserInfo(userInfo: UserInfo)
        fun showRepoInfo(viewer: ViewerInfo.Data.Viewer)
    }
}
