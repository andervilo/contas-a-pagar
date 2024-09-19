package com.anderson.contasapagar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

class ContasAPagarApplicationTests {

	@Test
	void contextLoads() {
		var startDate = LocalDate.now();
		var endDate = LocalDate.now();
		System.out.println("startDate = " + startDate.atTime(0, 0, 0));
		System.out.println("endDate = " + endDate.atTime(23, 59, 59));
	}

}
