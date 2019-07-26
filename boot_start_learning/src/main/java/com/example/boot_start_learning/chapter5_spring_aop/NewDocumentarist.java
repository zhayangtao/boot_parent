package com.example.boot_start_learning.chapter5_spring_aop;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/18
 */
public class NewDocumentarist extends Documentarist{
    @Override
    public void execute() {
        guitarist.sing();
        guitarist.sing(new Guitar());
        guitarist.talk();
    }
}
