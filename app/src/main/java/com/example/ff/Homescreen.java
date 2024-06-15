package com.example.ff;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Homescreen extends Join {
    EditText edtId, edtPw;
    Button mainBtnJoin, mainBtnLogin;
    SQLiteDatabase sqlDB; //데이터베이스 읽기 쓰기 전용
    //로그인하는 것, 입력란 ID, PW입력하고 로그인버튼을 누르면 회원가입유무(1,0) 판별, ID일치(idFlag 1,0),PW일치(pwFlag 1,0)
    int idFlag=0;
    int pwFlag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        edtId=(EditText)findViewById(R.id.edtId);
        edtPw=(EditText)findViewById(R.id.edtPw);
        mainBtnJoin=(Button)findViewById(R.id.mainBtnJoin);
        mainBtnLogin=(Button)findViewById(R.id.mainBtnLogin);

        //회원가입버튼을 클릭하면 Join,java로 이동해서 회원가입을 해야함.

        mainBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Join.class);//이동할 화면기록 클래스 추가하고
                startActivity(intent);//화면 시작
            }
        });//mainBtnJoin
        //로그인 버튼 동작 실행(입력란에 가입될 아이디 패스워드 입력 후 클릭) 화면유무판별
        mainBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //화면정보를 다 불러와서 cursor 에서 회원을 찾음
                Cursor cursor; // 커서인스턴스 필요-추가
                sqlDB=myHelper.getReadableDatabase();
                cursor=sqlDB.rawQuery("SELECT * FROM JoinInfo", null);//조회해 오는 동작은 rawQuery메소드
                ;//입력란 받을 임시 변수, DB의 ID 받을 임시변수
                String eId=null;
                String ePw=null;//입력란 비밀번호 저장할 변수
                String dId=null;//데이터베이스에서 찾은 ID를 저장할 변수
                String dPw=null;//데이터베이스엣 찾은 PW를 저장할 변수
                //전체레코드에서 ID, PW 있는지 비교
                while(cursor.moveToNext()) { //다음 레코드가 있으면 while문 수령
                    dId=cursor.getString(0);//다음행이 있으니,첫번째 레코드 이동해 dha, 첫번째 레코드에 0열 아이디를 dId 저장
                    dPw= cursor.getString(1);//첫번째 레코드의 패스워드를 dPw에 저장
                    eId=edtId.getText().toString();//로그인버튼을 누르기전에 입력란 아이디ID 아이디를 입력한 글자를 eId에 저장
                    ePw=edtPw.getText().toString();//입력란 PW 패스워드를 ePw에 저장
                    //첫번째 레코드와 아이디와 패스워드가 일치하는지 비교
                    if(dId.equals(eId)) { //dId==dId같은지 비교하는 메소드,아이디는 같음
                        idFlag=1; //아이디가 일치하면 비밀번호가 일치하는지 비교해야함.
                        if(dPw.equals(ePw)) {//아이디와 비번 모두 일치->정상회원->허가됨 메뉴로 이동->Menu.java가 없으니 MainActuvutu
                            pwFlag=1;
                            //안녕하세요 회원님
                            Toast.makeText(getApplicationContext(), "안녕하세요 회원님", Toast.LENGTH_SHORT).show();
                            //메인화면으로 이동하기로 시나리오를 잡음
                            Intent intent=new Intent(getApplicationContext(),MainScreen.class);
                            startActivity(intent);
                        }//내부if
                        else{//아이디는 일치하지만 비번오류
                            Toast.makeText(getApplicationContext(), "회원님 비밀번호 오류입니다", Toast.LENGTH_SHORT).show();
                        }//내부 if문 else
                    }//if
                    else{}//아이디가 일치하지 않을 때-다음 레코드 계속 찾아야 하므로 해줄게 없음.
                }//while
                cursor.getClass();
                sqlDB.close();
                //비회원:ID가 없는 사람에게 회원가입 유도 메세지 필요.
                if(idFlag==0 && pwFlag==0) {
                    Toast.makeText(getApplicationContext(), "아이디가 없습니다. 회원가입해 주세요", Toast.LENGTH_SHORT).show();
                }//if
            }//onCreate()
        });//mainBtnLogin
    }//onCreate()
}//MainActivity
