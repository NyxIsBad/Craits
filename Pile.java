import javax.swing.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Pile {
  //Define variables
  Card trashpile;
  String currentSuit;

  //Randomly make the first card in the game
  public void makePile() {
    do {
      trashpile = new Card();
    } while(trashpile.value != 3 && trashpile.value != 11 && trashpile.value != 12 && trashpile.value != 13);
    currentSuit = trashpile.suit;
  }
  
  //Just getters and setters from there
  public void addCard(Card card) {
    trashpile = card;
  }
  public String getSuit() {
    return currentSuit;
  }
  public Card getPile() {
    return trashpile;
  }
}