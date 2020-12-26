package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 金刚不坏，免疫1次死亡
 * @date 2020-12-26
 **/
public class Indestructibility implements Skill {


    @Override
    public void employ(Creature master) {
        
    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
