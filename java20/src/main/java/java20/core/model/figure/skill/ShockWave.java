package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 冲击波，击杀自身半径1格之外的前后左右4格内的所有敌方单位
 * @date 2020-12-26
 **/
public class ShockWave implements Skill {

    @Override
    public void employ(Creature master) {

    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
