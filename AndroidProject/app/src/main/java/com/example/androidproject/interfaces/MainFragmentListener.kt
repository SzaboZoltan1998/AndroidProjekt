package com.example.androidproject.interfaces

import com.example.androidproject.enums.BottomNavEnum
import com.example.androidproject.enums.MenuViewEnum
import java.io.Serializable


interface MainFragmentListener : Serializable {
    fun hideMenuView(viewEnum: MenuViewEnum?)
    fun showHomeIcons()
    fun showBottomNav()
    fun setBottomNavItem(bottomNavItem: BottomNavEnum?)
    fun showSearchView()
    fun showFilterView()
    fun showAccIcons()
    fun showMarketIcons()
    fun showCreateFareIcons()
    fun hideBottomNav()
    fun showLoginIcons()
    fun showDetailFragmentIcons()
}