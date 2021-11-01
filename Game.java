import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Game {
  //Player winner;
  //Player currentPlayer;
  boolean countState;
  int win; // A 0 would symbolize an inconclusive result, a 1 would signify a win, and a -1 will signify a loss.
  Scanner input;
  Pile pile = new Pile();
  int handNumber; // Which card is being chosen
  String currentSuit;
  String placeholderSuit;
  boolean skipStatus=false;
  int placeholderValue;
  Random rand = new Random();
  //Craits craits = new Craits();
  Hand Playerdeck = new Hand();
  Hand Compdeck = new Hand();
  
  boolean playersTurn = true;
  /*------------------------------------------------------------
  Starts the game and controls when it starts or ends
  ------------------------------------------------------------*/
  public void startGame() {
    gameLoop:
    while (true){
      System.out.format("\n\nCraits plays very similarly to Uno, but a bit more simplilfied and crazy.\n\n->Each round, you start off with 8 cards.\n->You can play cards that are the same suit or number as the current surface card\n->The goal is to get rid of all of the cards in your hand\n->1s can only be played in a count ->2 starts a special mode called the Count. In the count, you can only play 1s and 2s. You rack up count tokens (playing 2 adds 2 to the token count, and playing a 1 adds 1). At the end of the count (when one player cannot play any more 1s or 2s) the other player will be forced to draw cards according to the number of tokens accumulated\n->4 skips turn\n->5 makes all other players draw a card [since this is a 1v1 situation, this just makes the COM draw a card]\n->6 gives you a second turn [This is essentially a skip]\n->7 makes the next player draw a card\n->8 is a wild card\n->9 is a \"semiwild\" card, meaning it can only change to suits of the same color\n->10 reverses the direction [but in a 1v1 situation basically just skips]\n->In craits, if you are told to draw a card, you actually do it after your turn is over. As a result, they're really powerful in preventing wins\n->No, you can't stack cards in craits\n\n");
      Playerdeck.clearHand();
      Compdeck.clearHand();
      win = 0;

      pile.makePile();
      currentSuit = pile.getSuit();

      System.out.format("\nWelcome to Craits!\n");
      draw(8, Playerdeck);
      draw(8, Compdeck);
      //debugging
      /*
      Playerdeck.addHand(new Card(2, "Spades"));
      Playerdeck.addHand(new Card(2, "Spades"));
      Playerdeck.addHand(new Card(2, "Spades"));
      Playerdeck.addHand(new Card(1, "Spades"));
      Playerdeck.addHand(new Card(1, "Spades"));
      Playerdeck.addHand(new Card(1, "Spades"));
      Compdeck.addHand(new Card(2, "Spades"));
      Compdeck.addHand(new Card(2, "Spades"));
      Compdeck.addHand(new Card(2, "Spades"));
      Compdeck.addHand(new Card(1, "Spades"));
      Compdeck.addHand(new Card(1, "Spades"));
      Compdeck.addHand(new Card(1, "Spades"));
      Compdeck.addHand(new Card(1, "Spades"));
      pile.addCard(new Card(3, "Spades"));
      currentSuit = "Spades";*/
      

      while(checkGame()) {
        startTurn();
      }
      if(!endGame()){
        break gameLoop;
      }
    }
  }
  /*------------------------------------------------------------
  Ending checks for game, and conditional for gameloop.
  ------------------------------------------------------------*/
  public boolean endGame() {
    System.out.format("Thanks for playing!\n\n");
    if (win == 1) {
      System.out.format("\n\nYou win!!\n");
    } else {
      System.out.format("\n\nYou lose\n");
    }
    System.out.format("\nPlay again [Yes or No]? ");
    input = new Scanner(System.in);

    if (input.next().toLowerCase().contains("n")) {//Checks for a no
      return false;
    } else {
      return true;
    }
  }
  /*------------------------------------------------------------
  Controls the turn itself. Takes input then passes to turn calculator
  ------------------------------------------------------------*/
  public void startTurn() {
    // Turn logic
    handNumber = 0;
    System.out.format("\nThe surface card is: %s\n", pile.getPile().getFace());
    check:
    if (playersTurn) { // If it's the player's turn
      // Displaying user's deck
      // Debugging
      //System.out.println(surfaceCard.getFace());
      //System.out.println(surfaceCard.value);
      if (pile.getPile().value == 4 || pile.getPile().value == 6 || pile.getPile().value == 10) {
        if(skipStatus){
          skipStatus ^= true;
          playersTurn = true;
          System.out.format("The player has been skipped.\n");
          break check;
        }
      }
      System.out.format("It's your turn. Please type the index of the card you want to play: \n");
      for (int i = 0; i < Playerdeck.getSize(); i++) {
        System.out.format("%d. %s\n", i + 1, ((Card) Playerdeck.getCard(i)).getFace());
      }
      System.out.format("%d. Draw a card\n%d. Exit Game\n", Playerdeck.getSize() + 1, Playerdeck.getSize() + 2);
      inputFlag:
      while(true)
      {
        input = new Scanner(System.in);
        System.out.format("Input [The current card is %s | %s]: ", pile.getPile().value, currentSuit);
        if(input.hasNextInt()) {
          handNumber = input.nextInt() - 1;
          //System.out.format("\nDebugging: %d\n", handNumber);
          if (handNumber < 0) {
            System.out.format("The card that you entered is not indexed!\n");
          } else if (handNumber>=Playerdeck.getSize()){
            if (handNumber<=Playerdeck.getSize()+1){
              break inputFlag;
            }
            else {
            System.out.format("The card that you entered is not indexed!\n");
            }
          } else if (!((Card) Playerdeck.getCard(handNumber)).canPlace(pile.getPile(), currentSuit)){
            System.out.format("You can't play that card right now!\n");
          } else {
            break inputFlag;
          }
        } else {
          System.out.format("That wasn't a number!\n");
        }
      } //Checks to make sure input corresponds to a number we want and is actually an option. In addition, has to be playable
      /*------------------------------------------------------------
      Logic
      ------------------------------------------------------------*/
      if (handNumber == Playerdeck.getSize()) //Signifies Draw card option
        draw(1, Playerdeck);
      else if (handNumber == Playerdeck.getSize() + 1) {
        System.exit(0);
      } //Signifies exit option
      else { //If you can play a card
        calcTurn(true);
      }
    } 
    else {//This goes all the way back up
      //Debugging
      //System.out.println(surfaceCard.getFace());
      //System.out.println(surfaceCard.value);
      if (pile.getPile().value == 4 || pile.getPile().value == 6 || pile.getPile().value == 10) {
        if(skipStatus) {
          skipStatus ^= true;
          playersTurn = false; // Computer getting skipped
          System.out.format("The computer has been skipped.\n");
          break check;
        }
      }
      calcTurn(false);
    }
    // Out of cards! Someone has won
    // checking playerdeck comes first, because player goes first. It's thus possible that player won but computer plays anyway, and then hits 0 themselves. So we have to check player first.
    if (Playerdeck.getSize() == 0)
      win = 1;
    else if (Compdeck.getSize() == 0)
      win = -1;
    playersTurn ^= true;
  }
  /*------------------------------------------------------------
  Calculates the turn and executes it
  ------------------------------------------------------------*/
  public void calcTurn(boolean status){
    Count count = new Count();
    //boolean countFlag = false;
    boolean countTurn = status;
    // The count
    if (pile.getPile().value==2) {
      //infinite loop - runs until we break aka when a player runs out of cards
      //Initial starting value
      count.two();
      countLoop:
      while(true){
        ///countFlag = true;
        
        //case player's turn
        //First check if we still have cards to play for this count
        //If we do have the cards for it, then we look for inputs
        if (countTurn) {
          for (handNumber = 0; handNumber < Playerdeck.getSize(); handNumber++) { //Paging through cards
            if (((Card) Playerdeck.getCard(handNumber)).canPlace(pile.getPile(), currentSuit))
              break;
          }
          if (handNumber == Playerdeck.getSize()) {
            System.out.format("You're out of cards for this count!\n");
            //countFlag = false;
            break countLoop;
          }
          //print cards
          System.out.format("\nA COUNT HAS STARTED!!! Please type the index of the card you want to play: \n");
          for (int i = 0; i < Playerdeck.getSize(); i++) {
            System.out.format("%d. %s\n", i + 1, ((Card) Playerdeck.getCard(i)).getFace());
          }
          System.out.format("%d. Exit Game\n", Playerdeck.getSize() + 1);
          //if the countflag is still going on, we ask for cards
          inputFlag2:
          while(true)
          {
            input = new Scanner(System.in);
            System.out.format("Input [Reminder: IN A COUNT, YOU CAN ONLY PLAY 1s and 2s]: ");
            if(input.hasNextInt()) {
              handNumber = input.nextInt() - 1;
              //System.out.format("\nDebugging: %d\n", handNumber);
              if (handNumber < 0) {
                System.out.format("The card that you entered is not indexed!\n");
              } else if (handNumber>=Playerdeck.getSize()){
                if (handNumber<=Playerdeck.getSize()){
                  System.exit(0);
                }
                else {
                System.out.format("The card that you entered is not indexed!\n");
                }
              } else if (!((Card) Playerdeck.getCard(handNumber)).canPlace(pile.getPile(), currentSuit)){
                System.out.format("You can't play that card right now!\n");
              } else {
                break inputFlag2;
              }
            } else {
              System.out.format("That wasn't a number!\n");
            }
            //Add to count
            if(Playerdeck.getCard(handNumber).value == 2) {
              count.two();
            } else if (Playerdeck.getCard(handNumber).value ==1) {
              count.one();
            }
          }
          //After while loops ends, play card and remove it from hand
          pile.addCard((Card) Playerdeck.getCard(handNumber));
          Playerdeck.removeCard(handNumber);
        } else { //Case computer turn
          for (handNumber = 0; handNumber < Compdeck.getSize(); handNumber++) { //Paging through cards
            if (((Card) Compdeck.getCard(handNumber)).canPlace(pile.getPile(), currentSuit))
              break;
          }
          if (handNumber == Compdeck.getSize()) {
            System.out.format("\nI'm out of cards for this count!");
            //countFlag = false;
            break countLoop;
          } else {
            if(Compdeck.getCard(handNumber).value == 2) {
              count.two();
            } else if(Compdeck.getCard(handNumber).value == 1){
              count.one();
            }
            pile.addCard((Card) Compdeck.getCard(handNumber));
            Compdeck.removeCard(handNumber);
            System.out.println("\nI choose " + pile.getPile().getFace() + " ! This adds " + pile.getPile().value + " to the count!");
          }
        }
        countTurn ^= true;
        System.out.format("\nThe current count token is: %d", count.getTokens());
      }
      if(countTurn) {
        System.out.format("\nYou've lost this count! You have to draw %d cards", count.getTokens());
        draw(count.getTokens(), Playerdeck);
      } else {
        System.out.format("\nThe computer has lost this count! It has to draw %d cards", count.getTokens());
        draw(count.getTokens(), Compdeck);
      }
      pile.makePile();
      currentSuit = pile.getSuit();
      System.out.format("\nThe new card is %d | %s\n", pile.getPile().value, currentSuit);
    }

    
    // If status is true, then this calculates a player turn. If false, a computer turn.
    if(status) {
      //Note that in the cases of cards 4 and 6 (and 10, the reverse card), we can actually process in the computer's turn. In terms of a 1v1 game, they all do the same thing: Skip! lol
        
      placeholderSuit = pile.getPile().suit;
      placeholderValue = pile.getPile().value;
      pile.addCard((Card) Playerdeck.getCard(handNumber));          Playerdeck.removeCard(handNumber);
      currentSuit = pile.getPile().suit;
      //System.out.println("The current placeholder is" + placeholderValue);

      switch (placeholderValue)
      {
        case 5: case 7: // Draw a card. Since this is singleplayer, these two actually do the same thing.
        System.out.format("You draw a card!\n");
        draw(1, Playerdeck);
        break;
      }
      switch (pile.getPile().value) {
        case 4: case 6: case 10:
        skipStatus=true;
        break;
        case 8: // Wild/semiwild cards
        do // Goes until user does something right
        {
          System.out.format("\nEnter the suit you want [You can type Spades, Diamonds, Clubs, or Hearts]: ");
          input = new Scanner(System.in);
        } while (!input.hasNext("S.....|s.....|D.......|d.......|C....|c....|H.....|h.....") ); //blank inputs
        if (input.hasNext("S.....|s.....") )
          currentSuit = "Spades";
        else if (input.hasNext("D.......|d.......") )
          currentSuit = "Diamonds";
        else if (input.hasNext("C....|c....") )
          currentSuit = "Clubs";
        else if (input.hasNext("H.....|h.....") )
          currentSuit = "Hearts";
        System.out.format("You chose %s\n", currentSuit);
        break;
        case 9: // Semiwild cards
        //System.out.format("\nDebugging!! %s\n", currentSuit);
        if (placeholderSuit == "Hearts" || placeholderSuit == "Diamonds"){
          do // Goes until user does something right
          {
          System.out.format("\nEnter the suit you want [Diamonds or Hearts]: ", currentSuit);
          input = new Scanner(System.in);
          } while (!input.hasNext("D.......|d.......|H.....|h.....")); //blank inputs
        } else {
          do // Goes until user does something right
          {
            System.out.format("\nEnter the suit you want [Spades or Clubs]: ", currentSuit);
          input = new Scanner(System.in);
          } while (!input.hasNext("S.....|s.....|C....|c....|")); //blank inputs
        }
        if (input.hasNext("S.....|s....."))
          currentSuit = "Spades";
        else if (input.hasNext("D.......|d......."))
          currentSuit = "Diamonds";
        else if (input.hasNext("C....|c...."))
          currentSuit = "Clubs";
        else if (input.hasNext("H.....|h....."))
          currentSuit = "Hearts";
        System.out.format("You chose %s\n", currentSuit);
        break;
        //default:
        //  System.out.format("Something has gone wrong.\n");
        //  break gameLoop;
      }
    } else {
      
      System.out.format("\nComputer turn! The computer has %d card(s).\n", Compdeck.getSize());

      for (handNumber = 0; handNumber < Compdeck.getSize(); handNumber++) { //Paging through cards
        if (((Card) Compdeck.getCard(handNumber)).canPlace(pile.getPile(), currentSuit))
          break;
      }

      if (handNumber == Compdeck.getSize()) {
        System.out.format("There are not any cards left that I can play, so I'll draw one\n");
        draw(1, Compdeck);
      } else { //Turn logic
        //Debugging
        
        placeholderSuit = pile.getPile().suit;
        placeholderValue = pile.getPile().value;
        pile.addCard((Card) Compdeck.getCard(handNumber));
        Compdeck.removeCard(handNumber);
        currentSuit = pile.getPile().suit;
        System.out.println("I choose " + pile.getPile().getFace() + " !");

        //System.out.println("The current placeholder is" + placeholderValue);
        switch (placeholderValue)
        {
          case 5: case 7: // Draw a card. Since this is singleplayer, these two actually do the same thing.
            System.out.format("The computer draws a card!\n");
            draw(1, Compdeck);
            break;
        }
        switch (pile.getPile().value) {
          case 4: case 6: case 10:
          skipStatus=true;
          break;
          case 8: // True wild cards
            do { // The computer is dumb, so it picks a random suit
            currentSuit = new Card().suit; //using new card wild here
            } while (currentSuit == "wild");
            System.out.format("New suit is %s\n", currentSuit);
            break;
          case 9:
            do { // The computer is dumb, so it picks a random suit
            if (currentSuit == "Spades" || currentSuit == "Clubs") {
              if (rand.nextInt(2)==1) {
                currentSuit = "Spades";
              } else {
                currentSuit = "Clubs";
              }
            } else {
              if (rand.nextInt(2)==1) {
                currentSuit = "Hearts";
              } else {
                currentSuit = "Diamonds";
              }
            }
            } while (currentSuit == "semiwild");
            System.out.format("The new suit is %s\n", currentSuit);
            break;
          //default:
          //  System.out.format("Something has gone wrong.\n");
          //  break gameLoop;
        }
      }
      System.out.format("Computer turn ended! The computer now has %d card(s).\n", Compdeck.getSize());
    }
  }
  /*------------------------------------------------------------
  Utility
  ------------------------------------------------------------*/
  public boolean checkGame() {
    if(win == 0) {
      return true;
    } else {
      return false;
    }
  }
  // For drawing cards
  public static void draw(int cards, Hand deck)
  {
    for (int i = 0; i < cards; i++)
      deck.addHand(new Card());
  }
}