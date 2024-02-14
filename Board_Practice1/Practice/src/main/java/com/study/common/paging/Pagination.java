package com.study.common.paging;


import com.study.common.dto.SearchDto;
import lombok.Getter;

@Getter
public class Pagination {

    private int totalRecordCount; // 총 게시글 수
    private int totalPageCount;// 총 페이지 수
    private int startPage;// 첫 페이지 번호(목록 내)
    private int endPage;// 끝 페이지 번호(목록 내)
    private boolean existPrevPage;// 이전페이지(다음 목록의 끝 페이지로)
    private boolean existNextPage;// 다음페이지(다음 목록의 첫 페이지로)
    private int limitStart;// Limit 시작 위치(offset)

    public Pagination(int totalRecordCount, SearchDto params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(params);
        }
    }

    private void calculation(SearchDto params) {

        // 총 페이지 수 계산
        int restPageList = totalRecordCount % params.getRecordSize();
        totalPageCount = restPageList == 0 ? totalRecordCount / params.getRecordSize() : totalRecordCount / params.getRecordSize() + 1;

        // 전체 페이지를 초과하는 페이지를 요청 시 발생할 수 있는 오류나 예상치 못한 동작을 방지
        //  EX) 사용자가 쿼리스트링에 페이지를 임의로 작성, 존재하지 않는 페이지 번호를 요청 보낼 경우
        if(params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;

        if(endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // 이전 페이지 계산
        existPrevPage = startPage!=1;

        // 다음 페이지 계산
        existNextPage = endPage!=totalPageCount;

        // Limit 시작 위치 계산 - offset 계산
        limitStart = (params.getPage() - 1) * params.getRecordSize();
    }
}
