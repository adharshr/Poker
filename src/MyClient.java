import java.util.*;
public class MyClient {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a Poker Hand: ");
        String input1 = console.nextLine();
        System.out.println("Enter another Poker Hand: ");
        String input2 = console.nextLine();

        PokerHand hand1 = new PokerHand(input1);
        PokerHand hand2 = new PokerHand(input2);
        
        int compare = hand1.compareTo(hand2);
        if(compare < 0) {
            System.out.println("Hand 2 beats Hand 1");
        } else if(compare > 0) {
            System.out.println("Hand 1 beats Hand 2");
        } else {
            System.out.println("Tie");
        }
    }
}
