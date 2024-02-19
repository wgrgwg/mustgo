package com.mustgo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Month;

@Getter @Setter
public class Message {
    int age;
    Gender gender;
    int currentSeason;
    int isSpicy;
    int isRice;
    int isDinner;
    int priceFavor;

    public void setCurrentSeason() {
        Month currentMonth = LocalDateTime.now().getMonth();

        switch (currentMonth) {
            case DECEMBER:
            case JANUARY:
            case FEBRUARY:
                this.currentSeason =  0; // Winter
                break;
            case MARCH:
            case APRIL:
            case MAY:
                this.currentSeason = 1; // Spring
                break;
            case JUNE:
            case JULY:
            case AUGUST:
                this.currentSeason = 2; // Summer
                break;
            case SEPTEMBER:
            case OCTOBER:
            case NOVEMBER:
                this.currentSeason = 3; // Autumn/Fall
                break;
            default:
                this.currentSeason = -1; // Error or unknown season
        }
    }

    public void setIsDinner() {
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();

        if (hour >=10 && hour < 17) {
            this.isDinner = 0;  // 10:00 ~ 17:00 Lunch
        } else if (hour >= 17 && hour < 22) {
            this.isDinner = 1;  // Between 17:00 and 22:00 Dinner
        } else {
            this.isDinner = 2;  // After 22:00 night meal
        }
    }
}

