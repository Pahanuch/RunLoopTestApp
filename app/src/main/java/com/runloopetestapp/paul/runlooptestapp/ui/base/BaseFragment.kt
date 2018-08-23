@file:Suppress("DEPRECATION")

package com.runloopetestapp.paul.runlooptestapp.ui.base

import android.app.ProgressDialog
import com.arellomobile.mvp.MvpAppCompatFragment
import com.runloopetestapp.paul.runlooptestapp.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    private var dialog: ProgressDialog? = null

    override fun showNetworkError() {
        error(getString(R.string.network_error))
    }

    override fun showUnknownError() {
        error(getString(R.string.uknown_error))
    }

    override fun showError(message: String) {
        error(message)
    }

    override fun showLoading() {
        dialog?.dismiss()
        dialog = indeterminateProgressDialog(message = getString(R.string.loading_text))
        dialog?.setCancelable(false)
        dialog?.show()
    }

    override fun hideLoading() {
        dialog?.dismiss()
    }

    private fun error(message: String) {
        context.alert(factory = Appcompat, message = message, title = getString(R.string.error_title)) { positiveButton(android.R.string.ok) {} }.show()
    }

}