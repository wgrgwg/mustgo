package com.mustgo.repository;

import com.mustgo.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) { em.persist(post); }

    public Post findOne(Long id) { return em.find(Post.class, id); }

    public List<Post> findAllByString(PostSearch PostSearch) {
        //language=JPAQL
        String jpql = "select o From Post o join o.member m";
        boolean isFirstCondition = true;
        //게시글 제목 검색
        if (PostSearch.getPostTitle() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색
        if (StringUtils.hasText(PostSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }
        TypedQuery<Post> query = em.createQuery(jpql, Post.class)
                .setMaxResults(100); //최대 1000건
        if (PostSearch.getPostTitle() != null) {
            query = query.setParameter("status", PostSearch.getPostTitle());
        }
        if (StringUtils.hasText(PostSearch.getMemberName())) {
            query = query.setParameter("name", PostSearch.getMemberName());
        }
        return query.getResultList();
    }

}
