package com.example.boot_15.threads;

import org.springframework.stereotype.Service;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Override
    public void someLogic() {
        System.out.println("do something");
    }

}
