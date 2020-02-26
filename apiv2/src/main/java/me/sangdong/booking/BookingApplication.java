package me.sangdong.booking;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingApplication implements ApplicationRunner {


	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //blockingStub.GetRates()
    }
}
