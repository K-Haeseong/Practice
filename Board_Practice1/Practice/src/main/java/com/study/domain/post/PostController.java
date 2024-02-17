package com.study.domain.post;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/write")
    public String openPostWrite(@RequestParam(value = "id", required = false) Long id, Model model) {
        if(id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        return "post/write";
    }

    @PostMapping("/post/save")
    public String savePost(PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글이 저장 되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    @PostMapping("/post/update")
    public String updatePost( @ModelAttribute("queryParams") SearchDto queryParams,
                              @ModelAttribute("params") PostRequest params,
                              Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글이 수정 되었습니다.", "/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    @GetMapping("/post/list")
    public String openPostList(@ModelAttribute("params") SearchDto params,
                               Model model) {
        PagingResponse<PostResponse> response = postService.findAllPost(params);
        model.addAttribute("response", response);
        return "post/list";
    }

    @GetMapping("/post/view")
    public String openPostView( @ModelAttribute("params") SearchDto params,
                                @RequestParam("id") Long id,
                                Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

    @PostMapping("/post/delete")
    public String deletePost( @ModelAttribute("queryParams") SearchDto queryParams,
                              @RequestParam("id") Long id,
                              Model model) {
        postService.deletePostById(id);
        MessageDto message = new MessageDto("게시글이 삭제 되었습니다.", "/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    private Map<String, Object> queryParamsToMap(SearchDto queryParams) {
        Map<String, Object> data  = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());

        return data;
    }

    // 사용자에게 메시지 전달 및 페이지 리다이렉트
    public String showMessageAndRedirect(MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }


}
