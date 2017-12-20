/* Adharsh Ranganathan
 * 3/20/17
 * Limeade Coding Challenge
 * 
 * PokerHand represents a 5 card
 * Texas Hold'Em hand. PokerHands can be
 * compared to other PokerHands to determine
 * which one is the winning hand. 
 * */
import java.util.*;
public class PokerHand {
    private Map<Character, Integer> faces; 
    private Map<Integer, Integer> counts;
    private Set<Integer> values;
    private Set<Character> suites;
    private int kicker;

    public PokerHand(String input) {
        if(input == null) {
            throw new IllegalArgumentException("Cannot accept null parameter.");
        }
        
        input = input.trim();
        if(input.length() != 10) {
            throw new IllegalArgumentException("Valid hand must have 5 cards.");
        }
        
        input = input.toUpperCase();
        this.kicker = 0;
        //HashMap for O(1) containsKey
        //Can also use array, but HashMap allows faster lookup
        this.faces = new HashMap<Character, Integer>();
        this.faces.put('A', 14);
        this.faces.put('K', 13);
        this.faces.put('Q', 12);
        this.faces.put('J', 11);
        this.faces.put('T', 10);

        this.values = new TreeSet<Integer>();
        this.suites = new HashSet<Character>();
        //Values same as keySet of counts, but it is a TreeSet
        //so get largest value is O(logN) and need O(1) containsKey
        //from HashMap
        this.counts = new HashMap<Integer, Integer>();
        for(int i = 0; i < input.length() - 1; i+=2) {
            char value = input.charAt(i);
            char suite = input.charAt(i + 1);
            
            this.suites.add(suite);
            int numberValue = -1;
            if(this.faces.containsKey(value)) {  
                numberValue = this.faces.get(value);
            } else {
                numberValue = value - '0';
            }

            values.add(numberValue);
            if(!this.counts.containsKey(numberValue)) {
                this.counts.put(numberValue, 1);
            } else {
                int oldCount = this.counts.get(numberValue);
                this.counts.put(numberValue, oldCount + 1);
            }
        }  
    }

    /* @returns int representing the rank of a hand 
     * rank 1 represents royal flush, rank 10 represents 
     * high card.
     * */
    public int getRank() {
        if(this.isFlush()) {
            if(this.isStraight()) {
                if(this.getHighestCard() == 14) {
                    return 1;
                }

                return 2;
            }

            return 5;
        }

        if(this.isStraight()) {
            return 6;
        }

        if(this.values.size() == 2) {
            if(this.suites.size() == 4) {
                this.setKicker(1);
                return 3;
            }

            if(this.counts.values().contains(3)) {
                this.setKicker(3);
                return 4;
            }
        }

        if(this.counts.values().contains(3)) {
            return 7;
        }

        if(this.counts.values().contains(2)) {
            if(this.values.size() == 3) {
                return 8;
            }
            
            return 9;
        }

        return 10;
    }

    /* @param int representing the number of cards present in the
     * kicker for a given rank.
     * @throw IllegalArgumentException if number of cards
     * is negative
     * */
    private void setKicker(int val) {
        if(val < 0) {
            throw new IllegalArgumentException();
        }
        
        for(int key: this.counts.keySet()) {
            if(this.counts.get(key) == val) {
                this.kicker = key;
                break;
            }
        }
    }
    
    /* @returns int representing value of card in
     * the PokerHand with the highest value.
     * Ace is considered as the highest value card
     * in the deck.
     * */
    public int getHighestCard() {
        Object[] arr = this.values.toArray();
        int value = (int) arr[arr.length - 1];
        return value;
    }

    /* @returns an int representing the kicker.
     * Kicker is the card that determines the better of
     * two hands with the same rank in rank 3 or 4.
     * */
    public int getKicker() {
        return this.kicker;
    }
    
    /* @returns true if all the cards in the PokerHand 
     * belong to the same suite, false otherwise.
     * */
    public boolean isFlush() {
        return this.suites.size() == 1;
    }

    /* @returns true if cards in ascending order, false
     * otherwise.
     * Ace can be considered as less than 2 or greater
     * than King in value.
     * */
    public boolean isStraight() {
        Object[] arr = this.values.toArray();
        int val = (int) arr[arr.length - 1] - (int)arr[0];
        
        //Always use the HashMap for find b/c O(1) find
        if(!this.counts.containsKey(14)) {
            return val == 4;
        }
           
        //Ace can be value = 1 in straight starting
        //at 5 only (5, 4, 3, 2, A)
        int aceAsOne = (int)arr[arr.length - 2] - 1;
        return val == 4 || aceAsOne == 4;
    }

    /* @param PokerHand representing a second PokerHand other
     * @returns int representing which hand is better
     * positive int means other is worse than current hand
     * negative int means other is better than current hand
     * 0 means both hands are equal in value.
     * */
    public int compareTo(PokerHand other) {
        if(this.getRank() != other.getRank()) {
            return -1 * (this.getRank() - other.getRank());
        }
        
        if(this.getKicker() != 0) {
            return this.getKicker() - other.getKicker();
        }
        
        return this.getHighestCard() - other.getHighestCard();
    }
}
