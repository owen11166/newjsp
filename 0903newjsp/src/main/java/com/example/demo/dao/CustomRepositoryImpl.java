package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public void detach(User user) {
		em.detach(user);

	}
}
