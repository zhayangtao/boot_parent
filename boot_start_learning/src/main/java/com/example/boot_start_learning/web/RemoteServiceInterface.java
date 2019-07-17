package com.example.boot_start_learning.web;

import com.example.boot_start_learning.domain.UserInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/06/12
 */
public interface RemoteServiceInterface extends Remote {
    List<UserInfo> queryAllUserInfo() throws RemoteException;
}
