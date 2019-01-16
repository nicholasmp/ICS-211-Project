/*
*Charmeleon Pokemon Object
*Single Type Fire Type Pokemon Extends Charmander Super class
*Parent Class to Charizard
*Original code by Lisa Miller
* @editor Nicholas Miyamoto-Pennywell
*@since 9/30/17
*/
public class Charmeleon extends Charmander{

   static final int BASE_ATTACK_POWER = 158;
   static final int BASE_DEFENSE_POWER = 129;
   static final int BASE_STAMINA_POWER = 116;
   
   /* Constructor with no name */
   public Charmeleon(){
      super("Charmeleon", "Charmeleon", 5, 1.1, 19.0, "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER );   
   }
   /* Constructor with name */
   public Charmeleon(String name){
         super("Charmeleon", name, 5, 1.1, 19.0, "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER );
   }
    /* Constructor with species and name for subclasses */
   protected Charmeleon(String species,String name, int num, double ht, double wt, String type2, int baseAttackPwr, int baseDefensePwr, int baseStaminaPwr){
      super(species, name, num, ht, wt, type2, baseAttackPwr, baseDefensePwr, baseStaminaPwr);
   }
   

}
