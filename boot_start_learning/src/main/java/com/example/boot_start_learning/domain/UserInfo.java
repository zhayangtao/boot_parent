package com.example.boot_start_learning.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author no one
 * @version 1.0
 * @since 2019/06/12
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -670338731016923823L;
    private String userName;
    private String userDesc;
    private Integer userAge;
    private Boolean userSex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int end = m+n-1;
        m--;
        n--;
        while(n>=0){
            if(m<0){
                nums1[end] = nums1[n];
                n--;
            } else if(nums1[m]>nums2[n]){
                nums1[end] = nums1[m];
                m--;

            } else {
                nums1[end] = nums2[n];
                n--;
            }
            end--;
        }
//        while(n>0){
//            nums1[end--] = m<0 ? nums2[n--] : (nums1[m]<nums2[n] ? nums2[n--] : nums1[m--]);
//        }
    }

    public static void main(String[] args) {
        int nums1[] = {1,2,3,0,0,0};
        int nums2[] = {2,5,6};
        int m = 3;
        int n = 3;
        merge(nums1,m,nums2,n);
        System.out.println(Arrays.toString(nums1));


        int num = 0;
        for (int i = 0; i < 200; i++) {
            num = num++;
        }
        System.out.println(num);
    }
}
