package java20.util;

/**
 * @author hwd
 * @date 2020-12-26 5:20 PM
 **/
public enum Status {

    /**
     * dead 死亡
     * alive 可以被双方选中
     * available 可以被己方选中但敌方不能选中 这个状态应该只有六娃会出现
     * reachable 可以被敌方选中但己方不能选中 也就是封印 技能和行动全部木大
     * invincible 停止思考
     * traitorous 叛变
     */
    DEAD, ALIVE, AVAILABLE, REACHABLE, INVINCIBLE, TRAITOROUS
}
