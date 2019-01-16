import java.util.*;
/*
*Pokedex A.7
*@author Nicholas Miyamoto-Pennywell
8@since 11/24/17
*
*/
public class Pokedex { 
   public static void main(String [] args) { 
      boolean endLoop = false;
      String inString = "";
      String inString1 = "";
      Scanner userIn = new Scanner(System.in);
      Pokemon poke = null;
      PokeNode node = null;
      PokeTree pT = new PokeTree();
      
      /*Menu code Author @author Lisa Miller.
      *menu code editor @editor Nicholas mp 
      *loops until stopping condition is given
      */
      while (!endLoop) { //Menu
         //menu text
         System.out.println("\tCreate your Pokedex!");
         System.out.println("Please enter the number of your choice:");
         System.out.println("1. Catch your Pokemon");
         System.out.println("2. Trade your Pokemon");
         System.out.println("3. Print your Pokedex");
         System.out.println("0. Exit the program");
         System.out.print("What would you like to do? ");
         
         //read in from user as a String -- much less errors can happen!
         inString = userIn.nextLine();
         
         //take off any spaces on the string
         inString = inString.trim();
         try { 
            //just switch on the String no need to convert to int
            switch(inString) { 
               case "0": endLoop = true;
                  System.out.println("Good bye!");
                  break;
               case "1": //Caches Pokemon and Adds it to the NodeTree        
                  //Creates and adds Pokemon to the PokeTree
                  pT.add(MakePokemon.makePokemon());
                  break;
               case "2": //Trades a Pokemon(Removes a pokemon from the NodeTree)
                  try { 
                     //Calls tradePokemon method and removes the pokemon selected.
                     //If pokemon doesn't exist then it throws an exception.
                     poke = MakePokemon.tradePokemon();
                     pT.get(poke);
                     pT.remove(poke);
                  } catch (PokeException pe) { 
                     System.out.println("\nSorry, can't trade Pokemon you haven't caught.\n");
                  } 
                  break;
               case "3": //Prints the Pokedex inventory
                  pT.printPokeTree();
                  break;
               default: //not a valid menu entry
                  System.out.println("\n****Invalid menu choice.****\nPlease enter a 0, 1, or 2\n");
                  break;        
            } 
         } catch (NullPointerException npe) { 
            System.out.println("Wait... You need to Catch your Pokemon First!\n");
         } 
      } //Menu
   } //Menu Main
} //Class
