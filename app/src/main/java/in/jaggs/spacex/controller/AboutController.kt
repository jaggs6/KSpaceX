package `in`.jaggs.spacex.controller

import `in`.jaggs.spacex.model.SpaceXApi
import `in`.jaggs.spacex.model.SpaceXApiService
import `in`.jaggs.spacex.view.AboutFragment
import android.annotation.SuppressLint
import android.text.Html
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_about.*
import retrofit2.Response

class AboutController(val fragment: AboutFragment) {

    private val spaceXApiServe by lazy {
        SpaceXApiService.create()
    }
    var disposable: Disposable? = null

    fun fetchInfo() {
        disposable = spaceXApiServe.info("name,founder,founded")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result) },
                { error -> showError(error.message) }
            )
    }

    private fun showError(message: String?) {
        fragment.txt_about.text = message
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(result: SpaceXApi.Info) {

        fragment.txt_about.text = Html.fromHtml(
            "Name: <b>${result.name}</b> <br/>" +
                    "Founder: <b>${result.founder}</b> <br/>" +
                    "Founded: <b>${result.founded}</b> <br/>"
        )
    }
}