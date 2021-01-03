package java20.core.model.figure.skill;

import java20.core.model.figure.Creature;
import lombok.Getter;

/**
 * @author hwd
 * @description
 * @date 2020-12-27
 **/
@Getter
public abstract class Skill {

    protected final int cd;
    protected final boolean onlyOnce;
    protected int leftTime;

    /**
     * @param cd        如果为-2表示没有技能 -1表示随时可用且只能用一次的技能 如变身
     * @param onlyOnce  只能用一次
     * @param available 如果为0表示当前可用
     */
    protected Skill(int cd, boolean onlyOnce, boolean available) {
        this.cd = cd * 2;
        this.onlyOnce = onlyOnce;
        this.leftTime = ((available) ? 0 : this.cd);
    }

    /**
     * @param master 方法的使用者
     */
    public abstract void employ(Creature master);

    public boolean isAvailable() {
        return this.leftTime == 0;
    }

    public void updateCooldown() {
        if (this.cd == 0 || this.onlyOnce || this.leftTime == 0)
            return;
        --this.leftTime;
    }

    public int getCd() {
        return this.cd / 2;
    }

    public int getLeftTime() {
        return this.leftTime / 2;
    }

    /**
     * 封印技能一回合一般会用到这个 也可以用于加快技能冷却
     */
    public void modifyCooldown(int offset) {
        this.leftTime += offset * 2;
        if (this.leftTime < 0) {
            this.leftTime = 0;
        }
    }
}
