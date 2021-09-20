
/*
Class will make deck + manage a second array of objects known as pile
*/
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.*;

class Deck {
  private ArrayList<Card> cards; //Make an arraylist of Card objects
  private int numberOfCards;

  //The constructor
  public Deck(){
    numberOfCards = 54;
    cards = new ArrayList<Card>(numberOfCards);

    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    //Going through all the suits and making the cards
    for(int i=0; i<4; i++){
      for(int j=0; j<13; j++){
        cards.add( (i,j));
      }
      cards.add(); //Finish later lol im too lazy
    }
  }
}