package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canGetWhiskiesByYear() {
		List<Whisky> result = whiskyRepository.getWhiskiesByYear(2018);
		assertEquals(2, result.size());
	}

	@Test
	public void canGetDistillariesByRegion() {
		List<Distillery> result = distilleryRepository.getDistilleriesByRegion("Highland");
		assertEquals(1, result.size());
	}

	@Test
	public void canGetWhiskiesOfCertainAgeByDistillery() {
		List<Whisky> result = whiskyRepository.getWhiskiesByDistillaryIdAndAge(1L, 12);
		assertEquals(1, result.size());
	}
}
