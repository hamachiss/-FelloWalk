package com.example.ff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DBHelper 클래스는 SQLiteOpenHelper를 상속하여 데이터베이스를 관리합니다.
public class DBHelper extends SQLiteOpenHelper {

    // 데이터베이스 이름과 버전을 정의합니다.
    private static final String DATABASE_NAME = "board.db";
    private static final int DATABASE_VERSION = 1;

    // DBHelper 생성자
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 데이터베이스가 처음 생성될 때 호출됩니다.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 게시물 테이블을 생성하는 SQL 문
        db.execSQL("CREATE TABLE posts (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +  // 게시물 ID, 자동 증가
                "title TEXT," +  // 게시물 제목
                "content TEXT," +  // 게시물 내용
                "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP" +  // 작성 시간, 기본값은 현재 시간
                ");");
    }

    // 데이터베이스가 업그레이드될 때 호출됩니다.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블을 삭제하고 새로운 구조로 다시 생성합니다.
        db.execSQL("DROP TABLE IF EXISTS posts");
        onCreate(db);
    }
}