package com.example.zRescue;

import com.example.zRescue.model.Issue;
import com.example.zRescue.model.Report;
import com.example.zRescue.respository.StorageRepository;
import com.example.zRescue.service.MessageAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class ZRescueApplicationTests {


	@Autowired
	private StorageRepository repository;
	@Test
	void contextLoads() {
		MessageAdapter messageAdapter=MessageAdapter.getInstance();

		for(int i=0;i<1000;i++) {

			Random rand = new Random();
			// Setting the upper bound to generate the
			// random numbers in specific range
			int upperbound = 90;

			double latitude = rand.nextDouble()*90;
			double longitude = rand.nextDouble()*90;

			Issue issue = new Issue(latitude, longitude, 156346, null);
			Report report = messageAdapter.createStationMessage(issue);

			//   System.out.println(report);
			repository.save(report);
		}

	}

}
