package com.daweibayu.bootstrap;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wli on 15/11/23.
 * 单类型 item 的 RecyclerView 对应的 Adapter
 */
public class DBSCommonListAdapter<T> extends RecyclerView.Adapter<DBSCommonViewHolder> {

  private static HashMap<String, DBSCommonViewHolder.ViewHolderCreator> creatorHashMap = new HashMap<>();

  private Class<?> vhClass;

  protected List<T> dataList = new ArrayList<T>();

  public DBSCommonListAdapter() {
    super();
  }

  public DBSCommonListAdapter(Class<?> vhClass) {
    this.vhClass = vhClass;
  }

  /**
   * 获取该 Adapter 中存的数据
   *
   * @return
   */
  public List<T> getDataList() {
    return dataList;
  }

  /**
   * 设置数据，会清空以前数据
   *
   * @param datas
   */
  public void setDataList(List<T> datas) {
    dataList.clear();
    if (null != datas) {
      dataList.addAll(datas);
    }
  }

  /**
   * 添加数据，默认在最后插入，以前数据保留
   *
   * @param datas
   */
  public void addDataList(List<T> datas) {
    dataList.addAll(datas);
  }

  @Override
  public DBSCommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (null == vhClass) {
      try {
        throw new IllegalArgumentException("please use DBSCommonListAdapter(Class<VH> vhClass)");
      } catch (Exception e) {
      }
    }

    DBSCommonViewHolder.ViewHolderCreator<?> creator = null;
    if (creatorHashMap.containsKey(vhClass.getName())) {
      creator = creatorHashMap.get(vhClass.getName());
    } else {
      try {
        creator = (DBSCommonViewHolder.ViewHolderCreator) vhClass.getField("HOLDER_CREATOR").get(null);
        creatorHashMap.put(vhClass.getName(), creator);
      } catch (IllegalAccessException e) {
      } catch (NoSuchFieldException e) {
      }
    }
    if (null != creator) {
      return creator.createByViewGroupAndType(parent, viewType);
    } else {
      throw new IllegalArgumentException(vhClass.getName() + " HOLDER_CREATOR should be instantiated");
    }
  }

  @Override
  public void onBindViewHolder(DBSCommonViewHolder holder, int position) {
    if (position >= 0 && position < dataList.size()) {
      holder.bindData(dataList.get(position));
    }
  }

  @Override
  public int getItemCount() {
    return dataList.size();
  }
}