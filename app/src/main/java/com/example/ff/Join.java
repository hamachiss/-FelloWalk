package com.example.ff;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Join extends Activity {//클래스 추가
    //onCreate()
    EditText jId,jPw;
    Button joinBtn; //회원가입을 위해서 DB와 테이블 생성이 필요 SQLiteOpenHelper (내부)클래스
    myDBHelper myHelper; //객체참조변수선언,인스턴스선언
    SQLiteDatabase sqlDB;//DB열기를 리턴받을 인스턴스
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join); //조인화면 적용
        //onCreate()내부
        jId=(EditText) findViewById(R.id.jId);
        jPw=(EditText) findViewById(R.id.jPw);
        joinBtn=(Button) findViewById(R.id.joinBtn);
        //인스턴스생성
        myHelper=new myDBHelper(this);
        //회원가입버튼 클릭했을때 동작설정: 가입화면에 가입할 아이디와 비번을 입력하고[회원가입버튼]=>INSERT문 수행=>메인화면으로 이동
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB=myHelper.getWritableDatabase();//가입하기위해 DB추가
                sqlDB.execSQL("INSERT INTO joinInfo VALUES('"+
                        jId.getText().toString()+"','"+
                        jPw.getText().toString()+"');");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"가입됨",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(), Homescreen.class);//메인으로 로그인을 위해 화면이동
                startActivity(intent); //메인화면정보를 가지고 화면실행
            }//onClick()
        });//joinBtn()
    }
    public  class myDBHelper extends SQLiteOpenHelper{
        //onCreate() onUpgrade()
        public myDBHelper(Context context) {
            super(context,"LoginDB",null,1); //DB생성 부모클래스의 생성자를 사용.
        }//생성자

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {//테이블생성
            sqLiteDatabase.execSQL("CREATE TABLE JoinInfo(uId TEXT, uPassword TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//테이블삭제후 생성
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS JoinInfo"); //테이블명이 있으면 삭제:
            onCreate(sqLiteDatabase);

        }//onUpgrade

    }//myDBHelper
}//Join class