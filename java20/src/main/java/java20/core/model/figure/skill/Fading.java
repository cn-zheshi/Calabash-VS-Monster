package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @description 弱者退散，使1个葫芦娃后退1格并封印能力1回合
 * @date 2020-12-26
 **/
public class Fading implements Skill {


    @Override
    public void employ(Creature master) {
        
    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
