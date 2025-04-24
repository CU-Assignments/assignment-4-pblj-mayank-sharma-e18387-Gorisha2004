import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CardCollection {

    // HashMap to store cards with symbol as key
    private static Map<String, List<String>> cardCollection = new HashMap<>();

    // Method to initialize the card collection
    private static void initializeCards() {
        // Add cards under respective symbols
        List<String> hearts = new ArrayList<>();
        hearts.add("Ace of Hearts");
        hearts.add("2 of Hearts");
        hearts.add("3 of Hearts");
        hearts.add("King of Hearts");

        List<String> spades = new ArrayList<>();
        spades.add("Ace of Spades");
        spades.add("2 of Spades");
        spades.add("King of Spades");

        List<String> diamonds = new ArrayList<>();
        diamonds.add("Ace of Diamonds");
        diamonds.add("Queen of Diamonds");
        diamonds.add("10 of Diamonds");

        List<String> clubs = new ArrayList<>();
        clubs.add("Ace of Clubs");
        clubs.add("7 of Clubs");
        clubs.add("3 of Clubs");

        // Storing the symbols as keys and corresponding cards as values
        cardCollection.put("Hearts", hearts);
        cardCollection.put("Spades", spades);
        cardCollection.put("Diamonds", diamonds);
        cardCollection.put("Clubs", clubs);
    }

    // Method to search and display cards by symbol
    private static void searchCardsBySymbol(String symbol) {
        List<String> cards = cardCollection.get(symbol);

        if (cards != null && !cards.isEmpty()) {
            System.out.println("\nCards of " + symbol + ":");
            for (String card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeCards();  // Initialize the card collection

        // User interaction
        System.out.println("Welcome to the Card Collection System!");
        System.out.print("Enter the symbol (Hearts, Spades, Diamonds, Clubs) to find all cards of that symbol: ");
        String symbol = scanner.nextLine().trim();

        // Search for the cards by symbol
        searchCardsBySymbol(symbol);

        scanner.close();
    }
}
