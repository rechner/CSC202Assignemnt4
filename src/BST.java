import java.io.Serializable;
import java.util.*;

public class BST <T extends Comparable<T>> implements Iterable<T>, BSTInterface<T>, Serializable
{
  
   private Node<T> root;
   private Comparator<T> comparator;
   
   private static final long serialVersionUID = 2353232284622776147L;


   public BST() {
      root = null;
      comparator = null;
   }

   public BST(Comparator<T> comp) {
      root = null;
      comparator = comp;
   }

   private int compare(T x, T y) {
      if(comparator == null) 
    	  return x.compareTo(y);
      else
    	  return comparator.compare(x,y);
   }

   public void insert(T data) {
      root = insert(root, data);
   }
   
   private Node<T> insert(Node<T> p, T node) {
      if (p == null)
         return new Node<T>(node);

      if (compare(node, p.data) == 0)
      	return p;

      if (compare(node, p.data) < 0)
         p.left = insert(p.left, node);
      else
         p.right = insert(p.right, node);

      return p;
   }

   public boolean has(T element) throws NullBinarySearchTreeException {
	   // This shouldn't happen
	   if (root == null) { 
		   throw new NullBinarySearchTreeException("Root element not found"); 
	   }
      return contains(root, element);
   }
   
	 public T retrieve(T element) throws NullBinarySearchTreeException, ElementNotFoundException {
	   // check if root is null
	   if(root == null) { 
		   throw new NullBinarySearchTreeException("Root element not found"); 
	   }
	   return retrieve(root, element);
	 }
	 
	 private T retrieve(Node<T> p, T searchNode) throws ElementNotFoundException {
	    if (p == null)
	    	throw new ElementNotFoundException("Element not found.");
	    else if (compare(searchNode, p.data) == 0)
	    	return p.data;
	    else
	    if (compare(searchNode, p.data) < 0)
	       return retrieve(p.left, searchNode);
	    else
	       return retrieve(p.right, searchNode);
	 }
	
	 private boolean contains(Node<T> p, T toSearch) {
	     if (p == null)
	        return false;
	     else if (compare(toSearch, p.data) == 0)
	     	return true;
	     else if (compare(toSearch, p.data) < 0)
	        return contains(p.left, toSearch);
	     else
	        return contains(p.right, toSearch);
	  }
        

   public void remove(T node) {
      root = delete(root, node);
   }
   
   private Node<T> delete(Node<T> p, T toDelete) {
      if (p == null) 
    	  throw new RuntimeException("unable delete.");
      else
    	  if (compare(toDelete, p.data) < 0)
    		  p.left = delete (p.left, toDelete);
    	  else
    		  if (compare(toDelete, p.data)  > 0)
    			  p.right = delete (p.right, toDelete);
    		  else {
    			  if (p.left == null)
    				  return p.right;
    			  else
    				  if (p.right == null)
    					  return p.left;
    				  else {
    					  // get data from the rightmost node in the left subtree
    					  p.data = retrieveData(p.left);
    					  // delete the rightmost node in the left subtree
    					  p.left =  delete(p.left, p.data) ;
    				  }
    		  }
      	return p;
   }
   
   private T retrieveData(Node<T> p)
   {
      while (p.right != null) p = p.right;

      return p.data;
   }


   public String toString()
   {
      StringBuffer sb = new StringBuffer();
      for(T data : this) sb.append(data.toString());

      return sb.toString();
   }


   public void preOrderTraversal()
   {
      preOrderHelper(root);
   }
   private void preOrderHelper(Node r)
   {
      if (r != null)
      {
         System.out.print(r+" ");
         preOrderHelper(r.left);
         preOrderHelper(r.right);
      }
   }

   public void inOrderTraversal()
   {
      inOrderHelper(root);
   }
   private void inOrderHelper(Node r)
   {
      if (r != null)
      {
         inOrderHelper(r.left);
         System.out.print(r.toString());
         inOrderHelper(r.right);
      }
   }

/*************************************************
 *
 *            CLONE
 *
 **************************************************/

