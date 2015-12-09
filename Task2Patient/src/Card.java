public class Card {

private int NumberCard;

public Card (){
System.out.println(Card.class.getName());
}

public void Lost(){
}

@Override
public String toString(){
return "Card";
}
}