package com.example.boot_start_learning.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/12
 */
public class FoodProviderServiceImpl2 implements FoodProviderService {
    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<>(3);
        lunchSet.add(new Food("mike"));
        lunchSet.add(new Food("biscuits"));
        return lunchSet;
    }
}
