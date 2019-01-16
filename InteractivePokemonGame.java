import java.util.*;
/*
*InteractivePokemonGame A.5
*@author Nicholas Miyamoto-Pennywell
*@since 10/28/17
*/

public class InteractivePokemonGame{
   static final int SIZE = 6;
   
   public static void main(String[] args){
      Scanner userIn = new Scanner(System.in);
      String inString = new String("");
      boolean endLoop = false;
      boolean battleWinnerOne = false;
      boolean battleWinnerTwo = false;
      boolean firstTurn = false;
      Pokemon player1 = null;
      Pokemon player2 = null;
      /*Menu code Author @author Lisa Miller.
      *menu code editor @editor Nicholas mp 
      *loops until stopping condition is given
      */
      while(endLoop != true){//Menu
         //menu text
         System.out.println("\tTwo Player Pokemon Battle Areana!");
         System.out.println("Please enter the number of your choice:");
         System.out.println("1. Choose your Pokemon!");
         System.out.println("2. Battle!");
         System.out.println("0. Exit the program");
         System.out.print("What would you like to do? ");
         
         //read in from user as a String -- much less errors can happen!
         inString = userIn.nextLine();
         
         //take off any spaces on the string
         inString = inString.trim();
         try{
            //just switch on the String no need to convert to int
            switch(inString){
               case "0": endLoop = true;
                  System.out.println("Good bye!");
                  break;
               case "1": //Creates Players Pokemon, Phase 1        
                  Scanner userQ = new Scanner(System.in);
                  System.out.println("Player 1 Choose Your Pokemon: \n");
                  player1 = MakePokemon.makePokemon();
                  System.out.println("Player 1 chose:\n" + MakePokemon.printPoke(player1));
                  System.out.println("Player 2 Choose Your Pokemon: \n");
                  player2 = MakePokemon.makePokemon();
                  System.out.println("Player 2 chose:\n" + MakePokemon.printPoke(player2));
                  firstTurn = pokeTurn();
                  
                  break;
               case "2": //Executes Pokemon battle sequence, Phase 2
                  if(firstTurn) {
                     makeBattle(player1, "Player 1", player2, "Player 2");
                  } else {
                     makeBattle(player2, "Player 2", player1, "Player 1");
                  }
                  break;
               default: //not a valid menu entry
                  System.out.println("\n****Invalid menu choice.****\nPlease enter a 0, 1, or 2\n");
                  break;        
            }
         } catch (NullPointerException npe) {
            System.out.println("Wait... You need to choose your Pokemon First!\n");
         }
      }//Menu
   }//Main
   
   /*
   *pokeTurn Method
   *Boolean used to determine turn order for Pokemon Battle
   *@return turn
   */
   public static boolean pokeTurn() {
      Random ran = new Random();
      boolean turn = ran.nextBoolean();
      if(turn) {
         System.out.println("\nFirst Turn goes to Player 1...\n");
      } else {
         System.out.println("\nFirst Turn goes to Player 2...\n");
      }
      return turn;
   }//pokeTurn
    
   /*
   *makeBattle method
   *Main code to control the battle
   *Keeps track of each Pokemon HP and Damage
   */
   public static void makeBattle(Pokemon a, String sA, Pokemon b, String sB){
      Scanner userIn = new Scanner(System.in);
      String fightSeq = new String("");
      int aCounter = 0;
      int bCounter = 0;
      int pokeHP = 0;
      boolean endLoop2 = false;
      
      do {
         endLoop2 = false;
         while(!endLoop2) {
            System.out.println("\n" + sA + "\nWhat will " + a.getName().toUpperCase() + " do?");
            System.out.println(a.getName() + " HP: " + a.getHP());
            System.out.println("\n0: Pass Turn\n1: Perform Fast Attack\n2: Perform Special Attack");
            fightSeq = userIn.nextLine().trim();
            
            switch(fightSeq){
               case "0": ;
                  aCounter++;
                  System.out.println("Turn Ended\t(" + aCounter + " Energy Conserved)");
                  endLoop2 = true;
                  break;
               case "1": //Pokemon Preform Fast Attack   
                  pokeHP = b.getHP();
                  System.out.println(a.performFastAttack(b));
                  System.out.println("\n" + b.getName() + " Took " + (pokeHP - b.getHP()) + " damage.");
                  endLoop2 = true;
                  break;
               case "2": //Pokemon Preform Special Attack
                  if(aCounter < 3) {
                     System.out.println("Not Enough Energy Conserved, needs " + (3 - aCounter) + " more!");
                  } else {
                     pokeHP = b.getHP();
                     System.out.println(a.performSpecialAttack(b));
                     System.out.println("\n" + b.getName() + " Took " + (pokeHP - b.getHP()) + " damage.");
                     aCounter-=3;
                     endLoop2 = true;
                  }
                  break;
               default: //not a valid menu entry
                  System.out.println("\n Please choose an attack. \n");
                  break;        
            }//Pokemon a Menu
         }//While (Pokemon a) loop
         if(b.getHP() <= 0) {
            System.out.println("\n" + b.getName() + " Fainted...");
            System.out.println(sA + " WINS\tCONGRATULATIONS\n\n");
            System.out.println("Would you like a rematch?\n\n");
         }else {
            endLoop2 = false;
            while(!endLoop2) {
               System.out.println("\n" + sB + "\nWhat will " + b.getName().toUpperCase() + " do?");
               System.out.println(b.getName() + " HP: " + b.getHP());
               System.out.println("\n0: Pass Turn\n1: Perform Fast Attack\n2: Perform Special Attack");
               fightSeq = userIn.nextLine().trim();
               
               switch(fightSeq){
                  case "0": 
                     bCounter++;
                     System.out.println("Turn Ended\t(" + bCounter + " Energy Conserved)");
                     endLoop2 = true;
                     break;
                  case "1": //Pokemon Preform Fast Attack    
                     pokeHP = a.getHP();   
                     System.out.println(b.performFastAttack(a));
                     System.out.println("\n" + a.getName() + " Took " + (pokeHP - a.getHP()) + " damage.");
                     endLoop2 = true;
                     break;
                  case "2": //Pokemon Preform Special Attack
                     if(bCounter < 3) {
                        System.out.println("Not Enough Energy Conserved, needs " + (3 - bCounter) + " more!");
                     } else {
                        pokeHP = a.getHP();
                        System.out.println(b.performSpecialAttack(a));
                        System.out.println("\n" + a.getName() + " Took " + (pokeHP - a.getHP()) + " damage.");
                        bCounter-=3;
                        endLoop2 = true;
                     }      
                     break;
                  default: //not a valid menu entry
                     System.out.println("\n Please choose an attack. \n");
                     break;
               }//Pokemon b Menu
            }//While (Pokemon b) loop
         }//If else to catch Pokemon b
         if(a.getHP() <= 0) {
            System.out.println("\n" + a.getName() + " Fainted...");
            System.out.println(sB + " WINS\tCONGRATULATIONS\n\n");
            System.out.println("Would you like a rematch?\n\n");
         }
      } while (a.getHP() > 0 && b.getHP() > 0);//While (.getHP()) loop
   }//MakeBattle method

}//Class
