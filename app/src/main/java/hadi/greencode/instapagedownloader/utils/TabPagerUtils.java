package hadi.greencode.instapagedownloader.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hadi.greencode.instapagedownloader.InstaPageApplication;

public class TabPagerUtils {
    public static void setupTabPager(ViewPager viewPager, TabLayout tabLayout){

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        ViewGroup viewGroup = (ViewGroup) tabLayout.getChildAt(0);
        int       tabsCount = viewGroup.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup viewGroupTab = (ViewGroup) viewGroup.getChildAt(j);
            for (int i = 0; i < viewGroupTab.getChildCount(); i++) {
                View tabChild = viewGroupTab.getChildAt(i);
                if (tabChild instanceof TextView) {
                    ((TextView) tabChild).setTypeface(InstaPageApplication.getBoldIranSans());
                }
            }
        }
    }
}
