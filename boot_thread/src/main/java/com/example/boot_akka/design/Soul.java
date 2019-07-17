package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
public class Soul {
    private Doppelganger doppelganger;

    public void createDoppelganger(String name) {
        doppelganger = new Doppelganger(name);
    }

    public Doppelganger getDoppelganger() {
        return doppelganger;
    }

    public void createBody(String body) {
        System.out.println("设置" + doppelganger.getName() + "的" + body);
    }
    public void createHead(String head) {
        System.out.println("设置" + doppelganger.getName() + "的" + head);
        doppelganger.setHead(head);
    }

    public void createLeftArm(String leftArm) {
        System.out.println("设置" + doppelganger.getName() + "的" + leftArm);
        doppelganger.setLeftArm(leftArm);
    }

    public void createLeftHand(String leftHand) {
        System.out.println("设置" + doppelganger.getName() + "的" + leftHand);
        doppelganger.setLeftHand(leftHand);
    }

    public void createRightArm(String rightArm) {
        System.out.println("设置" + doppelganger.getName() + "的" + rightArm);
        doppelganger.setRightArm(rightArm);
    }

    public void createRightHand(String rightHand) {
        System.out.println("设置" + doppelganger.getName() + "的" + rightHand);
        doppelganger.setRightHand(rightHand);
    }

    public void createLeftLeg(String leftLeg) {
        System.out.println("设置" + doppelganger.getName() + "的" + leftLeg);
        doppelganger.setLeftLeg(leftLeg);
    }

    public void createLeftFoot(String leftFoot) {
        System.out.println("设置" + doppelganger.getName() + "的" + leftFoot);
        doppelganger.setLeftFoot(leftFoot);
    }

    public void createRightLeg(String rightLeg) {
        System.out.println("设置" + doppelganger.getName() + "的" + rightLeg);
        doppelganger.setRightLeg(rightLeg);
    }

    public void createRightFoot(String rightFoot) {
        System.out.println("设置" + doppelganger.getName() + "的" + rightFoot);
        doppelganger.setRightFoot(rightFoot);
    }

}
