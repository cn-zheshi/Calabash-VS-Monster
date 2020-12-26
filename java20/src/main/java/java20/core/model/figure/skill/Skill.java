package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;

public interface Skill {

    /**
     * @param master 方法的使用者
     */
    void employ(Creature master);

    boolean isAvailable(Creature master);
}
