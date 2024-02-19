package com.mustgo.controller;

import com.mustgo.domain.*;
import com.mustgo.service.MemberService;
import com.mustgo.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RestaurantService restaurantService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result, Model model){

        if(result.hasErrors()){
            return"members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getDistrict(), form.getDong());

        Member member = new Member();
        member.setName(form.getName());
        member.setAge(form.getAge());
        member.setGender(form.getGender());
        member.setAddress(address);

        memberService.join(member);

        Message message = new Message();

        message.setAge(member.getAge());
        message.setGender(member.getGender());
        message.setCurrentSeason();
        message.setIsSpicy(form.getIsSpicy());
        message.setIsRice(form.getIsRice());
        message.setIsDinner();
        message.setPriceFavor(form.getPriceFavor());


        // form 으로 입력된 값을 python으로 넘겨주고, 다시 받은 값을 바탕으로 새로운 페이지에 결과 출력

        // RestTemplate 생성
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/predict"; // Python 서버 URL

        // 회원 정보를 HttpEntity로 변환
        HttpEntity<Message> request = new HttpEntity<>(message);

        // Python 서버에 POST 요청
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // 결과 처리
        String recommendation = response.getBody();
        int categoryId = Integer.parseInt(recommendation.trim());

        /**
         * 테스트
         */
//        model.addAttribute("recommendation", recommendation);
//        return "/recommendation/result";

        List<Restaurant> restaurants = restaurantService.findRestaurantsByCategoryIdAndAddress(categoryId, address);
        //List<Restaurant> restaurants = restaurantService.findRestaurantsByCategoryId(categoryId);
        model.addAttribute("restaurants", restaurants);

        return "/restaurants/restaurantsList";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
