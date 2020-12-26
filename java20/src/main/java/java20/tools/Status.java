package java20.tools;

/**
 * @author hwd
 * @date 2020-12-26 5:20 PM
 **/
public enum Status {
    /**
     * dead 死亡
     * alive 可以被双方选中
     * available 可以被己方选中但敌方不能选中
     * reachable 可以被敌方选中但己方不能选中
     */
    DEAD, ALIVE, AVAILABLE, REACHABLE
}
