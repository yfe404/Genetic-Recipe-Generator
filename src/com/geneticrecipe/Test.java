package com.geneticrecipe;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {

	public static void main(String[] args) {

		System.out.println("\t\nTEST PROGRAM GENETIC-RECIPE\n");
		
		
		
		HashMap<String, Float> nutrientAmounts = new HashMap<String, Float>(); 
		nutrientAmounts.put("calories", 2000f);
		nutrientAmounts.put("carbs", 100f);
		nutrientAmounts.put("proteins", 235f);
		nutrientAmounts.put("fats", 65f);
		
		NutrientProfile targetProfile = new NutrientProfile(nutrientAmounts);
		
		System.out.println("Your target profile : " + targetProfile);
		
		nutrientAmounts.put("calories", 281f);
		nutrientAmounts.put("carbs", 73f);
		nutrientAmounts.put("proteins", 0f);
		nutrientAmounts.put("fats", 0f);
		Ingredient brownSugar = new Ingredient("Brown Sugar", new NutrientProfile(nutrientAmounts), 8, Ingredient.Unit.g);
		nutrientAmounts.put("calories", 86f);
		nutrientAmounts.put("carbs", 11f);
		nutrientAmounts.put("proteins", 3f);
		nutrientAmounts.put("fats", 3f);
		Ingredient bakingCocoa = new Ingredient("Baking Cocoa", new NutrientProfile(nutrientAmounts), 25, Ingredient.Unit.g);
		nutrientAmounts.put("calories", 486f);
		nutrientAmounts.put("carbs", 79f);
		nutrientAmounts.put("proteins", 20f);
		nutrientAmounts.put("fats", 11f);
		Ingredient oatFlower = new Ingredient("Oat Flower", new NutrientProfile(nutrientAmounts), 6, Ingredient.Unit.g);
		nutrientAmounts.put("calories", 123f);
		nutrientAmounts.put("carbs", 7f);
		nutrientAmounts.put("proteins", 5f);
		nutrientAmounts.put("fats", 9f);
		Ingredient Flaxseed = new Ingredient("Flaxseed", new NutrientProfile(nutrientAmounts), 18, Ingredient.Unit.g);
		nutrientAmounts.put("calories", 750f);
		nutrientAmounts.put("carbs", 0f);
		nutrientAmounts.put("proteins", 0f);
		nutrientAmounts.put("fats", 85f);
		Ingredient oliveOil = new Ingredient("Olive oil", new NutrientProfile(nutrientAmounts), 3, Ingredient.Unit.ml);
		nutrientAmounts.put("calories", 52f);
		nutrientAmounts.put("carbs", 0.7f);
		nutrientAmounts.put("proteins", 11f);
		nutrientAmounts.put("fats", 0.2f);
		Ingredient blancOeuf = new Ingredient("Blanc d'oeuf", new NutrientProfile(nutrientAmounts), 20, Ingredient.Unit.g);
		
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(brownSugar);
		ingredients.add(bakingCocoa);
		ingredients.add(oatFlower);
		ingredients.add(Flaxseed);
		ingredients.add(oliveOil);
		ingredients.add(blancOeuf);

		
		/*
		System.out.println("The following ingredients will be used : ");
		for(Ingredient i : ingredients) {
			System.out.println("* " + i);
		}*/
		
		GenRecipe gen = new GenRecipe(targetProfile, ingredients);

		for(int i = 0; i < 100; ++i){
			gen.nextGeneration();
			System.out.println(gen);
		}
		
	}

}
