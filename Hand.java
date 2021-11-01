import javax.swing.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Hand {
  ArrayList<Card> deck = new ArrayList<Card>();
  //ArrayList<Card> Compdeck = new ArrayList<Card>();
  
  public void clearHand() {
    deck.clear();
  }
  public void addHand(Card card) {
    deck.add(card);
  }
  public ArrayList getHand() {
    return deck;
  }
  public Card getCard(int index) {
    return deck.get(index);
  }
  public void removeCard(int index){
    deck.remove(index);
  }
  public int getSize() {
    return deck.size();
  }
  
}
/*private String playerName;

  //First one shouldn't ever be called but here for redundancy - Le Constructors
  public Hand(){
    playerName="Tester 1";
    int numberOfCards = 54;
    super (numberOfCards);
    this.playerName=playerName;
  }
  public Hand(String player, int numberOfCards){
    super(numberOfCards);
    this.playerName=player;
  }

  public void setPlayerName(String name){
    this.playerName = name;
  }

  public String getName(){
    return playerName;
  }

  //Logic begins
  //The idea is to check for cards that *can* be played in the first place here. In craits, you can only play a card if it is the same number, same suit, or a card that can be played at all times

  public int legalMove(Game game, Card card){
    if (game.getCurrentValue().equals("5") && game.getCurrentDraw()>0) {//check to see if a regular draw card exists outside of count
      return 0;
    } else{ return 1; }
    if (game.getCurrentValue().equals("7") && game.getCurrentDraw()>0) {//long range draw snipe card
      return 0;
    } else{ return 1; }
    if (game.getCurrentValue().equals("4")) {//player got skipped
      return 0;
    } else{ return 1; }
    if (game.getCurrentValue().equals("2") || game.getCurrentValue().equals("1")) {
      return 3;
    } else{ return 1; }
  }

  public int isAction (Card card) {
    int state = 0;
    switch (card.getValue()){
      case "3":
        state=1;
        break;
      case "11":
        state=1;
        break;
      case "12":
        state=1;
        break;
      case "13":
        state=1;
        break;
      default: 
        state=0;
        break;
    }
    return state;
  }
  */