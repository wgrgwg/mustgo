package com.mustgo.repository;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.mustgo.domain.Restaurant;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager em;

    public void save(Restaurant restaurant){
        if(restaurant.getId()==null){
            em.persist(restaurant); //신규로 등록
        } else{
            em.merge(restaurant); // update
        }
    }

    public Restaurant findOne(Long id){
        return em.find(Restaurant.class, id);
    }

    public List<Restaurant> findAll(){
        return em.createQuery("select i from Restaurant i", Restaurant.class)
                .getResultList();
    }
}
