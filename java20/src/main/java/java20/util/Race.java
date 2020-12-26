package java20.util;

public enum Race {

    /**
     * Calabash和Monsters是阵营名
     */
    Calabash(0),
    Monster(13),

    First(1),
    Second(2),
    Third(3),
    Forth(4),
    Fifth(5),
    Sixth(6),
    Seventh(7),
    Grandpa(8),

    Goblin(9),
    Demon(10),
    Snake(11),
    Scorpion(12);


    private int value;

    Race(int val) {
        this.value = val;
    }

    public boolean isCalabash() {
        return value < 8 && value >= 0;
    }

    public boolean isMonster() {
        return value > 8 && value <= 10;
    }

    public boolean isGrandpa() {
        return value == 8;
    }

    public boolean isKingMonster() {
        return value == 12 || value == 11;
    }

    public boolean isKing() {
        return this.value == 8 || this.value == 11 || this.value == 12;
    }
}
