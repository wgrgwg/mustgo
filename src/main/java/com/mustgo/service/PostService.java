package com.mustgo.service;

import com.mustgo.domain.Member;
import com.mustgo.domain.Post;
import com.mustgo.domain.Restaurant;
import com.mustgo.repository.MemberRepository;
import com.mustgo.repository.PostRepository;
import com.mustgo.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Long post(Long memberId, Long restaurantId, String title, String content){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);

//        //배송정보 생성
//        Delivery delivery = new Delivery();
//        delivery.setAddress(member.getAddress());

//        //주문상품 생성
//        Post post = OrderItem.createOrderItem(item, item.getPrice(), count);

        //게시글 생성
        Post post = Post.createPost(member, restaurant, title, content);

        //게시글 업데이트

        //게시글 저장
        postRepository.save(post);

        return post.getId();
    }
}
