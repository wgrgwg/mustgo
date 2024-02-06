package com.mustgo.controller;

import com.mustgo.domain.Member;
import com.mustgo.service.MemberService;
import com.mustgo.service.PostService;
import com.mustgo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final RestaurantService restaurantService;
    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/restaurant/{restaurantId/")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }
}
