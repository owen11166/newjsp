package com.example.demo.dao;


import com.example.demo.entity.User;

public interface CustomRepository {
		void detach(User user);
}
