package com.petar.timezoneadmin.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/time")
public class CurrentTimeController {
	
	@Value("${server.gmt.offset.in.hours}")
	private Integer serverGmtOffsetInHours;
	
	private static final int MILIS_IN_ONE_HOUR = 3600000;
	
	@GetMapping(value = "/current-gmt-milis", produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Long> currentGmtMilis() {
		return Collections.singletonList(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis() - MILIS_IN_ONE_HOUR * serverGmtOffsetInHours);
	}
}
