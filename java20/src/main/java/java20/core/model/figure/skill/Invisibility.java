package java20.core.model.figure.skill;


import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 隐身，使自身在下1回合无法被敌方选中
 * @date 2020-12-26
 **/
public class Invisibility extends Skill {


    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    public Invisibility(int cd, boolean onlyOnce, boolean available) {
        super(cd, onlyOnce, available);
    }

    @Override
    public void employ(Creature master) {
        master.hide(1);
        this.leftTime = this.cd;
    }

}
