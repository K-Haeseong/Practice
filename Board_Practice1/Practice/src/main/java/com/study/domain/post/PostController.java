package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String savePost(PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list";
    }

    @PostMapping("/post/update")
    public String updatePost(PostRequest params) {
        postService.updatePost(params);
        // @PathVariable 사용하기 전, 임시방편
        Long id = params.getId();
        return "redirect:/post/view" +"?id=" + id;
    }

    @GetMapping("/post/list")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/post/view")
    public String openPostView(Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }

    @PostMapping("/post/delete")
    public String deletePost(Long id) {
        postService.deletePostById(id);
        return "redirect:/post/list";
    }

}
