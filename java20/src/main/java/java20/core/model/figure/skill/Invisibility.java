package java20.core.model.figure.skill;


import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 隐身，使自身在下1回合无法被敌方选中
 * @date 2020-12-26
 **/
public class Invisibility implements Skill {


    @Override
    public void employ(Creature master) {
        
    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
