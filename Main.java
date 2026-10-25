final String WELCOME = "Welcome to dice game 12. You must roll 1-3 dice and try to get the sum of 12 ...";
final String PROMPT = "Enter which dice you want to roll [1,2,3] (exit with q): ";
final String INVALID = "Sorry, that is an invalid entry. Try again. Valid entries are 1, 2, 3, and q";
final String ALREADY = "Sorry, you have already rolled that dice. Try again";
final String WIN = "You won!!";
final String LOSS = "You lost!!";
final String DRAW = "You neither won nor lost the game.";
final String NEXT_ROUND = "Next round!";
final String GAME_OVER = "Game Over!";

Random random = new Random();   // one Random, reused for every roll

int rollDie() {
    int roll = random.nextInt(6) + 1;
    return roll;
}

boolean isWin(int sum) {
    // a win is a sum of exactly 12.
    if (sum == 12) {
        return true;
    }
    return false;
}

boolean isLoss(int sum) {
    // a loss is a sum greater than 12.
    if (sum > 12) {
        return true;
    }
    return false;
}

boolean validEntry(String input) {
    if ("1".equals(input) || "2".equals(input) || "3".equals(input) || "q".equals(input)) {
        return true;
    }
    return false;
}

void printStatus(int diceOne, int diceTwo, int diceThree, int sum, int wins, int losses) {
    IO.println(diceOne + " " + diceTwo + " " + diceThree 
        + " sum: " + sum + " #win: " + wins + " #loss: " + losses);

}

boolean hasPlayedAllDices (int diceOne, int diceTwo, int diceThree) {
    if (diceOne != 0 && diceTwo != 0 && diceThree != 0) {
        return true;
    }

    return false;
}

void main() {
    Scanner scanner = new Scanner(System.in);
    boolean done = false;
    boolean rolledThisTurn = false;
    int diceOne = 0;
    int diceTwo = 0;
    int diceThree = 0;

    int wins = 0;
    int losses = 0;
    int sum = 0;

    IO.println(WELCOME);

    while (!done) {
        IO.print(PROMPT);
        String input = scanner.next();   

        if (!validEntry(input)) {
            IO.println(INVALID);

        } 

        switch (input) {
            case "1" -> {
                if (diceOne != 0) {
                    IO.println(ALREADY);
                } else {
                    diceOne = rollDie();
                    sum = sum + diceOne; 
                    rolledThisTurn = true;
                    
                }
            }
            case "2" -> {
                if (diceTwo != 0) {
                    IO.println(ALREADY);
                } else {
                    diceTwo = rollDie();
                    sum = sum + diceTwo;
                    rolledThisTurn = true;
                }
            }
            case "3" -> {
                if (diceThree != 0) {
                    IO.println(ALREADY);
                } else {
                    diceThree = rollDie();
                    sum = sum + diceThree;
                    rolledThisTurn = true;
                }
            }
            case "q" -> {
                IO.println(GAME_OVER);
                IO.println("#win: " + wins + " #loss: " + losses);
                done = true;
            }
            default -> {
                
            }
        }
        
        if (rolledThisTurn) {
            if (hasPlayedAllDices(diceOne, diceTwo, diceThree)) {
                if (isWin(sum)) {
                    wins++;
                    printStatus(diceOne, diceTwo, diceThree, sum, wins, losses); 
                    IO.println(WIN);
                } else if (isLoss(sum)) {
                    losses++;
                    printStatus(diceOne, diceTwo, diceThree, sum, wins, losses);
                    IO.println(LOSS);
                } else if (sum < 12) {
                    printStatus(diceOne, diceTwo, diceThree, sum, wins, losses);
                    IO.println(DRAW);
                }

                IO.println(NEXT_ROUND);
                diceOne = 0;
                diceTwo = 0;
                diceThree = 0;
                sum = 0;
            } else {
                printStatus(diceOne, diceTwo, diceThree, sum, wins, losses);
            }     
        }

        rolledThisTurn = false;
    }
}