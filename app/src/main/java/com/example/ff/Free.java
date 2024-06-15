package com.example.ff;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Free extends AppCompatActivity {
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free);

        // DBManager 인스턴스 초기화
        dbManager = new DBManager(this);

        // UI 요소 초기화
        Button addButton = findViewById(R.id.addButton);
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText contentEditText = findViewById(R.id.contentEditText);
        LinearLayout postsLayout = findViewById(R.id.postsLayout);

        // 게시물 추가 버튼 클릭 리스너 설정
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();
                dbManager.addPost(title, content);  // 게시물 추가
                displayPosts(postsLayout);  // 게시물 목록 갱신
            }
        });

        displayPosts(postsLayout);  // 처음 액티비티 시작 시 게시물 목록 표시
    }

    // 게시물 목록을 표시하는 메서드
    private void displayPosts(LinearLayout postsLayout) {
        postsLayout.removeAllViews();
        List<Post> posts = dbManager.getAllPosts();

        for (Post post : posts) {
            View postView = getLayoutInflater().inflate(R.layout.post_item, null);

            TextView titleTextView = postView.findViewById(R.id.titleTextView);
            TextView contentTextView = postView.findViewById(R.id.contentTextView);
            TextView timestampTextView = postView.findViewById(R.id.timestampTextView);
            Button editButton = postView.findViewById(R.id.editButton);
            Button deleteButton = postView.findViewById(R.id.deleteButton);

            // 게시물 데이터를 UI에 설정
            titleTextView.setText(post.getTitle());
            contentTextView.setText(post.getContent());
            timestampTextView.setText(post.getTimestamp());

            // 수정 버튼 클릭 리스너 설정
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText titleEditText = findViewById(R.id.titleEditText);
                    EditText contentEditText = findViewById(R.id.contentEditText);
                    titleEditText.setText(post.getTitle());
                    contentEditText.setText(post.getContent());

                    Button saveButton = findViewById(R.id.saveButton);
                    saveButton.setVisibility(View.VISIBLE);  // 저장 버튼 표시
                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String updatedTitle = titleEditText.getText().toString();
                            String updatedContent = contentEditText.getText().toString();
                            dbManager.updatePost(post.getId(), updatedTitle, updatedContent);  // 게시물 업데이트
                            displayPosts(postsLayout);  // 게시물 목록 갱신
                            saveButton.setVisibility(View.GONE);  // 저장 버튼 숨김
                        }
                    });
                }
            });

            // 삭제 버튼 클릭 리스너 설정
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbManager.deletePost(post.getId());  // 게시물 삭제
                    displayPosts(postsLayout);  // 게시물 목록 갱신
                }
            });

            postsLayout.addView(postView);  // 게시물 뷰를 레이아웃에 추가
        }
    }

}
