package com.fungisoft.gqlgitexample.views.presenters.impl


import com.fungisoft.gqlgitexample.network.events.ApolloEvent
import com.fungisoft.gqlgitexample.network.events.RetrofitEvent
import com.fungisoft.gqlgitexample.network.ApiClient
import com.fungisoft.gqlgitexample.views.presenters.DashboardPresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DashboardPresenterImpl(
        private val view: DashboardPresenter.DashboardView,
        private val apiClient: ApiClient) : DashboardPresenter {
    override fun requestInfo() {
        view.showProgress()
        apiClient.getUserInfo()
    }

    override fun requestRepositories() {
        view.showProgress()
        apiClient.getRepositories()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun completeUserReceived(event: ApolloEvent) {
        view.hideProgress()
        view.showRepoInfo(event.viewer)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun userInfoReceived(event: RetrofitEvent) {
        view.hideProgress()
        view.showUserInfo(event.userInfo)
    }

    override fun start() {
        EventBus.getDefault().register(this)
    }

    override fun resume() {
        EventBus.getDefault().unregister(this)
    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun destroy() {

    }

    override fun onError(message: String) {

    }
}
