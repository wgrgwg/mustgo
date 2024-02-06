package com.mustgo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    private MemberRank rank;

    private Address address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();


    /**
     * rank 관련 메소드 추가?
     */
}
