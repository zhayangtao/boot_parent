package com.example.boot_start_learning.web;

import com.example.boot_start_learning.domain.UserInfo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/06/12
 */
public class RemoteUnicastServiceImpl extends UnicastRemoteObject implements RemoteServiceInterface {

    private static final long serialVersionUID = -3832543732168209648L;

    protected RemoteUnicastServiceImpl() throws RemoteException {
    }

    @Override
    public List<UserInfo> queryAllUserInfo() {
        List<UserInfo> userInfos = new ArrayList<>();

        UserInfo user1 = new UserInfo();
        user1.setUserAge(21);
        user1.setUserDesc("userDesc1");
        user1.setUserName("userName1");
        user1.setUserSex(true);
        userInfos.add(user1);

        UserInfo user2 = new UserInfo();
        user2.setUserAge(21);
        user2.setUserDesc("userDesc2");
        user2.setUserName("userName2");
        user2.setUserSex(false);
        userInfos.add(user2);
        return userInfos;
    }

    /**
     * 将 RMI 注册到“本地 RMI 注册表”
     * @param args
     */
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        // LocateRegistry：RMI 服务位置仓库
        LocateRegistry.createRegistry(1099);
        // 向 LocateRegistry 注册 RMI
        RemoteUnicastServiceImpl unicastService = new RemoteUnicastServiceImpl();
        // 通过 java 名字服务技术，将具体的 RMI 实现绑定一个访问路径，注册到 LocateRegistry
        Naming.rebind("rmi://localhost:1099/queryAllUserInfo", unicastService);

    }

}
