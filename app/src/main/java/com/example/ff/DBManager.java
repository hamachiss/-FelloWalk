package com.example.ff;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    // DBManager 생성자, DBHelper 인스턴스를 초기화하고 데이터베이스를 얻습니다.
    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    // 게시물 추가 메서드
    public void addPost(String title, String content) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        database.insert("posts", null, values);
    }

    // 모든 게시물을 가져오는 메서드
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        Cursor cursor = database.query("posts", null, null, null, null, null, "timestamp DESC");

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Post post = new Post(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("content")),
                        cursor.getString(cursor.getColumnIndex("timestamp"))
                );
                posts.add(post);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return posts;
    }

    // 게시물 업데이트 메서드
    public void updatePost(int id, String title, String content) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        database.update("posts", values, "id=?", new String[]{String.valueOf(id)});
    }

    // 게시물 삭제 메서드
    public void deletePost(int id) {
        database.delete("posts", "id=?", new String[]{String.valueOf(id)});
    }
}