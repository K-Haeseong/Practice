package com.study.domain.post;


import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    /* 게시글 저장 */
    @Transactional
    public Long savePost(PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }

    /* 게시글 상세 정보 조회 */
    public PostResponse findPostById(Long id) {
        return postMapper.findById(id);
    }


    /* 게시글 수정 */
    @Transactional
    public Long updatePost(PostRequest params) {
        postMapper.update(params);
        return params.getId();
    }

    /* 게시글 삭제 */
    public Long deletePostById(Long id) {
        postMapper.deleteById(id);
        return id;
    }

    /* 게시글 목록 조회 */
    public PagingResponse<PostResponse> findAllPost(SearchDto params) {

        int count = postMapper.count(params);

        // Pagination 객체를 생성 후 페이지 정보 계산, SearchDto타입 객체에 pagination 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 저장된 페이지 정보 활용하여 리스트 목록 조회
        List<PostResponse> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);



    }


}
