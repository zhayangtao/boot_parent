package com.example.boot_start_learning.chapter11_task;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
@Service("scheduledCarService")
@Repository
@Transactional
public class ScheduledCarServiceImpl extends CarServiceImpl {
    @Override
    @Scheduled(fixedDelay = 10000)
    public void updateCarAgeJob() {
        List<Car> carList = findAll();
        DateTime current = DateTime.now();
        carList.forEach(car -> {
            int age = Years.yearsBetween(car.getManufactureDate(), current).getYears();
            car.setAge(age);
            save(car);
        });
        System.out.println("Car age update job completed successfully");
    }
}
