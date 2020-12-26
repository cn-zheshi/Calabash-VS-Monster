package java20.core.model.figure.skill;


/**
 * @author hwd
 * @description 喷水，使自身所在直线上的敌方单位封印1回合 封印指无法别己方选中，但可以被敌方选中
 * @date 2020-12-26
 **/
public class Water implements Skill {


    @Override
    public void employ(java20.core.model.figure.Creature master) {

    }

    @Override
    public boolean isAvailable(java20.core.model.figure.Creature master) {
        return false;
    }
}
