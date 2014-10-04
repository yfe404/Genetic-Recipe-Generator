package com.geneticrecipe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class NutrientProfile {

	static final public String[] nutrientNames = {"calories", "carbs", "proteins", "fats"};
	
	private HashMap<String, Float> nutrientAmounts = new HashMap<String, Float>(); 

	public NutrientProfile(HashMap<String, Float> nutrientAmounts) {

		/**
		 * Fill the map with the nutrient names.
		 */
		for(String s : this.nutrientNames) {
			this.nutrientAmounts.put(s, 0f);
		}
		
		if(nutrientAmounts == null) return;

		/**
		 * Set the nutrient amounts if the nutrient name exists
		 */
		for (Entry<String, Float> entry : nutrientAmounts.entrySet()) {
			String key = entry.getKey();
			Float value = entry.getValue();

			if(this.nutrientAmounts.containsKey(key)){
				this.nutrientAmounts.put(key, value);
			}

		}

	}


	@Override
	public String toString() {

		String ret = "";

		for (Entry<String, Float> entry : this.nutrientAmounts.entrySet()) {
			String key = entry.getKey();
			Float value = entry.getValue();

			ret += key + ": " + value + "\n";
		}

		return ret;
	}

	
	
	
	/**
	 * @return the nutrientAmounts
	 */
	public HashMap<String, Float> getNutrientAmounts() {
		return nutrientAmounts;
	}


	/**
	 * @param nutrientAmounts the nutrientAmounts to set
	 */
	public void setNutrientAmounts(HashMap<String, Float> nutrientAmounts) {
		this.nutrientAmounts = nutrientAmounts;
	}


	/**
	 * @return the nutrientnames
	 */
	public static String[] getNutrientnames() {
		return nutrientNames;
	}


	
};