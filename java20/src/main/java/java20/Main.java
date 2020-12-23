package java20;

import java.util.ArrayList;

import java20.ablility.GrandpaAbility;
import java20.ablility.ScorpionAbility;
import java20.ablility.SnakeAbility;
import java20.king.King;
import java20.movestrategy.GrandpaStrategy;
import java20.movestrategy.ScorpionStrategy;
import java20.movestrategy.SnakeStrategy;
import java20.tools.KindOfCreature;
import java20.tools.Position;
import java20.warrior.Creature;

public class Main {
    ArrayList<Creature> creatures;
    ArrayList<King> kings;
    Client client;
    public static void main(String[] args) {

    }
    Main(){
        client=new Client();
        Position grandpaPosition=new Position(5, 0);
        King grandpa=new King(KindOfCreature.Grandpa, grandpaPosition, new GrandpaStrategy(), new GrandpaAbility());
        kings.add(grandpa);
        Position snakePosition=new Position(5, 9);
        King snake=new King(KindOfCreature.Snake, snakePosition, new SnakeStrategy(), new SnakeAbility());
        kings.add(snake);
        Position scorpionPosition=new Position(4, 9);
        King scorpion=new King(KindOfCreature.Scorpion, scorpionPosition, new ScorpionStrategy(), new ScorpionAbility());
    }
}
