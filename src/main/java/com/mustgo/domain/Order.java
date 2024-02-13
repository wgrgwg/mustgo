package com.mustgo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Month;

@Getter @Setter
public class Order {
    Member member;
    int currentSeason;
    int isDinner;
    int orderFrequency;

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

        this.isDinner =  hour >= 17 ? 1 : 0;
    }
}

