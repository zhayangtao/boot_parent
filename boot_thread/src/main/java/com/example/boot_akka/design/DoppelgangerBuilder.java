package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
 interface DoppelgangerBuilder  {

     void createDoppelganger(String name);

     void buildBody();

     void buildHead();

     void buildLeftArm();

     void buildLeftHand();

     void buildRightArm();

     void buildRightHand();

     void buildLeftLeg();

     void buildLeftFoot();

     void buildRightLeg();

     void buildRightFoot();

     Doppelganger getDoppelganger();
}
