/* Adharsh Ranganathan
 * Limeade Coding Challenge
 * 
 * These are my test cases for my PokerHand class.
 * I went with a mostly black-box testing approach.
 * I tested my PokerHand class by only using the knowledge
 * provided by the method headers and not looking
 * at the code within each method.
 * */
import static org.junit.Assert.*;

import org.junit.Test;

public class TestPoker {

    @Test(expected = IllegalArgumentException.class)
    public void TestInvalidHand() {
        String input = "THTHTHTHTHTHTHTHTH";
        PokerHand hand = new PokerHand(input);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TestNullHand() {
        String input = "";
        PokerHand hand = new PokerHand(input);
    }
    
    @Test
    public void TestRoyalFlush() {
        String input = "THJHQHAHKH";
        TestRank(input, 1);
    }
    
    @Test
    public void TestStraightFlush() {
        String input = "6S7S9S8STS";
        TestRank(input, 2);
    }
    
    @Test
    public void TestFourOfAKind() {
        String input = "5S5D5C5H7C";
        TestRank(input, 3);
    }
    
    @Test
    public void TestFullHouse() {
        String input = "AHASACKHKS";
        TestRank(input, 4);
    }
    
    @Test 
    public void TestFlush() {
        String input = "2S3S5S6STS";
        TestRank(input, 5);
    }
    
    @Test
    public void TestStraight() {
        String input = "5H4C3D2SAS";
        TestRank(input, 6);
    }
    
    @Test
    public void TestThreeKind() {
        String input = "TH4DASAHAD";
        TestRank(input, 7);
    }
    
    @Test
    public void TestTwoPair() {
        String input = "ASTSQDAC2D";
        TestRank(input, 8);
    }
    
    @Test
    public void TestOnePair() {
        String input = "4DTS6D4S7H";
        TestRank(input, 9);
    }
    
    @Test
    public void TestHighCard() {
        String input = "THJHQHAHKH";
        TestRank(input, 10);
    }
    
    public void TestRank(String input, int expected) {
        PokerHand hand = new PokerHand(input);
        int rank = hand.getRank();
        assertEquals("Returns incorrect rank", expected, expected);
    }
    
    @Test
    public void TestHighestCard() {
        String input = "THJHQHAHKH";
        PokerHand hand = new PokerHand(input);
        int highest = hand.getHighestCard();
        int expected = 14;
        assertEquals("Returns incorrect highest card", expected, highest);
        
        input = "5C6S8C4H5D";
        PokerHand hand2 = new PokerHand(input);
        highest = hand2.getHighestCard();
        expected = 8;
        assertEquals("Returns incorrect highest card", expected, highest); 
    }
    
    @Test
    public void TestIsFlush() {
        String input = "2S3S5S6STS";
        PokerHand hand = new PokerHand(input);
        assertTrue("Isn't a flush", hand.isFlush());
        
        input = "2S3S5S6STH";
        PokerHand hand2 = new PokerHand(input);
        assertFalse("Is a flush", hand2.isFlush());
    }
    
    @Test
    public void TestIsStraight() {
        String input = "5H4C3D2SAS";
        PokerHand hand = new PokerHand(input);
        assertTrue("Isn't a straight", hand.isStraight());
    }
    
    @Test
    public void TestCompareToDifferentRanks() {
        String input1 = "5S5D5C5H7C";
        PokerHand hand1 = new PokerHand(input1);
        String input2 = "2S3S5S6STS";
        PokerHand hand2 = new PokerHand(input2);
        int n = hand1.compareTo(hand2);
        boolean test = n > 0;
        assertTrue("Incorrect compare", test);
    }
    
    @Test
    public void TestCompareToKicker() {
        String input1 = "5S5D5C5HTC";
        PokerHand hand1 = new PokerHand(input1);
        String input2 = "5S5D5C5H7C";
        PokerHand hand2 = new PokerHand(input2);
        int n = hand1.compareTo(hand2);
        boolean test = n > 0;
        assertTrue("Incorrect compare", test);
    }
    
    @Test
    public void TestCompareToHighCard() {
        String input1 = "5HKSQDJC9S";
        PokerHand hand1 = new PokerHand(input1);
        String input2 = "AHKSQDJC9S";
        PokerHand hand2 = new PokerHand(input2);
        int n = hand1.compareTo(hand2);
        boolean test = n < 0;
        assertTrue("Incorrect compare", test);
    }
    
    @Test
    public void TestEqualCards() {
        String input = "5S5D5C5H7C";
        PokerHand hand = new PokerHand(input);
        int n = hand.compareTo(hand);
        boolean test = n == 0;
        assertTrue("Incorrect compare", test);
    }
}
