package com.petar.timezoneadmin.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petar.timezoneadmin.model.User;
@Mapper
public interface UserMapper {

	public User findByUsername(String username);
	
	public User findById(Long id);

	public void createUser(User user);

	public void updateUser(User user);
	
	public void deleteUser(Long userId);

	public List<User> findAllUsers();
}
