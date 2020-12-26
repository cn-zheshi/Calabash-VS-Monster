package java20.core.model.figure.skill;


import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 喷火，击杀自己所在直线上的前2个单位
 * @date 2020-12-26
 **/
public class Flame implements Skill {


    @Override
    public void employ(Creature master) {
        
    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
