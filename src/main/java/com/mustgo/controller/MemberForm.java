package com.mustgo.controller;

import com.mustgo.domain.Gender;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    private int age;

    private Gender gender;

    private String city;
    private String district;
    private String dong;

    private int orderFrequency;

    /**
     * 연령대 5단위
     *
     */
}
