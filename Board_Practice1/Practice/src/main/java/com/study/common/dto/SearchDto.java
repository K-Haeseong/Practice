package com.study.common.dto;

import com.study.common.paging.Pagination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {

    private int page;               // 현재페이지
    private int recordSize;         // 페이지당 게시글 출력 개수
    private int pageSize;           // 화면에 표시될 페이지 개수
    private String keyword;         // 검색 유형
    private String searchType;      // 검색 키워드
    private Pagination pagination;  // 페이지네이션

    public SearchDto() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

}
