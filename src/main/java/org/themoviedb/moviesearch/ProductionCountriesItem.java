package org.themoviedb.moviesearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The production countries item from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class ProductionCountriesItem{

	@JsonProperty("iso_3166_1")
	private String iso31661;

	@JsonProperty("name")
	private String name;

	/**
	 * Sets the local iso31661 field
	 *
	 * @param iso31661 the value to set the local iso31661 field
	 */
	public void setIso31661(String iso31661){
		this.iso31661 = iso31661;
	}

	/**
	 * Gets the local iso31661 field
	 *
	 * @return the local iso31661 field
	 */
	public String getIso31661(){
		return iso31661;
	}

	/**
	 * Sets the local name field
	 *
	 * @param name the value to set the local name field
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the local name field
	 *
	 * @return the local name field
	 */
	public String getName(){
		return name;
	}

	/**
	 * Creates the toString for ProductionCountriesItem class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"ProductionCountriesItem{" + 
			"iso_3166_1 = '" + iso31661 + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}