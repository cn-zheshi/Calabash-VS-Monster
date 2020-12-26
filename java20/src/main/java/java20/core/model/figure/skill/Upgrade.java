package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;

/**
 * @author hwd
 * @date 2020-12-27 12:18 AM
 **/
public class Upgrade implements Skill {
    
    @Override
    public void employ(Creature master) {

    }

    @Override
    public boolean isAvailable(Creature master) {
        return false;
    }
}
