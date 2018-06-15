package com.calprice.designmodel.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/06/14
 * 命令模式
 */
abstract class Command {
    Barbecuer barbecuer;

    Command(Barbecuer barbecuer) {
        this.barbecuer = barbecuer;
    }

    abstract void executeCommand();
}

class Barbecuer {

    void bakeMutton() {
        System.out.println("烤羊肉串");
    }

    void bakeChickenWing() {
        System.out.println("烤鸡翅");
    }
}

class BakeMuttonCommand extends Command {
    public BakeMuttonCommand(Barbecuer barbecuer) {
        super(barbecuer);
    }

    @Override
    void executeCommand() {
        barbecuer.bakeMutton();
    }

    @Override
    public String toString() {
        return "BakeMuttonCommand";
    }
}

class BakeChickenWingCommand extends Command {
    public BakeChickenWingCommand(Barbecuer barbecuer) {
        super(barbecuer);
    }

    @Override
    void executeCommand() {
        barbecuer.bakeChickenWing();
    }

    @Override
    public String toString() {
        return "BakeChickenWingCommand";
    }
}

// 服务员类
class Waiter {
    private Command command;

    void setOrder(Command command) {
        this.command = command;
    }

    void notifyC() {
        command.executeCommand();
    }
}

// 松耦合设计
class Waiter2 {
    private List<Command> commands = new ArrayList<>();

    void setOrder(Command command) {
        if (command.toString().equals("BakeChickenWingCommand")) {
            System.out.println("没鸡翅了");
        } else {
            commands.add(command);
            System.out.println("增加订单：" + command.toString() + " 时间：" + new Date());
        }
    }

    void cancelOrder(Command command) {
        commands.remove(command);
        System.out.println("取消订单：" + command.toString() + " 时间：" + new Date());
    }
    void notifyC() {
        commands.forEach(Command::executeCommand);
    }
}