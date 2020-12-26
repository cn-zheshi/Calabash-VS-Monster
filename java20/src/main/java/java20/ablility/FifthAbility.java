package java20.ablility;

import java20.Controller;
import java20.warrior.Creature;

import java.util.ArrayList;

public class FifthAbility extends Ability {


    /**
     * 封印指无法别己方选中，但可以被敌方选中
     */
    @Override
    public void doSth() {
        // TODO Auto-generated method stub
        ArrayList<Creature> creatures = Controller.getInstance().getCreatures();
        
    }

    @Override
    public boolean canDoSth() {
        // TODO Auto-generated method stub
        return false;
    }

}
