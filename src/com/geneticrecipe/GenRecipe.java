/**
 * 
 */
package com.geneticrecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yafeunteun
 *
 */


public class GenRecipe {

	private NutrientProfile targetProfile;
	protected ArrayList<Ingredient> ingredients;
	private ArrayList<Recipe> recipes;
	private int currentGeneration;
	
	/**
	 * @todo : Give the user the ability to change these values through Mutators
	 */
	private static final int populationSize = 100;
	private static final float mutationProbability = 0.7f;
	private static final float mutationMultiplier = 0.1f;
	private static final float deathRate = 0.3f;
	
	
	/**
	 * GenRecipe should be initialized with a target nutrient profile and a list of ingredients.
	 */
	public GenRecipe(NutrientProfile targetProfile, ArrayList<Ingredient> ingredients) {
		this.targetProfile = targetProfile;
		this.ingredients = ingredients;
		
		this.reset();
	}
	
	
	/**
	 * Randomly generate new recipes. The number of recipes to generate is defined by populationSize
	 */
	private void reset() {
	    this.currentGeneration = 0;
	    this.recipes = new ArrayList<Recipe>();
	    for (int i = 0; i < GenRecipe.populationSize; ++i){
	        this.recipes.add(new Recipe(this, null));
	    }
	}
	
	
	public void nextGeneration() {

	    // Throw out the worst performing recipes. The % thrown out is defined by the deathRate variable.
	    int recipesToKeep = (int) (this.recipes.size() * (1 - GenRecipe.deathRate));
	    System.out.println("Genération " + this.currentGeneration);
	    System.out.println("recipesToKeep " + recipesToKeep);
	    //this.recipes.subList(0, recipesToKeep);
	    	    
	    for(int i = recipesToKeep; i < GenRecipe.populationSize; ++i) {
	    	this.recipes.remove(this.recipes.size() - 1);
	    }
	    		
	    // Pick two random recipes from the remaining list and 'mate' them, to produce a child recipe.
	    for (int i = 0; i < GenRecipe.populationSize - recipesToKeep; ++i) {
	        Recipe parentOne = this.recipes.get((int) Math.floor(Math.random() * recipesToKeep));
	        Recipe parentTwo = this.recipes.get((int) Math.floor(Math.random() * recipesToKeep));
	        Recipe childRecipe = parentOne.createChildWith(parentTwo);

	        this.recipes.add(childRecipe);
	    }

	    this.sortRecipes();
	    this.currentGeneration++;
	}


	/**
	 * Sort the recipes from best to worst
	 */
	public void sortRecipes(){
		Collections.sort(this.recipes);
	}
	

	/**
	 * @return the targetProfile
	 */
	public NutrientProfile getTargetProfile() {
		return targetProfile;
	}

	/**
	 * @param targetProfile the targetProfile to set
	 */
	public void setTargetProfile(NutrientProfile targetProfile) {
		this.targetProfile = targetProfile;
	}

	/**
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	static float getMutationprobability() {
		return mutationProbability;
	}


	public static float getMutationmultiplier() {
		return mutationMultiplier;
	}
	
	public String toString(){
		
		String ret = "";
		
		ret += "total: " + this.recipes.get(0).getNutrientTotals().toString();
       //ret += "amounts: " + this.recipes.get(0).getIngredientAmounts().toString();
        //ret += "ingredients: " + this.ingredients.toString();
        //ret += "targetProfile: " + this.targetProfile.toString();
        ret += "completenessScore : " + this.recipes.get(0).getCompletenessScore();
        ret += "\n";
        
		return ret;
		
	}
};
