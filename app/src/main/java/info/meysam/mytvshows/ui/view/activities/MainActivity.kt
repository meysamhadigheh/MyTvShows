package info.meysam.mytvshows.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import info.meysam.mytvshows.R

class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    override fun setContentViewActivity() {
        setContentView(R.layout.activity_main)

    }

    override fun getIntentData() {
    }

    override fun instantiateViews() {
    }

    override fun setViewListeners() {
    }

    override fun setActivityContent() {
    }
}