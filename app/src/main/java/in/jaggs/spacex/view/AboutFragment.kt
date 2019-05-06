package `in`.jaggs.spacex.view


import `in`.jaggs.spacex.R
import `in`.jaggs.spacex.controller.AboutController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    private lateinit var aboutController: AboutController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutController = AboutController(this)
        aboutController.fetchInfo()
    }

    override fun onDestroy() {
        aboutController.disposable?.dispose()
        super.onDestroy()
    }
}
