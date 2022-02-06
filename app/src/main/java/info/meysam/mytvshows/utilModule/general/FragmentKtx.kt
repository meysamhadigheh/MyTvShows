package info.meysam.mytvshows.utilModule.general

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment



fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)


