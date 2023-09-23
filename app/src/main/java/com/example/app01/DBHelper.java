package com.example.app01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    //版本
    private final static int DBVersion = 1;
    private final static String DBName = "SampleList.db";
    private final static String TableName = "SampleList";

    public DBHelper(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 程式首次運行，且資料庫不存在時(第一次載入)，會自動生成id,Account,Password的資料表!
        String SQLTable = "CREATE TABLE IF NOT EXISTS " + TableName + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Account TEXT, " +
                "Password TEXT, " +
                "CheckPassword TEXT" +
                ");";
        db.execSQL(SQLTable);
    }

    //onUpgrade暫時不知道要升級時如何操作
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 資料庫結構有改變了就會用到onUpgrade ex.1.改版本2.改資料庫結構(add表...)3.刪除舊的表,then移到新的
        //DROP TABLE 刪除舊的TableName表，then我可以再創一個新的
        final String SQL = "DROP TABLE " + TableName;
        sqLiteDatabase.execSQL(SQL);
    }

    //檢查table有無正常被創建 若沒有就創建
    public void checkDBTable() {
        //Cursor android中查詢資料的介面，他會收到一個結果集
        //getWriteableDatabases() --> 它返回一個可寫入的資料庫物件。 這裡是把抓到的TableName寫進去
        //利用sql語言來檢查資料庫中的所有資料表，是否存在有那個名字的表 (where ... ,條件:TableName)
        //sqlite_master 是 SQLite的內建系統表，不動他。
        Cursor cursor = getWritableDatabase().rawQuery(
                "SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" +
                        TableName + "';", null);

        //cursor == null 代表sql查詢錯誤
        //!=null來確保cursor正確初始化
        if (cursor != null) {
            if (cursor.getCount() == 0) {
                getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + TableName + "(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Account TEXT, " +
                        "Password TEXT, " +
                        "CheckPassword TEXT" +
                        ");");
                cursor.close();
            }
        }
    }

    public boolean registerFunction(String account, String password, String checkPd){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Account", account);
        values.put("Password", password);
        values.put("CheckPassword", checkPd);
        long result = db.insert(TableName, null, values);

        //若插入成功的話 return ture (ture = 1) SQLite錯誤會直接回傳-1;
        return result != -1;
    }

    public boolean checkIfUserExists(String account){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TableName, new String[]{"Account"},"Account = ?", new String[]{account},null,null,null);
        boolean userExists = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }
        return userExists;
    }

}
