package com.example.testnavermap.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseTable {
  private static final int VERSION = 1;
  protected SQLiteOpenHelper helper;

  protected BaseTable(Context context) {
    helper = new BaseDatabaseHelper(context, "Spot__DB", null, VERSION);
  }

  protected SQLiteDatabase db() {
    return helper.getWritableDatabase();
  }

  public void close() {
    db().close();
  }

  protected int insert() {
    SQLiteDatabase db = helper.getWritableDatabase();

    String sql = "SELECT last_insert_rowid();";
    Cursor c = db.rawQuery(sql, null);

    int result = 0;
    c.moveToFirst();
    if(c.getCount() > 0)
      result = c.getInt(0);
    c.close();

    return result;
  }
}
