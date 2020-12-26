package java20.core.model.figure.skill;


import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 紫金葫芦封印，使1个敌方king单位下1回合不能使用技能
 * @date 2020-12-26
 **/
public class Cucurbit implements Skill {

    @Override
    public void employ(Creature master) {

    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
