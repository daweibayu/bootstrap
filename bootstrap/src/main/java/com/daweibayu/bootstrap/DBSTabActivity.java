package com.daweibayu.bootstrap;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;


/**
 * Created by wli on 16/4/20.
 */
public abstract class DBSTabActivity extends AppCompatActivity {

  protected ViewPager viewPager;
  protected TabLayout tabLayout;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dbs_common_tab_activity);
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(layoutResID);
    viewPager = (ViewPager)findViewById(R.id.dbs_common_tab_activity_viewpager);
    tabLayout = (TabLayout)findViewById(R.id.dbs_common_tab_activity_tablayout);
  }

  protected void initTabLayout(String[] tabList, Fragment[] fragmentList) {
    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    for (int i = 0; i < tabList.length; i++) {
      tabLayout.addTab(tabLayout.newTab().setText(tabList[i]));
    }

    DBSTabFragmentAdapter adapter = new DBSTabFragmentAdapter(getSupportFragmentManager(),
      Arrays.asList(fragmentList), Arrays.asList(tabList));
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override
      public void onPageSelected(int position) {
        if (0 == position) {
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {
      }
    });
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabsFromPagerAdapter(adapter);
  }
}
