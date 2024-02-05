package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/write")
    public String openPostWrite(Model model) {
        return "post/write";
    }

    @PostMapping("/post/save")
    public String savePost(PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list";
    }

}
