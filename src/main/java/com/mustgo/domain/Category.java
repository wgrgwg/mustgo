package com.mustgo.domain;

public enum Category {
    KOR, WESTERN, CHN, JAP, SNACK, CHICKEN, FASTFOOD, PIZZA,
    GUKBAP, MALA, BOJOK, DONKATSU, BBQ, DAK;

    public static Category fromInteger(int x) {
        switch(x) {
            case 0:
                return GUKBAP; // 국밥
            case 1:
                return MALA; // 마라탕
            case 2:
                return WESTERN; // 파스타,스테이크
            case 3:
                return BOJOK; // 보쌈,족발
            case 4:
                return DONKATSU; // 돈까스
            case 5:
                return CHN; // 짜장,짬뽕
            case 6:
                return JAP; // 회,초밥
            case 7:
                return BBQ; // 고기
            case 8:
                return DAK; // 닭갈비,찜닭
            case 9:
                return KOR; // 한정식
            case 10:
                return SNACK; // 분식
            case 11:
                return CHICKEN; // 치킨
            case 12:
                return FASTFOOD; // 버거,샌드위치,토스트
            case 13:
                return PIZZA; // 피자
            default:
                return null;
        }
    }

    public String getDescription() {
        switch (this) {
            case GUKBAP:
                return "국밥";
            case MALA:
                return "마라탕";
            case WESTERN:
                return "파스타,스테이크";
            case BOJOK:
                return "보쌈,족발";
            case DONKATSU:
                return "돈까스";
            case CHN:
                return "짜장,짬뽕";
            case JAP:
                return "회,초밥";
            case BBQ:
                return "고기";
            case DAK:
                return "닭갈비,찜닭";
            case KOR:
                return "한정식";
            case SNACK:
                return "분식";
            case CHICKEN:
                return "치킨";
            case FASTFOOD:
                return "버거,샌드위치,토스트";
            case PIZZA:
                return "피자";
            default:
                return null;
        }
    }
}
