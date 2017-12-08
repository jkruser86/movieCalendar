package org.themoviedb.moviesearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The production companies item from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class ProductionCompaniesItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

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
	 * Sets the local id field
	 *
	 * @param id the value to set the local id field
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Gets the local id field
	 *
	 * @return the local id field
	 */
	public int getId(){
		return id;
	}

	/**
	 * Creates the toString for ProductionCompaniesItem class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"ProductionCompaniesItem{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}