package com.daweibayu.bootstrap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by wli on 16/4/19.
 */
public class DBSTabFragmentAdapter extends FragmentStatePagerAdapter {

  private List<Fragment> mFragments;
  private List<String> mTitles;

  public DBSTabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
    super(fm);
    mFragments = fragments;
    mTitles = titles;
  }

  @Override
  public Fragment getItem(int position) {
    return mFragments.get(position);
  }

  @Override
  public int getCount() {
    return mFragments.size();
  }

  @Override
  public CharSequence getPageTitle(int position) {
    return mTitles.get(position);
  }
}