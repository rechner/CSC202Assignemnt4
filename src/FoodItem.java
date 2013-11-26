import java.io.Serializable;


public class FoodItem implements Comparable<FoodItem>, Serializable {
	private static final long serialVersionUID = 7526472295622776137L;

	public String foodName;
	public String ingredients;
	
	public FoodItem(String foodName, String ingredients) {
		this.foodName = foodName;
		this.ingredients = ingredients;
	}
	
	public int compareTo(FoodItem food) {
		return this.foodName.compareTo(food.foodName);
	}
	
	public String toString() {
		return foodName +  "\n ingredients → " + ingredients + "\n";
	}

}