   public BST<T> clone()
   {
      BST<T> twin = null;

      if(comparator == null)
         twin = new BST<T>();
      else
         twin = new BST<T>(comparator);

      twin.root = cloneHelper(root);
      return twin;
   }
   private Node<T> cloneHelper(Node<T> p)
   {
      if(p == null)
         return null;
      else
         return new Node<T>(p.data, cloneHelper(p.left), cloneHelper(p.right));
   }


   public int height() {
      return height(root);
   }
   private int height(Node<T> p) {
      if(p == null) return -1;
      else
      return 1 + Math.max( height(p.left), height(p.right));
   }

   public int countLeaves() {
      return countLeaves(root);
   }
   private int countLeaves(Node<T> p) {
      if(p == null) 
    	  return 0;
      else 
    	  if(p.left == null && p.right == null)
    		  return 1;
    	  else
    		  return countLeaves(p.left) + countLeaves(p.right);
   }



  //This method restores a BST given preorder and inorder traversals
   public void restore(T[] pre, T[] in)  {
      root = restore(pre, 0, pre.length-1, in, 0, in.length-1);
   }
   
   private Node<T> restore(T[] pre, int preL, int preR, T[] in, int inL, int inR) {
      if(preL <= preR)
      {
         int count = 0;
         //find the root in the inorder array
         while(pre[preL] != in[inL + count]) count++;

         Node<T> tmp = new Node<T>(pre[preL]);
         tmp.left = restore(pre, preL+1, preL + count, in, inL, inL +count-1);
         tmp.right = restore(pre, preL+count+1, preR, in, inL+count+1, inR);
         return tmp;
      }
      else
         return null;
   }


   //The width of a binary tree is the maximum number of elements on one level of the tree.
   public int width()
   {
      int max = 0;
      for(int k = 0; k <= height(); k++)
      {
         int tmp = width(root, k);
         if(tmp > max) max = tmp;
      }
      return max;
   }
   //returns the number of node on a given level
   public int width(Node<T> p, int depth)
   {
      if(p==null) return 0;
      else
      if(depth == 0) return 1;
      else
      return width(p.left, depth-1) + width(p.right, depth-1);
   }

   //The diameter of a tree is the number of nodes
   //on the longest path between two leaves in the tree.
   public int diameter()
   {
      return diameter(root);
   }
  
   private int diameter(Node<T> p)
   {
      if(p==null) 
    	  return 0;

      //the path goes through the root
      int len1 = height(p.left) + height(p.right) +3;

      //the path does not pass the root
      int len2 = Math.max(diameter(p.left), diameter(p.right));

      return Math.max(len1, len2);
   }


   public Iterator<T> iterator() {
      return new BSTIterator();
   }

   private class BSTIterator implements Iterator<T> {
      Stack<Node<T>> stack = new Stack<Node<T>>();

      public BSTIterator() {
         if(root != null) stack.push(root);
      }
      public boolean hasNext() {
         return !stack.isEmpty();
      }

      public T next() {
         Node<T> cur = stack.peek();
         if(cur.left != null) {
            stack.push(cur.left);
         } else {
            Node<T> tmp = stack.pop();
            while( tmp.right == null ) {
               if(stack.isEmpty()) return cur.data;
               tmp = stack.pop();
            }
            stack.push(tmp.right);
         }

         return cur.data;
      }

      public void remove() {
    	  throw new java.lang.NullPointerException();
      }
   }
   
   public int size() {
	   int n = 0;
	   for(T node : this) { 
		   n++;
	   }
	   return n;
   }


   @SuppressWarnings("hiding")
   private class Node<T> implements Serializable  {
	  private static final long serialVersionUID = 7526472295622776130L;

      private T data;
      private Node<T> left, right;

      public Node(T data, Node<T> l, Node<T> r) {
         left = l; right = r;
         this.data = data;
      }

      public Node(T data) {
         this(data, null, null);
      }

      public String toString()
      {
         return data.toString();
      }
   } //end of Node
}//end of BST
