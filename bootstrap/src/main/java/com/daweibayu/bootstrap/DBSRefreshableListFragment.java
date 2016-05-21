package com.daweibayu.bootstrap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wli on 16/4/22.
 * TODO 稍后添加分页
 */
public abstract class DBSRefreshableListFragment<T> extends Fragment {

  protected SwipeRefreshLayout refreshLayout;
  protected RecyclerView recyclerView;

  LinearLayoutManager layoutManager;
  protected DBSHeaderListAdapter itemAdapter;

  @Nullable
  @Override
  public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dbs_refreshable_list_fragment, container, false);

    refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.dbs_refreshable_list_srl);
    recyclerView = (RecyclerView) view.findViewById(R.id.dbs_refreshable_list_recycler);

    layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(new DBSDividerItemDecoration(getActivity()));

    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        processFetchData();
      }
    });
    setHolder();
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    processFetchData();
  }

  private void processFetchData() {
    fetchData(10, 0, new DBSCallback<List<T>>() {
      @Override
      public void done(List<T> ts, Exception e) {
        refreshLayout.setRefreshing(false);
        itemAdapter.setDataList(ts);
        itemAdapter.notifyDataSetChanged();
      }
    });
  }

  public abstract Class<?> getHolder();

  private void setHolder() {
    itemAdapter = new DBSHeaderListAdapter<T>(getHolder());
    recyclerView.setAdapter(itemAdapter);
  }

  public void updateFragment() {
    processFetchData();
  }

  public void setHeaderView(View view) {
    itemAdapter.setHeaderView(view);
  }

  public void setFooterView(View view) {
    itemAdapter.setFooterView(view);
  }

  public abstract void fetchData(int limit, int skip, DBSCallback<List<T>> callback);
}