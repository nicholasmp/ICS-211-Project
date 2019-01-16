import java.util.Random;
/*
*Charizard Pokemon Object
*Dual type Fire Type ||Flying Type Pokemon Extends Charmeleon Sub class
*Original code by Lisa Miller
* @editor Nicholas Miyamoto-Pennywell
*@since 9/30/17
*/
public class Charizard extends Charmeleon implements FlyingType{

   static final int BASE_ATTACK_POWER = 223;
   static final int BASE_DEFENSE_POWER = 176;
   static final int BASE_STAMINA_POWER = 156;
   
   //booleans for telling which type attack to use for dual type Pokemon
   protected boolean fastIsFire = true;
   protected boolean specialIsFire = true;
   
   /* Constructor with no name */
   public Charizard(){
      super("Charizard", "Charizard", 6, 1.7, 90.5, FLYING_TYPE, BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER );   
         chooseFastAttack();
         chooseSpecialAttack();
   }
   /* Constructor with name */
   public Charizard(String name){
      super("Charizard", name, 6, 1.7, 90.5, FLYING_TYPE, BASE_ATTACK_POWER, BASE_DEFENSE_POWER, BASE_STAMINA_POWER );
         chooseFastAttack();
         chooseSpecialAttack();
   }
   /*
   * Fire|Flying-type specific fast attack chooser
   * Randomly picks whether attack type is grass or poison
   * Randomly picks an attack from type attack arrays
   */
   protected void chooseFastAttack(){
      //randomly choose to get fire or flying attack
      Random randGen = new Random();
      int index;
      fastIsFire = randGen.nextBoolean();
      
      if(fastIsFire){
         index = randGen.nextInt(FIRE_FAST_ATTACKS.length);
         fastAttack = FIRE_FAST_ATTACKS[index];
         fastAttackPower = FIRE_FAST_ATK_POWER[index];
      }else{//is Flying
         index = randGen.nextInt(FLYING_FAST_ATTACKS.length);
         fastAttack =FLYING_FAST_ATTACKS[index]; 
         fastAttackPower = FLYING_FAST_ATK_POWER[index];
         fastIsFire = false;  
      }
   }
   
  /*
   * Fire|Flying-type specific special attack chooser
   * Randomly picks whether attack type is fire or flying
   * Randomly picks an attack from type interface attack arrays
   */
   protected void chooseSpecialAttack(){
      //randomly choose to get fire or flying attack
      Random randGen = new Random();
      int index;
      specialIsFire = randGen.nextBoolean();
      
      if(specialIsFire){
         index = randGen.nextInt(FIRE_SPECIAL_ATTACKS.length);
         specialAttack = FIRE_SPECIAL_ATTACKS[index];
         specialAttackPower = FIRE_SPECIAL_ATK_POWER[index];
      }else{//is flying
         index = randGen.nextInt(FLYING_SPECIAL_ATTACKS.length);
         specialAttack =  FLYING_SPECIAL_ATTACKS[index];
         specialAttackPower = FLYING_SPECIAL_ATK_POWER[index];  
      }
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
      //check effectiveness of attack
      if(fastIsFire){//if attack is fire-type
         if(vType.equals("Grass") || vType.equals("Ice") || vType.equals("Bug") || vType.equals("Steel")){
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;          
         }else if(vType.equals("Fire") || vType.equals("Water") || vType.equals("Rock")||vType.equals("Dragon")){ 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         }
      }else{//is Flying attack
         if(vType.equals("Grass") || vType.equals("Fighting") || vType.equals("Bug")){
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;
            
         }else if(vType.equals("Electric") || vType.equals("Rock") || vType.equals("Steel")){ 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         }
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
      if(specialIsFire){//if attack is fire-type
         if(vType.equals("Grass") || vType.equals("Ice") || vType.equals("Bug") || vType.equals("Steel")){
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;          
         }else if(vType.equals("Fire") || vType.equals("Water") || vType.equals("Rock")||vType.equals("Dragon")){ 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         }
      }else{//is Flying attack
         if(vType.equals("Grass") || vType.equals("Fighting") || vType.equals("Bug")){
            s = s + "\n It was super effective!";
            modifier = modifier * 2.0;
            
         }else if(vType.equals("Electric") || vType.equals("Rock") || vType.equals("Steel")){ 
            s = s + "\n It was not very effective.";
            modifier = modifier * 0.5;
         }
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

}
