import java.util.ArrayList;

class Game{
  //some information stuff
  private String currentSuit;
  private String currentValue;
  private int currentDirection;
  private int currentDraw;
  private int currentSkip;

  private int turn;
  private int gameStatus; //1 or 0, game over or not
  private Card lastCard;

  private Deck currentDeck;
  private Pile currentPile;
  private Hand[] hands; //cant catch these hands
  private Hand user1;
  private Hand user2;
  private Hand user3;
  private Hand user4; 

  public Game(Craits craits){
    //currentSuit = ;
    currentDirection = 1;
    //currentValue = ; figure out first draw later
    currentDraw = 0;
    currentSkip = 0;
    currentDeck = new Deck(); //Figure out implementation later
    currentDeck.shuffle();
    currentPile = new Pile(); //Figure out implementation later
    user1 = new Hand(8);
    user2 = new Hand(8); //figure out implementation later
    user3 = new Hand(8);
    user4 = new Hand(8);
    hands = new Hand[4];
    hands[0] = user1;
    hands[1] = user2;
    hands[2] = user3;
    hands[3] = user4;
    turn = 0;
    gameOver = 0;
    //Craits starts with 8 cards
    for(int i=0; i<hands.length; i++) {
        user1.draw(8);
        user2.draw(8);
        user3.draw(8);
        user4.draw(8);
    }
  }
  //More crap
}