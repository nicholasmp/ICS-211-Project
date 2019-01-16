import java.awt.*;
import javax.swing.*;
import java.awt.event.*; //add this for the listener
import java.util.*;
import java.text.*;

/*
*PokePanel for PokeGUI
*@author Nicholas Miaymoto-Pennywell w/ Brian H. & Audrey R
*@since December 9, 2017
*/

public class PokePanel extends JPanel{

   private int i = 0;
   
   //****LABELS******
   private JLabel lBottomMsg = new JLabel("   ");
   //****BUTTONS*****
   private JButton bHunt = new JButton ("Encounter Pokemon");
   private JButton bCatch = new JButton ("Catch Pokemon");
   private JButton bBP = new JButton ("View Backpack");
   private JButton bPokedex = new JButton("View Pokedex");
   private Choice chList = new Choice();
   //****Sup-Panels**
   private JPanel top = new JPanel();
   private JPanel center = new JPanel();
   private JPanel bottom = new JPanel();
   //****TextArea**
   private JTextArea centerMsg = new JTextArea (10, 10);
   private JTextArea bottomMsg = new JTextArea (20, 20);
   private JScrollPane bottomScroller = new JScrollPane(bottomMsg);
   //**GUI Action Listener
   private GUIListener listener = new GUIListener( );
   
   //Pokemon and Arrays
   private Pokemon pk;
   private ArrayDeque<Pokemon> stack = new ArrayDeque<>();
   private PriorityQueue<Pokemon> pq = new PriorityQueue<Pokemon>();
   private ArrayList<Pokemon> pkArr = new ArrayList<>();
   private PokeTree <Pokemon> caughtPoke = new PokeTree<Pokemon>();
   private int count = 0;
      
   /*
   *Constructor
   */
   public PokePanel(){
      setLayout(new BorderLayout());
      setPreferredSize(new Dimension(400, 800));
      top.setBackground(Color.red);
      top.setPreferredSize(new Dimension (400,200));
      center.setBackground(Color.white);
      center.setPreferredSize(new Dimension (400, 150));
      bottom.setBackground(Color.blue);
      bottom.setPreferredSize(new Dimension (400,450));
      
      //Adding to top
      top.add(bHunt);
      bHunt.addActionListener(listener);
      top.add(bCatch);
      bCatch.addActionListener(listener);
      add("North", top);
      
      center.add(centerMsg);
      add("Center", center);
      
      //adding to bottom
      bottom.add(chList);
      chList.add("Number");
      chList.add("Recent");
      bottom.add(bPokedex);
      bPokedex.addActionListener(listener); 
      bottom.add(bBP);
      bBP.addActionListener(listener);  
      bottom.add(bottomScroller);
      add("South", bottom);
      
   }//Constructor
   
   //Private ActionListener Class
   
   private class GUIListener implements ActionListener{
   
   Random ran = new Random();
   Pokemon poke;
   boolean seen = false;
   
      /*
      * ActionPreformed Method
      * @Param event
      */
      public void actionPerformed(ActionEvent event){
         if (event.getSource() == bHunt){
            try {
               poke = MakePokemon.makePokemon();
               centerMsg.setText(poke.getSpecies() + " appeared!");
               seen = true;
            } catch (NullPointerException npe) { 
               centerMsg.setText("Try catch it again!");
            } 
         } //bHunt
         if (event.getSource() == bCatch) { 
            boolean ranCaught = ran.nextBoolean();
            if (seen) {  
               if (ranCaught) { //Scenario caught
                  try {
                     centerMsg.setText(poke.getSpecies() + " was caught!");
                     caughtPoke.add(poke);
                     pq.add(poke);
                     stack.push(poke);
                     seen = false;
                  } catch (NullPointerException npe){ 
                     centerMsg.setText("Something went wrong with the Pokemon.");
                  } 
               } else { //Scenario not caught
                  boolean ranEscape = ran.nextBoolean();
                  if (ranEscape) { //not caught and escaped
                     centerMsg.setText("PokeBall broke...try again?");
                  } else { //not caught but not escaped
                     centerMsg.setText(poke.getSpecies() + " escaped!");
                     seen = false;
                  } 
               } 
            } else {
               centerMsg.setText("There is no pokemon to catch, try encounter one.");
            }   
         } 
         if (event.getSource() == bBP) { 
            String printPoke = "";
            int s = chList.getSelectedIndex();
            switch (s) { 
               case 0:
                  while(pq.size() > 0) {
                     printPoke += pq.poll();
                  }
                  
                  break;
               case 1:
                  while(stack.size() > 0) {
                     printPoke += stack.pop();
                  }
                  
                  break;
            }
            bottomMsg.setText(printPoke);
         }
         if (event.getSource() == bPokedex) { 
            String print = caughtPoke.printPokeTree();
            bottomMsg.setText(print);
         }  
      }
   }//GUIListener
}
