package com.daweibayu.bootstrap;

/**
 * Created by wli on 16/5/21.
 */
public abstract class DBSCallback<T> {
  public DBSCallback() {
  }

  public abstract void done(T t, Exception e);
}
