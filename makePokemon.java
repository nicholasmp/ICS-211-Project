import java.util.*;
/*
*MakePokemon class
*@author Nicholas mp 
*@since 11/5/17
*/
public class MakePokemon { 
   
   /*makePokemon Method
   *Main calls method
   *Method asks to make pokemon object then retruns it to Main.
   */
   public static Pokemon makePokemon() {
      String i1 = "Catch your Pokemon:";
      String q1 = "What is the Pokemon's species?";
      String c1 = "Choose the Pokemon's Species by entering the number of the Pokemon:\n";
      String pOptions = "Bulbasaur : 1 \nIvysaur : 2\nVenasuar : 3\nCharmander : 4\nCharmeleon : 5\nCharizard : 6\nSquirtle : 7\nWartortle : 8\nBlastoise : 9\n";
      Scanner scan = new Scanner(System.in);
      String userIn = "";
      int userNum = 0;
      String uName = "";
      boolean done = false; 
      boolean bName = false;  
      
      Pokemon p = null;
      //Asking Pokemon Species
      System.out.println(q1);
      System.out.println(pOptions);
      System.out.println(c1);
      //Scanning User response, Checking results.
      
      //While loop until correct set of numbers is chosen.
      while(!done) {
         userIn = scan.nextLine();
         userIn = userIn.trim();
         try {
            userNum = Integer.parseInt(userIn);
            if(userNum > 9) {//Checks for numbers out of bounds (1-9)
              System.out.println("Pokemon selected not in data base. Please try Again... \n");
            } else if (userNum < 0) {
               System.out.println("Please select a Pokemon.");
            } else {
              done = true;
            }
         } catch(NumberFormatException nfe){//EXCEPTIONS, if string is used instead of numbers
            System.out.println("Please try again");
         }
         switch(userNum) {//switch statement for Pokemon Object without Name
            case 1:
               p = new Bulbasaur();
               break;
            case 2:
               p = new Ivysaur();
               break;
            case 3:
               p = new Venusaur();
               break;
            case 4:
               p = new Charmander();
               break;
            case 5:
               p = new Charmeleon();
               break;
            case 6:
               p = new Charizard();
               break;
            case 7:
               p = new Squirtle();
               break;
            case 8:
               p = new Wartortle();
               break;
            case 9:
               p =  new Blastoise ();
               break;
            default:
         }
      }  
      return p;   
   } //makePokemon Method
   
   public static Pokemon tradePokemon() {
      String i1 = "Which Pokemon would you like to Trade?:";
      String q1 = "What is the Pokemon's species?";
      String c1 = "Choose the Pokemon's Species by entering the number of the Pokemon:\n";
      String pOptions = "Bulbasaur : 1 \nIvysaur : 2\nVenasuar : 3\nCharmander : 4\nCharmeleon : 5\nCharizard : 6\nSquirtle : 7\nWartortle : 8\nBlastoise : 9\n";
      Scanner scan = new Scanner(System.in);
      String userIn = "";
      int userNum = 0;
      String uName = "";
      boolean done = false; 
      boolean bName = false;  
      
      Pokemon p = null;
      //Asking Pokemon Species
      System.out.println(q1);
      System.out.println(pOptions);
      System.out.println(c1);
      //Scanning User response, Checking results.
      
      //While loop until correct set of numbers is chosen.
      while(!done) {
         userIn = scan.nextLine();
         userIn = userIn.trim();
         try {
            userNum = Integer.parseInt(userIn);
            if(userNum > 9) {//Checks for numbers out of bounds (1-9)
              System.out.println("Pokemon selected not in data base. Please try Again... \n");
            } else if (userNum < 0) {
               System.out.println("Please select a Pokemon.");
            } else {
              done = true;
            }
         } catch(NumberFormatException nfe){//EXCEPTIONS, if string is used instead of numbers
            System.out.println("Please try again");
         }
         switch(userNum) {//switch statement for Pokemon Object without Name
            case 1:
               p = new Bulbasaur();
               break;
            case 2:
               p = new Ivysaur();
               break;
            case 3:
               p = new Venusaur();
               break;
            case 4:
               p = new Charmander();
               break;
            case 5:
               p = new Charmeleon();
               break;
            case 6:
               p = new Charizard();
               break;
            case 7:
               p = new Squirtle();
               break;
            case 8:
               p = new Wartortle();
               break;
            case 9:
               p =  new Blastoise ();
               break;
            default:
         }
      }   
      return p;  
   } //TradePokemon Method
   
   /*printPoke
   *Prints the objgects stored in the Array List in order
   */
   public static String printPoke(Pokemon p){
         String p1 = "";
         p1 = "Species: " + p.getSpecies();
         p1 = p1 + "\nHP: " + p.getHP();
         p1 = p1 + "\nFast Attack: " + p.getFastAttack();
         p1 = p1 + "\nSpecial Attack: " + p.getSpecialAttack() + "\n";
         
         return p1;
   }//printArray Method

} //Class
