package com.petar.timezoneadmin.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.petar.timezoneadmin.model.Timezone;
import com.petar.timezoneadmin.model.User;
@Mapper
public interface TimeZoneMapper {

	public List<User> findTimezonesByUserId(Long userId);

	public List<User> findAllTimezones();

	public Timezone findById(Long timezoneId);

	public void createTimezone(Timezone timezone);

	public Timezone findbyNameAndUserId(String name, Long userId);

	public void updateTimezone(Timezone timezone);

	public void deleteTimezone(Timezone foundTimezone);

}
