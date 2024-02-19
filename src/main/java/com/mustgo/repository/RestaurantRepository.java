package com.mustgo.repository;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.mustgo.domain.Address;
import com.mustgo.domain.Category;
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

    public List<Restaurant> findByCategory(Category category){
        return em.createQuery("select i from Restaurant i where i.category = :category", Restaurant.class)
                .getResultList();
    }

    public List<Restaurant> findByCategoryAddressOrderedByLikes(Category category, Address address) {
//        return em.createQuery("select i from Restaurant i where i.category = :category" +
//                        " and i.address.district = :address_district" +
//                        " order by i.likes desc", Restaurant.class)
//                .setParameter("category", category)
//                .setParameter("address_district", address.getDistrict())
//                .setMaxResults(5)
//                .getResultList();
        return em.createQuery("select i from Restaurant i where i.category = :category" +
                        " and i.address.district = :address_district" +
                        " order by case when i.address.dong = :address_dong then 0 else 1 end, i.likes desc", Restaurant.class)
                .setParameter("category", category)
                .setParameter("address_district", address.getDistrict())
                .setParameter("address_dong", address.getDong())
                .setMaxResults(5)
                .getResultList();
    }

}
