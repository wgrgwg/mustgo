package com.mustgo.service;

import com.mustgo.domain.Address;
import com.mustgo.domain.Category;
import com.mustgo.domain.Restaurant;
import com.mustgo.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService { // 레포에 단순히 위임만하는 클래스
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void saveRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }

    @Transactional
    public void updateRestaurant(Long restaurantId, String name, Category category, Address address){
        Restaurant findRestaurant = restaurantRepository.findOne(restaurantId);

        findRestaurant.setName(name);
        findRestaurant.setCategory(category);
        findRestaurant.setAddress(address);
    }

    public List<Restaurant> findRestaurants(){
        return restaurantRepository.findAll();
    }

    public Restaurant findOne(Long restaurantId){
        return restaurantRepository.findOne(restaurantId);
    }

    public List<Restaurant> findRestaurantsByCategoryId(int categoryId){
        return restaurantRepository.findByCategory(Category.fromInteger(categoryId));
    }

    public List<Restaurant> findRestaurantsByCategoryIdAndAddress(int categoryId, Address address){
        return restaurantRepository.
                findByCategoryAddressOrderedByLikes(Category.fromInteger(categoryId), address);
    }
}