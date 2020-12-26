package java20.core.model.figure.skill;


import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 洞察，使敌不敢轻举妄动，封印敌方全体技能1回合
 * @date 2020-12-26
 **/
public class Insight implements Skill {


    @Override
    public void employ(Creature master) {
        
    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
