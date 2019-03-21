package com.example.boot_15;

import com.example.boot_15.threads.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Boot15Application.class)
public class AopUtilsTest {

    @Autowired
    private IEmployeeService iEmployeeService;

    @Test
    public void testIdAop() {
        assertTrue(AopUtils.isAopProxy(iEmployeeService));
    }
}
