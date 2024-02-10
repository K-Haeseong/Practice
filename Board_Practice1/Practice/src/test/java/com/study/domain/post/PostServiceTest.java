package com.study.domain.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void saveByForeach() {
        for (int i =1; i<=1000; i++) {
            PostRequest params = new PostRequest();
            params.setTitle( i + "번 게시글");
            params.setContent( i + "번 게시글 내용");
            params.setWriter( "작성자" + i);
            params.setNoticeYn(false);
            postService.savePost(params);
        }
    }


    @Test
    void savePost() {
        PostRequest params = new PostRequest();
        params.setTitle("2번 게시글 제목");
        params.setContent("2번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);

        Long id = postService.savePost(params);
        System.out.println("생성된 게시글 id : " + id );
    }

    @Test
    void findPostById() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePostById() {
    }

    @Test
    void findAllPost() {
    }
}