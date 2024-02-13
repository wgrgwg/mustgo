package com.mustgo.controller;

import com.mustgo.domain.Restaurant;
import com.mustgo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class  RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String list(Model model){
        List<Restaurant> restaurants = restaurantService.findRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "restaurants/restaurantsList";
    }
}
