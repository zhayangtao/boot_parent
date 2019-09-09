package com.example.boot_start_learning.chapter11_task;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/30
 */
@Service(value = "carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {
    private static Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private boolean done;

    @Autowired
    private CarRepository carRepository;
    @Override
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void updateCarAgeJob() {
        List<Car> cars = findAll();
        DateTime current = DateTime.now();
        logger.info("car age update job started");

        cars.forEach(car -> {
            int age = Years.yearsBetween(car.getManufactureDate(), current).getYears();
            car.setAge(age);
        });
        logger.info("Car age update job completed successfully");
        done = true;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
