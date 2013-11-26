import java.io.FileNotFoundException;
import java.io.IOException;


public class BSTDriverDemo {
	public static void main(String[] args) {
		
		BST<FoodItem> foodBST = new BST<FoodItem>();
		FoodItem grits, strawberryLemonade, omelette, hamSandwich;
		hamSandwich = new FoodItem("ham sandwich", "bread, ham, mayonaise, mustard, lettuce, tomato");
		strawberryLemonade = new FoodItem("strawberry lemonade", "lemons, strawberries, ice, sugar, water");
		omelette = new FoodItem("omelette", "eggs, red pepper, cheddar cheese, mushrooms, butter, onions");
		grits = new FoodItem("grits", "grits, milk, brown sugar, raisins, grapes");
		
		
		// inserting stuff
		System.out.println("Inserting food elements.");
		foodBST.insert(omelette);
		foodBST.insert(hamSandwich); 
		foodBST.insert(grits);
		foodBST.insert(strawberryLemonade);
	    
		// let's take a look inside
		System.out.println("Contents of the binary search tree:");
		foodBST.inOrderTraversal();
		System.out.println();
		
		// remove a thing
		System.out.println("Removing an element from the tree:");
		foodBST.remove(grits);
		System.out.println();

		// printing out a thing
		System.out.println("Contents of the binary search tree:");
		foodBST.inOrderTraversal();
		System.out.println();
						
		// print object of element
		try {
			System.out.println("Printing out a recipe.");
			System.out.println(foodBST.retrieve(strawberryLemonade).ingredients);
			System.out.println();
		} catch (NullBinarySearchTreeException e) {
			e.printStackTrace();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
		
		// print out the food
		System.out.println("Contents of the binary search tree:");
		foodBST.inOrderTraversal();
		System.out.println();
		
		// how big is it?
		System.out.println("The size of the entire BST is: " + foodBST.size());
		System.out.println();

		// serialize in/out from file
		String serializeFileName = "recipies.ser";
		try {
			// serialize out
			System.out.print("Saving the search tree... ");
			ObjectSerializer.save(foodBST, serializeFileName);
			System.out.println(" DONE");
			// serialize in
			System.out.print("Loading the search tree from file...");
			BST<FoodItem> serializedInFoodBST = (BST<FoodItem>) ObjectSerializer.open(serializeFileName);
			System.out.print(" DONE");
			// print out the food
			System.out.println("Printing out the serialized in bst.");
			serializedInFoodBST.inOrderTraversal();
			System.out.println();
			
		} catch (FileNotFoundException e) {
			System.out.println(" FAIL: File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(" FAIL: I/O failure");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
