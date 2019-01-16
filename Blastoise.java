/*
*Blastoise Pokemon Object
*Single type Water Type Pokemon Extends Wartortle Sub class
*Original code by Lisa Miller
* @editor Nicholas Miyamoto-Pennywell
*@since 9/30/17
*/
public class Blastoise extends Wartortle{

   static final int BASE_ATTACK_POWER = 171;
   static final int BASE_DEFENSE_POWER = 210;
   static final int BASE_STAMINA_POWER = 158;
   
   /* Constructor with no name */
   public Blastoise(){
      super("Blastoise", "Blastoise", 9, 1.6, 85.5,BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER );   
   }
   /* Constructor with name */
   public Blastoise(String name){
         super("Blastoise", name, 9, 1.6, 85.5,BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER );
   }
}
