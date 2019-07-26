package com.example.boot_start_learning.chapter5_spring_aop;

import org.aopalliance.intercept.Joinpoint;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/18
 */
public class Documentarist {
    GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setDep(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}

class AuditAdvice {
    public void simpleBeforeAdvice(Joinpoint joinpoint) {
        System.out.println("Executing:" + joinpoint.getStaticPart());
    }
}
