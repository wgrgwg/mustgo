package com.mustgo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Post {
    @Id @GeneratedValue
    @Column(name="post_id")
    private Long id;

    private String title;
    private String content;

    private int likes;

    private LocalDateTime postDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    //==생성 메서드==//
    public static Post createPost(Member member, Restaurant restaurant, String title, String content){
        Post post = new Post();
        post.setMember(member);
        post.setRestaurant(restaurant);
        post.setTitle(title);
        post.setContent(content);
        post.setLikes(0);
        post.setPostDate(LocalDateTime.now());

        return post;
    }

    /**
     * likes 관련 메소드 추가?
     */

}
