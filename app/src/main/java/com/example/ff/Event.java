package com.example.ff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Event extends AppCompatActivity {

    private ListView listView1;

    private LuckyWheel luckyWheel;

    List<WheelItem> wheelItems ;


    String point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        listView1=(ListView)findViewById(R.id.listview1);

        //변수에 담기
        luckyWheel = findViewById(R.id.luck_wheel);

        //점수판 데이터 생성
        generateWheelItems();

        //점수판 타겟 정해지면 이벤트 발생
        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {

                //아이템 변수에 담기
                WheelItem wheelItem = wheelItems.get(Integer.parseInt(point)-1);

                //아이템 텍스트 변수에 담기
                String money = wheelItem.text;

                //메시지
                Toast.makeText(Event.this, money, Toast.LENGTH_SHORT).show();
            }
        });

        //시작버튼
        Button start = findViewById(R.id.spin_btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random = new Random();
                point = String.valueOf(random.nextInt(6)+1); // 1 ~ 6
                luckyWheel.rotateWheelTo(Integer.parseInt(point));
            }
        });

        listView1=(ListView)findViewById(R.id.listview1);

        List<String> data= new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        listView1.setAdapter(adapter);

        data.add("ㅎㅇ");
        data.add("ㅂㅇ");
    }

    /**
     * 데이터 담기
     */
    private void generateWheelItems() {

        wheelItems = new ArrayList<>();


        wheelItems.add(new WheelItem(Color.parseColor("#296BEF"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "100"));;
        wheelItems.add(new WheelItem(Color.parseColor("#31F1F8"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "200"));;
        wheelItems.add(new WheelItem(Color.parseColor("#296BEF"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "300"));;
        wheelItems.add(new WheelItem(Color.parseColor("#31F1F8"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "400"));;
        wheelItems.add(new WheelItem(Color.parseColor("#296BEF"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "500"));;
        wheelItems.add(new WheelItem(Color.parseColor("#31F1F8"), BitmapFactory.decodeResource(getResources(),
                R.drawable.coin), "500"));;


        //점수판에 데이터 넣기
        luckyWheel.addWheelItems(wheelItems);
    }


    /**
     * drawable -> bitmap
     * @param drawable drawable
     * @return
     */
    public static Bitmap drawableToBitmap (Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

}