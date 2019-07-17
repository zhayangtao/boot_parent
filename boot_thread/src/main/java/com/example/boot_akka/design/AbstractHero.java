package com.example.boot_akka.design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/28
 * 享元模式
 */
public abstract class AbstractHero {
    final String name;//英雄名称
    final String[] skills = new String[4];//每个英雄4个技能

    public AbstractHero(String name) {
        this.name = getName();
        initSkills();
        checkSkills();
    }

    private void checkSkills() {
        Arrays.stream(skills).forEach(s -> {
            if (s == null) {
                throw new NullPointerException();
            }
        });
    }

    // 释放技能
    public void release(int index) {
        if (index < 0)
            index = 0;
        else if (index > 3)
            index = 3;
        System.out.println(name + "释放" + skills[index]);
    }

    // 物理攻击
    public void commonAttack() {
        System.out.println(name + "进行物理攻击");
    }

    public abstract String getName();

    public abstract void initSkills();

    /**
     * @author zhayangtao
     * @version 1.0
     * @since 2019/05/28
     */
    public static class Role {
        private AbstractHero hero;//角色选择的英雄
        private long hp;
        private long mp;

        Role(AbstractHero hero) {
            this.hero = hero;
        }

        //释放技能
        public void release(int index) {
            hero.release(index);
        }

        // 无力攻击
        public void commonAttack() {
            hero.commonAttack();
        }

        public long getHp() {
            return hp;
        }

        public void setHp(long hp) {
            this.hp = hp;
        }

        public long getMp() {
            return mp;
        }

        public void setMp(long mp) {
            this.mp = mp;
        }
    }

    /**
     * 提供共享功能
     */
    public static class HeroManager {
        private static HeroManager heroManager = new HeroManager();
        private Map<String, AbstractHero> heroMap;

        private HeroManager() {
            heroMap = new HashMap<>();
        }

        public static HeroManager getInstance() {
            return heroManager;
        }

        //该方法提供共享功能
        public AbstractHero getHero(String name) {
            AbstractHero hero = heroMap.get(name);
            if (hero == null) {
                if (name.equals("恶魔巫师")) {
                    hero = new Lion("恶魔巫师");
                } else if (name.equals("影魔")) {
                    hero = new SF("影魔");
                }
                heroMap.put(name, hero);
            }
            return hero;
        }
    }

    public static class Lion extends AbstractHero {

        public Lion(String name) {
            super(name);
        }

        public String getName() {
            return "恶魔巫师";
        }

        public void initSkills() {
            skills[0] = "穿刺";
            skills[1] = "妖术";
            skills[2] = "法力汲取";
            skills[3] = "死亡一指";
        }
    }

    public static class SF extends AbstractHero {

        public SF(String name) {
            super(name);
        }

        public String getName() {
            return "影魔";
        }

        public void initSkills() {
            skills[0] = "毁灭阴影";
            skills[1] = "支配死灵";
            skills[2] = "魔王降临";
            skills[3] = "魂之挽歌";
        }
    }

    public static void main(String[] args) {
        //假设有四个solo局，则使用了享元模式的情况下，其实恶魔巫师和影魔的实例各自只有一个
        HeroManager heroManager = HeroManager.getInstance();
        Role role1 = new Role(heroManager.getHero("恶魔巫师"));
        Role role2 = new Role(heroManager.getHero("影魔"));

        Role role3 = new Role(heroManager.getHero("恶魔巫师"));
        Role role4 = new Role(heroManager.getHero("影魔"));

        Role role5 = new Role(heroManager.getHero("恶魔巫师"));
        Role role6 = new Role(heroManager.getHero("影魔"));

        Role role7 = new Role(heroManager.getHero("恶魔巫师"));
        Role role8 = new Role(heroManager.getHero("影魔"));
    }
}
