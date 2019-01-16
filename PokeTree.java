/*
*PokeTree A.7
*@author Nicholas Miyamoto-Pennywell
*@since 11/27.17
*/
public class PokeTree {
   public PokeTree() {
      /*Empty Constructor*/
   }
   /*
   *Root node
   */
   private PokeNode root = null;
   
   public void add(Pokemon p) {
      root = add(root, p);
   }
 /**
 * adds a Pokemon to the tree.
 * 
 * @param node
 *            is the root of the tree/subtree
 * @param Pokemon
 *            is the Pokemon to be added
 */
   private PokeNode add(PokeNode node, Pokemon p) {
         //base case: empty tree or end of a leaf
      if (node == null) {
         return new PokeNode(p, 1, null, null);
         //base case: if node is equal to added pokemon
            //Incerases the Number Caught value
      } else if (node.getKey() == p.getNumber()) {
         node.increaseNumCaught(); 
         return node;
         //base case: if node is less than key
            //Sets node to left child node
      } else if (p.getNumber() < node.getKey()) { 
         node.setLChild(this.add(node.getLChild(), p));
         return node;
         //base case: if node is more than key
            //Sets node to right child node
      } else { 
         node.setRChild(this.add(node.getRChild(), p));
         return node;
      }
   } //PokeNode add
   
   
   public void remove(Pokemon p) {
      root = remove(root, p);
   }
   
   private PokeNode remove(PokeNode node, Pokemon p) {
       // if item not found, throw exception
      if (node == null) {
         throw new PokeException("Pokemon not found!");
      }
      // if search key is less than node's search key,
      // continue to left subtree
      else if (p.getNumber() < node.getKey()) {
         node.setLChild(this.remove(node.getLChild(), p));
         return node;
      }
      // if search key is greater than node's search key,
      // continue to right subtree
      else if (p.getNumber() > node.getKey()) {
         node.setRChild(this.remove(node.getRChild(), p));
         return node;
      }
      // found node containing object with same search key,
      // so delete it
      else {
      // call private method remove
         if (node.getNumCaught() > 1) { 
            node.decreaseNumCaught();
            return node;
         } else {
            node = this.remove(node);
            return node;
         }
      }
   }
   
   private PokeNode remove(PokeNode node) {
      // if node is a leaf,return null
      if (node.getLChild() == null && node.getRChild() == null) {
         return null;
      }
      // if node has a single right child node,
      // then return a reference to the right child node
      else if (node.getLChild() == null) {
         return node.getRChild();
      }
      // if node has a single left child node,
      // then return a reference to the left child node
      else if (node.getRChild() == null) {
         return node.getLChild();
      }
      // if the node has two child nodes
      else {
      // get next Smaller Item, which is Largest Item in Left Subtree
      // The next Smaller Item is stored at the rightmost node in the left
      // subtree.
         PokeNode largestPokeInLeftSubtree = this.getPokeWithLargestKey(node
             .getLChild());
      // replace the node's item with this item
         node = largestPokeInLeftSubtree;
      // delete the rightmost node in the left subtree
         node.setLChild(this.removePokeWithLargestKey(node
             .getLChild()));
         return node;
      }   
   }
   
   /**.
 * Returns the address of the Pokemon with the largest search key in the tree
 * 
 * @param node
 *            is the root of the tree/subtree
 */
   private PokeNode getPokeWithLargestKey(PokeNode node) {
   // if no right child, then this node contains the largest Pokemon
      if (node.getRChild() == null) {
         return node;
      }
      // if not, keep looking on the right
      else {
         return this.getPokeWithLargestKey(node.getRChild());
      }
   }

/**.
 * Removes the node with the largest search key
 * 
 * @param node
 *            is the root of the tree/subtree
 */
   private PokeNode removePokeWithLargestKey(PokeNode node) {
   // if no right child, then this node contains the largest Pokmeon
   // so replace it with its left child
      if (node.getRChild() == null) {
         return node.getLChild();
      }
      // if not, keep looking on the right
      else {
         node.setRChild(this.removePokeWithLargestKey(node
             .getRChild()));
         return node;
      }
   }

   
   public Pokemon get(Pokemon searchKey) {
      return this.get(root, searchKey);
   }
   
 /**.
 * gets a Pokemon from the tree with the same search key
 * 
 * @param node
 *            is the root of the tree/subtree
 * @param searchKey
 *            is a Pokemon storing only the search key
 */
   
   private Pokemon get(PokeNode node, Pokemon searchKey) {
      if (node == null) {
         throw new PokeException("Pokemon not Found!");
      } else {
         if (searchKey.getNumber() == node.getKey()) {
            return searchKey;
         } else if (searchKey.getNumber() < node.getKey()) {
            return this.get(node.getLChild(), searchKey);
         }  else {
            return this.get(node.getRChild(), searchKey);
         }
      }
   } //Pokemon, getKey
   
   public void printPokeTree() {
      this.preorderPokeTree(root);  
   }
   
   public void preorderPokeTree() {
      this.preorderPokeTree(root);
   }
   private void preorderPokeTree(PokeNode root) {
      if (root != null) { 
         System.out.println("  " + root.getPokemon().toString() + "\nCaught: " + root.getNumCaught());
         preorderPokeTree(root.getLChild());
         preorderPokeTree(root.getRChild());
      }
   }
} //Class
