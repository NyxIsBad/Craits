/*
Menu 
a.) How many players we are going to have
b.) Reset the game
c.) 


-> At game start, draw 8 cards to all players
-> Card from top of deck goes to pile, action happens


Function: Gameplay
1: return it, redo the turn
2: Start count, flag break from gameplay
3: n/a, break
4: skip next player, break
5: draw 1 card to all other players, break
6: second turn, break
7: person 2 players over draws card, break
8: wild card, change suit, break (has dialog)
9: wild card, change suit of same color, break (has dialog)
10: reverse play, break
11, 12, 13: n/a, break

Function: Skip()
skip 1 player

Function: Skip(int i)
skip i players

Function: evaluateCard(suit, number)
If card is same suit as current suit

elseif card is same number as current card
    return 1

else
    return 0
    
Function: turn
Variables: suit, number
gameloop:
while(1)
    Ask player to play card
    Get card and send to evaluate card with suit and number
    If (evaluateCard)
        gameplay
        break gameloop
    else
        break
*/

import javax.swing.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;

class Turn {
  public static void main(String[] args) {
  
  }
}