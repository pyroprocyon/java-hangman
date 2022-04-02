import java.util.Scanner;

public class Hangman {

  public static final String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
  "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
  "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
  "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
  "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
  "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
  "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
  "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
  "wombat", "zebra"};

  public static final String[] gallows = {
      "+---+\n" +
      "|   |\n" +
      "    |\n" +
      "    |\n" +
      "    |\n" +
      "    |\n" +
      "=========\n",

      "+---+\n" +
      "|   |\n" +
      "O   |\n" +
      "    |\n" +
      "    |\n" +
      "    |\n" +
      "=========\n",

      "+---+\n" +
      "|   |\n" +
      "O   |\n" +
      "|   |\n" +
      "    |\n" +
      "    |\n" +
      "=========\n",

      " +---+\n" +
      " |   |\n" +
      " O   |\n" +
      "/|   |\n" +
      "     |\n" +
      "     |\n" +
      " =========\n",

      " +---+\n" +
      " |   |\n" +
      " O   |\n" +
      "/|\\  |\n" +
      "     |\n" +
      "     |\n" +
      " =========\n",

      " +---+\n" +
      " |   |\n" +
      " O   |\n" +
      "/|\\  |\n" +
      "/    |\n" +
      "     |\n" +
      " =========\n",

      " +---+\n" +
      " |   |\n" +
      " O   |\n" +
      "/|\\  |\n" +
      "/ \\  |\n" +
      "     |\n" +
      " =========\n"
    };

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.println("Welcome! Let's play Hangman. \n");
    System.out.println("Type 'exit' to quit the game.");
    System.out.println("If you are ready press any key...");
    String ans = scan.nextLine();

    if (ans.equals("exit")) {
      System.out.println("\nSome other time!");
      System.exit(0);
    }

    String word = randomWord(words);
    char[] placeholder = makePlaceholder(word);
    char[] missedGuess = new char[6];
    int missed = 0;

    while (true) {
      printGallows(gallows, missed);

      System.out.print("Word: \t");
      printPlaceholder(placeholder);

      if (String.valueOf(placeholder).equals(word)) {
        System.out.println("\n\nYou win! Good Job!");
        break;
      }

      if (missed == 6) {
        System.out.println("\n\nRIP!");
        System.out.println("The word is " + word.toUpperCase());
        break;
      }

      System.out.print("\n\nMisses: ");
      printMissedGuess(missedGuess);

      System.out.print("\n\nGuess: \t" );
      String guess = scan.nextLine().toLowerCase().trim();

      if (!guess.isEmpty()) {
        if (checkGuess(word, guess)) {
          updatePlaceholder(word, placeholder, guess);
        } else {
          missedGuess[missed] = guess.charAt(0);
          missed++;
        }
      }
    }

    scan.close();
  }

  public static void printGallows(String[] gallows, int miss) {
    System.out.println("\n" + gallows[miss]);
  }

  public static String randomWord(String[] words) {
    int num = (int)(Math.random() * words.length);
    return words[num];
  }

  public static char[] makePlaceholder(String word) {
    char[] arrChar = word.toCharArray();
    for (int i = 0; i < arrChar.length; i++) {
      arrChar[i] = '_';
    }
    return arrChar;
  }

  public static void printPlaceholder(char[] placeholder) {
    for (int i = 0; i < placeholder.length; i++) {
      System.out.print(placeholder[i] + " ");
    }
  }

  public static void printMissedGuess(char[] missedGuess) {
    for (int i = 0; i < missedGuess.length; i++) {
      System.out.print(missedGuess[i]);
    }
  }

  public static boolean checkGuess(String word, String guess) {
    for (int i = 0; i < word.length(); i++) {
      if (word.charAt(i) == guess.charAt(0)) {
        return true;
      }
    }
    return false;
  }

  public static void updatePlaceholder(String word, char[] placeholder, String guess) {
    for (int i = 0; i < placeholder.length; i++) {
      if (word.charAt(i) == guess.charAt(0)) {
        placeholder[i] = guess.charAt(0);
      }
    }
  }
}
