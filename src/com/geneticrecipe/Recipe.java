package com.geneticrecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Recipe implements Comparable<Recipe> {

	private int completenessScore;
	private GenRecipe genRecipe;
	private HashMap<String, Float> ingredientAmounts;
	private NutrientProfile nutrientTotals;

	public Recipe(GenRecipe genRecipe, HashMap<String, Float> ingredientAmounts) {
		this.genRecipe = genRecipe;

		if (ingredientAmounts != null) {
			this.ingredientAmounts = ingredientAmounts;
		} else {

			this.ingredientAmounts = new HashMap<String, Float>();

			// Initialize the recipe with random amounts of each ingredient.
			for (Ingredient ingredient : this.genRecipe.getIngredients()){
				this.ingredientAmounts.put(ingredient.getName(), (float) (Math.random() * ingredient.getMaxAmount()));
			}
		}

		this.calculateCompletenes();
	}



	/**
	 * This function 'mates' two recipes, producing a 'child' recipe.
	 * The child recipe contains most of it's parents chromosomes, with a few of them mutated slightly.
	 */
	protected Recipe createChildWith(Recipe mate) {

		// Pick random ingredient amounts from each parent.
		HashMap<String, Float> childIngredientAmounts = new HashMap<String, Float>();	    
		for (Ingredient ingredient : this.genRecipe.getIngredients()) {
			String name = ingredient.getName();
			Recipe randomParent = Math.random() > 0.5 ? this : mate;
			childIngredientAmounts.put(name, randomParent.getIngredientAmounts().get(name));
		} 

		// Pick some random ingredient in the recipe to mutate.
		// A mutation is defined as increasing or decreasing the amount of an ingredient by the mutationMultiplier.
		while (Math.random() < GenRecipe.getMutationprobability()){
			int IndexIngredientToMutate = (int) Math.floor(Math.random() * this.genRecipe.ingredients.size());
			Ingredient ingredientToMutate = this.genRecipe.ingredients.get(IndexIngredientToMutate);
			float mutationMultiplier = Math.random() > 0.5 ? (1 - GenRecipe.getMutationmultiplier()) : (1 + GenRecipe.getMutationmultiplier());
			childIngredientAmounts.put(ingredientToMutate.getName(), childIngredientAmounts.get(ingredientToMutate.getName()) * mutationMultiplier);
		}

		return new Recipe(this.genRecipe, childIngredientAmounts);
	};



	@Override
	public int compareTo(Recipe recipe) {
		if (this.completenessScore > recipe.completenessScore) {
			return -1;
		}
		else if (this.completenessScore < recipe.completenessScore) {
			return 1;
		}
		else {
			return 0;
		}
	}




	/**
	 * Sets the current nutrient totals for the recipe.
	 * @todo : revoir la class Nutrientprofile, cette fonction est dégueulasse !
	 */
	 public void calculateTotalNutrients() {

		 this.nutrientTotals = new NutrientProfile(null);
		 
		 for(Ingredient ingredient : this.genRecipe.getIngredients()) {
			 for(String nutrient : NutrientProfile.nutrientNames) {
				 Float lastNutrientAmountValue = this.getNutrientTotals().getNutrientAmounts().get(nutrient);
				 Float currentNutrientValueForTheCurrentIngredient = ingredient.getNutrientProfile().getNutrientAmounts().get(nutrient);
				 Float newNutientAmountValue = lastNutrientAmountValue + (currentNutrientValueForTheCurrentIngredient * this.ingredientAmounts.get(ingredient.getName())); 
				 this.getNutrientTotals().getNutrientAmounts().put(nutrient, newNutientAmountValue);
			 }
			 
		 } 
	 }



	 /**
	 * @return the completenessScore
	 */
	public int getCompletenessScore() {
		return completenessScore;
	}




	/**
	  * Compute the recipes score. The closer the number is to 0, the better.
	  */
	 public void calculateCompletenes() {

		 //var nutrients = _.keys(this.soylent.targetNutrients);
		 this.calculateTotalNutrients();

		 int  nutrientCompleteness = 0;
		 for(String nutrient : NutrientProfile.nutrientNames) {
			 float completeness = this.getNutrientTotals().getNutrientAmounts().get(nutrient)  / this.genRecipe.getTargetProfile().getNutrientAmounts().get(nutrient) * 100;
			 if (completeness > 100) {
				 completeness = completeness - 100;
			 }
			 else {
				 completeness = 100 - completeness;
			 }
			 nutrientCompleteness += completeness;
		 }
		 
		 this.completenessScore = -nutrientCompleteness;
	 }



	/**
	 * @return the nutrientTotals
	 */
	public NutrientProfile getNutrientTotals() {
		return nutrientTotals;
	}



	/**
	 * @return the ingredientAmounts
	 */
	public HashMap<String, Float> getIngredientAmounts() {
		return ingredientAmounts;
	}



};