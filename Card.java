/*
 * Contains information about each card object
*/
import javax.swing.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Card {
  private String suit; /*For all intents and purposes this consists of the red suits diamonds and hearts and the black suits clubs and spades*/
  private String value;
  private int playDirection; //1 or -1 - 0 would be nice to have but inconvenient
  private int drawCount; //Number of cards you're doomed to draw
  private int skipCount; //Number of people that will get skipped muahaha

  //The constructor
  public Card(String suit, String value, int playDirection, int drawCount, int skipCount){
    this.suit = suit;
    this.value = value;
    this.playDirection = playDirection;
    this.drawCount = drawCount;
    this.skipCount = skipCount;
  }

  //The Getters
  public String getSuit(){
    return suit;
  }
  public String getValue(){
    return value;
  }
  public int getDirection(){
    return playDirection;
  }
  public int getDraw(){
    return drawCount;
  }
  public int getSkips(){
    return skipCount;
  }

  //The Setters
  public void setSuit(String suit){
    this.suit = suit;
  }
  public void setValue(String value){
    this.value = value;
  }
  public void setDirection(int playDirection){
    this.playDirection = playDirection;
  }
  public void setDraw(int drawCount){
    this.drawCount = drawCount;
  }
  public void setSkips(int skipCount){
    this.skipCount = skipCount;
  }
}