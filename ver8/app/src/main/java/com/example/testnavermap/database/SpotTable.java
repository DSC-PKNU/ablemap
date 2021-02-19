package com.example.testnavermap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import com.example.testnavermap.Spot;

import java.util.ArrayList;

public class SpotTable extends BaseTable {
  protected static SpotTable instance;

  // 테이블
  public final static String TABLE_NAME = "Spot__Table";

  // 필드
  public static final String _ID = "id";
  public static final String NAME = "name";
  public static final String ADDRESS = "address";
  public static final String IMAGE = "image";
  public static final String NAVER = "naverurl";
  public static final String GREEN = "greenurl";
  public static final String PICTO_A="picto_a";
  public static final String PICTO_B="picto_b";
  public static final String PICTO_C="picto_c";
  public static final String PICTO_D="picto_d";
  public static final String PICTO_E="picto_e";
  public static final String PICTO_F="picto_f";
  public static final String PICTO_G="picto_g";
  public static final String PICTO_H="picto_h";
  public static final String PICTO_I="picto_i";
  public static final String PICTO_J="picto_j";
  public static final String PICTO_K="picto_k";

  // 주로 사용할 필드 목록
  private static final String[] COLUMNS = { _ID, NAME, ADDRESS, IMAGE,NAVER,GREEN, PICTO_A,PICTO_B,PICTO_C,PICTO_D,PICTO_E,PICTO_F,PICTO_G,PICTO_H,PICTO_I,PICTO_J,PICTO_K};

  // 조회조건
  private static final String WHERE_BY_ID = _ID + "=?";
  private static final String WHERE_BY_NAME =  NAME + "=?";

  // 최초 생성 sql
  public static final String createSql = "CREATE TABLE if not exists " + TABLE_NAME
          + "(" + _ID + " integer primary key autoincrement, "
          + NAME + " text not null, "
          + ADDRESS + " text not null,"
          + IMAGE + " text not null,"
          +NAVER + " text not null,"
          +GREEN + " text default \"\","
          +PICTO_A+" integer default 0,"
          +PICTO_B+" integer default 0,"
          +PICTO_C+" integer default 0,"
          +PICTO_D+" integer default 0,"
          +PICTO_E+" integer default 0,"
          +PICTO_F+" integer default 0,"
          +PICTO_G+" integer default 0,"
          +PICTO_H+" integer default 0,"
          +PICTO_I+" integer default 0,"
          +PICTO_J+" integer default 0,"
          +PICTO_K+" integer default 0);";

  public synchronized static SpotTable instance(Context $context) {
    if(instance == null) {
      synchronized (SpotTable.class) {
        if(instance == null)
          instance = new SpotTable($context);
      }
    }
    return instance;
  }

  private SpotTable(Context $context) {
    super($context);
  }

  /**
   * 입력
   */
  public int insert(String name, String address, String image,String naver,String green,
                    int picto_A, int picto_B, int picto_C, int picto_D, int picto_E, int picto_F,
                    int picto_G, int picto_H, int picto_I, int picto_J, int picto_K) {
    ContentValues values = new ContentValues();
    values.put(NAME, name);
    values.put(ADDRESS, address);
    values.put(IMAGE, image);
    values.put(NAVER,naver);
    values.put(GREEN,green);
    values.put(PICTO_A,picto_A);
    values.put(PICTO_B,picto_B);
    values.put(PICTO_C,picto_C);
    values.put(PICTO_D,picto_D);
    values.put(PICTO_E,picto_E);
    values.put(PICTO_F,picto_F);
    values.put(PICTO_G,picto_G);
    values.put(PICTO_H,picto_H);
    values.put(PICTO_I,picto_I);
    values.put(PICTO_J,picto_J);
    values.put(PICTO_K,picto_K);


    db().insertOrThrow(TABLE_NAME, null, values);
    return super.insert();
  }

  public int insert(String name, String address, String image,String naver,String green){
    ContentValues values = new ContentValues();
    values.put(NAME, name);
    values.put(ADDRESS, address);
    values.put(IMAGE, image);
    values.put(NAVER,naver);
    values.put(GREEN,green);

    db().insertOrThrow(TABLE_NAME, null, values);
    return super.insert();
  }
  /**
   * 테이블에서 하나씩 꺼내 객체에 넣고 리스트 반환
   */
  public ArrayList<Spot> loadByTable() {
    ArrayList<Spot> result = new ArrayList<>();

    Cursor c = db().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

    if(c.getCount() == 0)
      return result;

    c.moveToFirst();

    while (!c.isAfterLast()) {
      result.add(makeRest(c));
      c.moveToNext();
    }

    c.close();

    return result;
  }

  private Spot makeRest(Cursor cursor) {
    String id = cursor.getString(cursor.getColumnIndex(_ID));
    String name = cursor.getString(cursor.getColumnIndex(NAME));
    String address = cursor.getString(cursor.getColumnIndex(ADDRESS));
    String image = cursor.getString(cursor.getColumnIndex(IMAGE));
    String naver = cursor.getString(cursor.getColumnIndex(NAVER));
    String green = cursor.getString(cursor.getColumnIndex(GREEN));
    int a = cursor.getInt(cursor.getColumnIndex(PICTO_A));
    int b = cursor.getInt(cursor.getColumnIndex(PICTO_B));
    int c = cursor.getInt(cursor.getColumnIndex(PICTO_C));
    int d = cursor.getInt(cursor.getColumnIndex(PICTO_D));
    int e = cursor.getInt(cursor.getColumnIndex(PICTO_E));
    int f = cursor.getInt(cursor.getColumnIndex(PICTO_F));
    int g = cursor.getInt(cursor.getColumnIndex(PICTO_G));
    int h = cursor.getInt(cursor.getColumnIndex(PICTO_H));
    int i = cursor.getInt(cursor.getColumnIndex(PICTO_I));
    int j = cursor.getInt(cursor.getColumnIndex(PICTO_J));
    int k = cursor.getInt(cursor.getColumnIndex(PICTO_K));


    return new Spot(id, name, address,image,naver,green,a,b,c,d,e,f,g,h,i,j,k);
  }

  public void deleteAll()
  {
    db().delete(TABLE_NAME, null, null);
  }

  public boolean isEmpty() {
    Cursor c = db().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

    return c.getCount() == 0;
  }
}
