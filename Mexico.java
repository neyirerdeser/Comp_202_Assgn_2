public class Mexico { 
  // problem : line 74
  // NEYIR ERDESER 260736004
  
  // method to give a random integer between 1-6 (included) or other words, roll a die
  public static int diceRoll() { // for one dice
    return (int)(Math.random()*6+1);
  }
  // method to take two rolled dice and merged them into a two digit score
  // if statement so that the dice with greater number will come first
  public static int getScore(int a, int b) {
    if(a>=b)
      return a*10+b;
    else
      return b*10+a;
  }
  // merges the first two methods to simulate one round of the game for ONE player
  // returns the score of that round
  public static int playOneRound(String player) {
    // variables to avoid calling the same method unneccessary amount of times and changing values uncontrollably
    int dice1 = diceRoll();
    int dice2 = diceRoll();
    int score = getScore(dice1, dice2);
    System.out.println(player+" rolled: "+dice1+" "+dice2);
    System.out.println(player+"'s score is: "+score);
    return score;
  }
  // plays one round of the game for TWO players, compares scores and determines the winner
  public static String getWinner(int sc1, int sc2) {
    // this will be the String that gets returned. will be initilized later via conditions
    String winner;
    // variables for the players to avoid repetation and for a more generalized code.
    String pl1 = "Giulia";
    String pl2 = "David";
    // determination of the winner by comparing scores
    // first case: no winner
    if(sc1==sc2) {
      winner = "It's a tie. Roll again!";
      System.out.println(winner);
    }
    // second case: there is a winner to be determined.
    else {
      // first rank - getting a 21
      if (sc1 == 21)
        winner = pl1;
      else if (sc2 == 21)
        winner = pl2;
      // second rank - geting a double
      else if (sc1%11==0) {
        if (sc2%11!=0||sc1>sc2)
          winner = pl1;
        else
          winner = pl2;
      }
      else if (sc2%11 == 0) {
        if (sc1%11!=0||sc2>sc1)
          winner = pl2;
        else
          winner = pl1;
      }
      // third rank - value comparision
      else if (sc1>sc2)
        winner = pl1;
      else
        winner = pl2;
      System.out.println(winner+" wins this round");
    }
    // the winner is determined
    return winner;
  }
  // decides if the game is playable logic-wise (if you have less money than the base bet there's no reason starting to play)
  // game can only be played if the buy-in is positive and no less than base bet
  public static boolean canPlay(double buyIn, double baseBet) {
    if (buyIn>0 && buyIn >= baseBet) // (&&buyIn%baseBet==0) should have added this also.
      return true;
    else
      return false;
  }
  // makes canPlay method to determine if the game is playable and then plays it until theres an absolute winner
  // meaning, until one of the players lose all their money.
  public static void playMexico(double buyIn, double baseBet) {
    // both players start with the same amount of money
    double pl1mon = buyIn;
    double pl2mon = buyIn;
    // checking if the game is fine to play with the condition of while loop
    //  - more explanation after the loop    
    // round counter for the title for each round
    int round = 1;
    // the game will be played as long as the same playing conditions in canPlay is there
    // so the game wont run at all if its not
    while(canPlay(buyIn, baseBet))
    {
      // round title
      System.out.println("\nRound "+round+"\n");
      // determines the winner while also displaying the round stats
      String winner = getWinner(playOneRound("Giulia"),playOneRound("David"));
      // round counter updates after each round for the next title - if it ever gets printed
      round++;
      // players lose the base bet amount after each round if they arent the winner
      if (winner.equals("Giulia"))
        pl2mon -= baseBet;
      else if (winner.equals("David"))
        pl1mon -= baseBet;
      // if anyone loses all their money the game ends and the player with money left wins the game
      if (pl2mon == 0) {
        System.out.println("\nGulia won the game!");
        return;
      } else if (pl1mon == 0) {
        System.out.println("\nDavid won the game!");
        return;
      }
    }
    System.out.println("Insufficient funds. The game cannot be played.");
    /* this statement will only be reached if the loop never iterates - if the condition of the loop is false
     * because the condition is not changing during the game
     * as soon as the game is started the condition is either true or false , it will never change again during the game
     * if its true the it will enter the loop and only exit with one if the return statements
     * which will stop the method before being able to reach this line
     * if the condition is false though the loop will never be entered
     * this print line will be executed and the method will end.
     */
  }
  // just to have the code running on the console.
  // run Mexico <buyIn> <baseBet>
  public static void main(String args[]) {
    playMexico(Double.parseDouble(args[0]),Double.parseDouble(args[1]));
  }
}