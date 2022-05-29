package com.example.androidproject.interfaces;

import com.example.androidproject.enums.BottomNavEnum;
import com.example.androidproject.enums.MenuViewEnum;

import java.io.Serializable;

public interface MainFragmentListener extends Serializable {
    void hideMenuView(MenuViewEnum viewEnum);

    void showHomeIcons();

    void showBottomNav();

    void setBottomNavItem(BottomNavEnum bottomNavItem);

    void showSearchView();

    void showFilterView();

    void showAccIcons();

    void showMarketIcons();

    void showCreateFareIcons();

    void hideBottomNav();

    void showLoginIcons();

    void showDetailFragmentIcons();

    void setSearchListener(OnSearchListener listener);
}
