import java.util.Random;
/*
*Squirtle Pokemon Object Class
*Single-Type Water Type implementes one type interface
*Parent class to Wartortle
*Original code by Lisa Miller
* @editor Nicholas Miyamoto-Pennywell
*@since 9/29/17
*
*/
public class Squirtle extends Pokemon implements WaterType{

   static final int BASE_ATTACK_POWER = 94;
   static final int BASE_DEFENSE_POWER = 122;
   static final int BASE_STAMINA_POWER = 88;
   
   //boolean 
   protected boolean fastIsWater = true;
   protected boolean specialIsWater = true;
   
   /*Constructor with no name
   *uses Pokemon superclass Constructor
   *attacks must be set after construction of Pokemon Object
   *because of dependence of type interface
   */
   public Squirtle(){
      super("Squirtle", "Squirtle", 7, WATER_COLOR, 0.5, 9, WATER_TYPE, "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
      //pick Attacks after construct
      chooseFastAttack();
      chooseSpecialAttack();
   }
   //Constructor with name
   public Squirtle(String name){
      super("Squirtle", name, 7, WATER_COLOR, 0.5, 9, WATER_TYPE, "", BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER);
      chooseFastAttack();
      chooseSpecialAttack();
   }
   protected Squirtle(String species,String name, int num, double ht, double wt, int baseAttackPwr, int baseDefensePwr, int baseStaminaPwr){
      super(species, name, num, WATER_COLOR, ht, wt, WATER_TYPE, "", baseAttackPwr, baseDefensePwr, baseStaminaPwr);
      //pick Attacks
      //can happen here because all subclasses are the same type as Squirtle
      chooseFastAttack();
      chooseSpecialAttack();
   }
   
   /*
   * Water type specific fast attack chooser
   * Randomly picks an attack from the water type attack arrays
   */
   protected void chooseFastAttack(){
      //Random to pick fast attack
      Random randGen = new Random();
      int index;
      index = randGen.nextInt(WATER_FAST_ATTACKS.length);
      fastAttack = WATER_FAST_ATTACKS[index];
      fastAttackPower = WATER_FAST_ATK_POWER[index];
       
   }
   
  /*
   * Water-type specific special attack chooser
   * Randomly picks an attack from the water type interface attack arrays
   */
   protected void chooseSpecialAttack(){
      //Random to pick special attack
      Random randGen = new Random();
      int index;
      index = randGen.nextInt(WATER_SPECIAL_ATTACKS.length);
      specialAttack = WATER_SPECIAL_ATTACKS[index];
      specialAttackPower = WATER_SPECIAL_ATK_POWER[index];
   }
   
   /*
   * Game-play fast attack simulation
   * Creates an output String stating attack details
   * checks for weakness/strength adjustment based on 
   * victim Pokemon's type and attack type(only need to check latter for dual-type)
   * Calls beAttacked method on attacked victim.
   * @param Pokemon the Pokemon being attacked
   * @return String explaining attack and effectiveness
   */
   public String performFastAttack(Pokemon victim){
      
      Random rand = new Random();
      double damage = 0.0;
      double modifier = 1.0;
      double damageDivisor = 250.0;
      
      String s = "";
      String vType = victim.getType1();
      
      modifier = (double)(rand.nextInt(16) + 85)/100.0; //random modifier .85 - 1.00
      s = name+ " performed " + fastAttack + " on " + victim.getSpecies();
      //check effectiveness of Water-Type Attack
      if(vType.equals("Fire") || vType.equals("Ground") || vType.equals("Rock")){
         s = s + "\n It was super effective!";
         modifier = modifier * 2.0;          
      }else if(vType.equals("Water") || vType.equals("Grass") || vType.equals("Dragon")){ 
         s = s + "\n It was not very effective.";
         modifier = modifier * 0.5;
      }
      //check for same types for bonus
      if(type1.equals(vType) && type2.equals(victim.getType2())){
         modifier = modifier *  1.5;
      }
      //bulbapedia damage formula:
      damage = (((2*level)+10)/damageDivisor)* attackPower * (specialAttackPower + 2) * modifier;      
      //perform hit on victim pokemon
      victim.beAttacked((int)damage);
      return s;
   }
   
  /*
   * Game-play special attack simulation
   * Creates an output String stating attack details
   * checks for weakness/strength adjustment based on 
   * victim Pokemon's type and attack type(only need to check latter for dual-type)
   * uses Damage formula from here: http://bulbapedia.bulbagarden.net/wiki/Damage
   * Calls beAttacked method on attacked victim.
   * @param Pokemon the Pokemon being attacked
   * @return String explaining attack and effectiveness
   */
   public String performSpecialAttack(Pokemon victim){
      Random rand = new Random();
      double damage = 0.0;
      double modifier = 1.0;
      double damageDivisor = 250.0;
      
      String s = "";
      String vType = victim.getType1();
      
      modifier = (double)(rand.nextInt(16) + 85)/100.0; //random modifier .85 - 1.00
      
      s = name+ " performed " + specialAttack + " on " + victim.getSpecies();
      
      //check effectiveness of attack
      if(vType.equals("Fire") || vType.equals("Ground") || vType.equals("Rock")){
         s = s + "\n It was super effective!";
         modifier = modifier * 2.0;          
      }else if(vType.equals("Water") || vType.equals("Grass") || vType.equals("Dragon")){ 
         s = s + "\n It was not very effective.";
         modifier = modifier * 0.5;
      }
      //check for same types for bonus
      if(type1.equals(vType) && type2.equals(victim.getType2())){
         modifier = modifier *  1.5;
      }
      //bulbapedia damage formula:
      damage = (((2*level)+10)/damageDivisor)* attackPower * (specialAttackPower + 2) * modifier;
     
      //perform hit on victim pokemon
      victim.beAttacked((int)damage);
      return s;
   }
   
   
   /*
   * Reduces Pokemon's HP by damage/defensePower
   * Doesn't allow HP to go less than 0
   * Implementation of "fainting" not done yet
   * @param int hit points to take off HP
   */
   protected void beAttacked(int damage){
      //part of bulbapedia damage formula
      damage = damage/defensePower;
      
      if(hP > damage){
        hP = hP - damage;
      }else{
        hP = 0;//"Pokemon fainted"
      }
   }     

   
}//Class