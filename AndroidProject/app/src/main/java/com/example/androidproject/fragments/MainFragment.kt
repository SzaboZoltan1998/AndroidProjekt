package com.example.androidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.androidproject.R
import com.example.androidproject.databinding.MainFragmentBinding
import com.example.androidproject.enums.BottomNavEnum
import com.example.androidproject.enums.MenuViewEnum
import com.example.androidproject.interfaces.MainFragmentListener
import com.example.androidproject.utils.Navigator


open class MainFragment : NavHostFragment(),
    MainFragmentListener {
    private var mBaseBinding : MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mBaseBinding == null) {
            mBaseBinding = MainFragmentBinding.inflate(inflater, container, false)
        }

        return mBaseBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBaseBinding!!.appBar.setMainFragmentListener(this)
        mBaseBinding!!.appBar.showBazarLogo()
        Navigator.getsInstance(requireContext()).setMainFragmentListener(this)

        val bundle = Bundle()
        bundle.putSerializable("KEY_MAIN_LISTENER", this)

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
        navController.setGraph(R.navigation.nav_graph, bundle)
        Navigator.getsInstance(requireContext()).setNavController(navController)
    }

    override fun hideMenuView(viewEnum: MenuViewEnum?) {
    }

    override fun showHomeIcons() {
        mBaseBinding!!.appBar.showHomeIcons()
    }

    override fun showBottomNav() {

        mBaseBinding!!.bottomNav.visibility = View.VISIBLE
    }

    override fun setBottomNavItem(bottomNavItem: BottomNavEnum?) {
        mBaseBinding!!.bottomNav.setBottomNavItem(bottomNavItem)
    }

    override fun showSearchView() {
        if (mBaseBinding!!.searchView.visibility == View.VISIBLE) {
            mBaseBinding!!.searchView.visibility = View.GONE
            return
        }

        val visibility = mBaseBinding!!.filterView.visibility
        if (visibility == View.VISIBLE) {
            mBaseBinding!!.filterView.visibility = View.GONE
        }

        mBaseBinding!!.searchView.visibility = View.VISIBLE
    }

    override fun showFilterView() {
        if (mBaseBinding!!.filterView.visibility == View.VISIBLE) {
            mBaseBinding!!.filterView.visibility = View.GONE
            return
        }

        val visibility = mBaseBinding!!.searchView.visibility
        if (visibility == View.VISIBLE) {
            mBaseBinding!!.searchView.visibility = View.GONE
        }
        mBaseBinding!!.filterView.visibility = View.VISIBLE
    }

    override fun showAccIcons() {
        mBaseBinding!!.appBar.showAccIcons()
        mBaseBinding!!.filterView.visibility = View.GONE
        mBaseBinding!!.searchView.visibility = View.GONE
    }

    override fun showMarketIcons() {
        mBaseBinding!!.appBar.showMarketIcons()
        mBaseBinding!!.filterView.visibility = View.GONE
    }

    override fun showCreateFareIcons() {
        mBaseBinding!!.appBar.showCreateFareIcons()
        mBaseBinding!!.filterView.visibility = View.GONE
        mBaseBinding!!.searchView.visibility = View.GONE
    }

    override fun hideBottomNav() {
        mBaseBinding!!.bottomNav.visibility = View.GONE
    }

    override fun showLoginIcons() {
        mBaseBinding!!.appBar.showLoginIcons()
    }

    override fun showDetailFragmentIcons() {
        mBaseBinding!!.appBar.showDetailIcons()
    }
}