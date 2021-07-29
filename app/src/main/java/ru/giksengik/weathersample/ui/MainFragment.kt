package ru.giksengik.weathersample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.giksengik.weathersample.R
import ru.giksengik.weathersample.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding : FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) setupDrawerNavigation()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupDrawerNavigation()
    }

    private fun setupDrawerNavigation() {
        binding?.let { binding ->
            val navController = binding.mainNavHost.findNavController()
            binding.appToolbar.setupWithNavController(navController, binding.drawerLayout)
            setupDrawerToggle()
        }
    }

    private fun setupDrawerToggle() =
        activity?.let{  activity ->
            val drawerToggle = ActionBarDrawerToggle(activity, binding?.drawerLayout, binding?.appToolbar,
                R.string.open_drawer_text, R.string.close_drawer_text)
            drawerToggle.isDrawerIndicatorEnabled = true
            drawerToggle.syncState()
            binding?.drawerLayout?.addDrawerListener(drawerToggle)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}