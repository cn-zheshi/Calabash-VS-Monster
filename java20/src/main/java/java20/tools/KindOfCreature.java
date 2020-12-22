package java20.tools;

public enum KindOfCreature {
    Calabash(0), First(1), Second(2), Third(3), Forth(4), Fifth(5), Sixth(6), Seventh(7), Grandpa(8), // Calabash and
                                                                                                      // Grandpa
    Monster(9), StrongerMonster(10), Snake(11), Scorpion(12), Monsters(13);// Monsters

    private int value;

    private KindOfCreature(int val) {
        value = val;
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
}
