package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 蛊惑，控制1个葫芦娃的移动控制权和技能控制权，但葫芦娃攻击目标不能是爷爷
 * @date 2020-12-26
 **/
public class Seduction implements Skill {


    @Override
    public void employ(Creature master) {

    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
