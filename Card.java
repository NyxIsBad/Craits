import java.util.Random;

public class Card
{
  public String suit;
  public int value;
  private Random rand;
  private String face;

  public Card(int v, String s)
  {
    value = v;
    suit = s; 
  }

  // Creates a random card
  public Card()
  {
    rand = new Random();
    value = rand.nextInt(13) + 1; 
    // Giving them all suits
    rand = new Random();
    switch(rand.nextInt(4)) {
      case 0: 
        suit = "Spades"; 
        break;
      case 1: 
        suit = "Diamonds"; 
        break;
      case 2: 
        suit = "Clubs"; 
        break;
      case 3: 
        suit = "Hearts"; 
        break;
    }
    // We have some special cards.
    if (value == 8){
      suit = "wild";
    } else if (value == 9) {
      suit = "semiwild"; // Same color wild card
    }
  }

  public String getFace()
  {
    //Gives the card information
    face = "";
    if (suit != "wild" && suit != "semiwild")
    {
      face += this.suit + " ";
    }

    switch(this.value)
    {
      case 1: 
        face += "1 | Count incrementer"; 
        break;
      case 2: 
        face += "2 | Starts Count"; 
        break;
      case 4: 
        face += "4 | Skip"; 
        break;
      case 5: 
        face += "5 | Everyone else draws 1"; 
        break;
      case 6: 
        face += "6 | Play twice"; 
        break;
      case 7: 
        face += "7 | Next player draws card"; // I'm aware that in the game there is a special provisio that if played with >2 players, this actually makes the player +1 over draw, but I'm going to skip this for the sake of simplicity. Read: Jeffery is lazy 
        break;
      case 8: 
        face += "8 | Wild Card";
        break;
      case 9:
        face += "9 | Semiwild Card";
        break;
      case 10:
        face += "10 | Reverse";
        break;
      default: face += String.valueOf(this.value); 
        break;
    }
    //face += "]";
    return face;
  }

  // Checking if the card is usable
  // The suit needs to be specified because if a wild card was chosen in the previous round, the suit would have changed, but the card staying the same
  public boolean canPlace(Card o, String s)
  {
    if (o.value == 1 || o.value == 2){
      if(this.value == 1 || this.value == 2) { //This checks if a count is over. Note that if a count is over, a new card gets drawn from the deck in order to avoid forcing a player into a suit play.
        return true;
      } else {
        return false;
      }
    }
    if (s == this.suit) { //Same suit
      if (this.value == 1) {
        return false;
      }
      else {
        return true;
      }
    } else if (o.value == this.value) { //Same value
      if (this.value == 1) {
        return false;
      }
      else {
        return true;
      }
    } else if (this.suit == "wild") { // Wild cards
      return true;
    } else if (this.suit == "semiwild") {
      return true;
    }
    return false; //default case
  }
}