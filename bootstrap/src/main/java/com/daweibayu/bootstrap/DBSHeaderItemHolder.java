package com.daweibayu.bootstrap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Created by wli on 15/11/26.
 */
public class DBSHeaderItemHolder extends DBSCommonViewHolder {
  LinearLayout rootLayout;

  public DBSHeaderItemHolder(Context context, ViewGroup root) {
    super(context, root, R.layout.dbs_header_item_layout);
    rootLayout = (LinearLayout)itemView.findViewById(R.id.dbs_header_item_root_view);
  }

  public void setView(View view) {
    rootLayout.removeAllViews();
    if (null != view) {
      rootLayout.addView(view);
    }
  }

  @Override
  public void bindData(Object o) {}
}
