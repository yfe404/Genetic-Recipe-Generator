/**
 * 
 */
package com.geneticrecipe;

/**
 * @author yafeunteun
 *
 */

public class Ingredient {
	
	public enum Unit{g, ml, UI};
	
	private String name;
	private NutrientProfile nutrientProfile;
	private float maxAmount;
	private Unit unit;
	
	public Ingredient(String name, NutrientProfile nutrientProfile, float maxAmount, Unit unit){
		this.name = name;
		this.nutrientProfile = nutrientProfile;
		this.maxAmount = maxAmount;
		this.unit = unit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ingredient [name=" + name + ", nutrientProfile="
				+ nutrientProfile + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nutrientProfile
	 */
	public NutrientProfile getNutrientProfile() {
		return nutrientProfile;
	}

	/**
	 * @param nutrientProfile the nutrientProfile to set
	 */
	public void setNutrientProfile(NutrientProfile nutrientProfile) {
		this.nutrientProfile = nutrientProfile;
	}

	/**
	 * @return the maxAmount
	 */
	public float getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @param maxAmount the maxAmount to set
	 */
	public void setMaxAmount(float maxAmount) {
		this.maxAmount = maxAmount;
	}
}
