package org.themoviedb.moviesearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The spoken languages item from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class SpokenLanguagesItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("iso_639_1")
	private String iso6391;

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
	 * Sets the local iso6391 field
	 *
	 * @param iso6391 the value to set the local iso6391 field
	 */
	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	/**
	 * Gets the local iso6391 field
	 *
	 * @return the local iso6391 field
	 */
	public String getIso6391(){
		return iso6391;
	}

	/**
	 * Creates the toString for SpokenLanguagesItem class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"SpokenLanguagesItem{" + 
			"name = '" + name + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			"}";
		}
}