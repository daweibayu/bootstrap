package com.daweibayu.bootstrap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by wli on 16/5/26.
 */
public abstract class DBSCommonListActivity<T> extends AppCompatActivity {

  protected SwipeRefreshLayout refreshLayout;
  protected RecyclerView recyclerView;

  LinearLayoutManager layoutManager;
  protected DBSHeaderListAdapter itemAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dbs_common_list_activity);

    refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshable_list_srl);
    recyclerView = (RecyclerView) findViewById(R.id.refreshable_list_recycler);

    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(new DBSDividerItemDecoration(this));

    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        processFetchData();
      }
    });

    setHolder();
    processFetchData();
  }

  private void setHolder() {
    itemAdapter = new DBSHeaderListAdapter<T>(getHolder());
    recyclerView.setAdapter(itemAdapter);
  }

  protected void processFetchData() {
    fetchData(10, 0, new DBSCallback<List<T>>() {
      @Override
      public void done(List<T> ts, Exception e) {
        refreshLayout.setRefreshing(false);
        itemAdapter.setDataList(ts);
        itemAdapter.notifyDataSetChanged();
      }
    });
  }

  public abstract void fetchData(int limit, int skip, DBSCallback<List<T>> callback);

  public abstract Class<?> getHolder();
}
