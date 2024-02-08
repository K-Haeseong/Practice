package com.study.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MessageDto {

    private String message; // 사용자에게 보낼 메시지 내용
    private String redirectUri; // 리다이렉트 위한 URI
    private RequestMethod method; // HTTP 요청 메서드
    private Map<String, Object> data;  // 화면에 넘길 데이터(파라미터)
}
