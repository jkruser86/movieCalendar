package org.themoviedb.moviesearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The release dates item from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class ReleaseDatesItem{

	@JsonProperty("note")
	private String note;

	@JsonProperty("release_date")
	private String releaseDate;

	@JsonProperty("type")
	private int type;

	@JsonProperty("iso_639_1")
	private String iso6391;

	@JsonProperty("certification")
	private String certification;

	/**
	 * Sets the local note field
	 *
	 * @param note the value to set the local note field
	 */
	public void setNote(String note){
		this.note = note;
	}

	/**
	 * Gets the local note field
	 *
	 * @return the local note field
	 */
	public String getNote(){
		return note;
	}

	/**
	 * Sets the local releaseDate field
	 *
	 * @param releaseDate the value to set the local releaseDate field
	 */
	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	/**
	 * Gets the local releaseDate field
	 *
	 * @return the local releaseDate field
	 */
	public String getReleaseDate(){
		return releaseDate;
	}

	/**
	 * Sets the local type field
	 *
	 * @param type the value to set the local type field
	 */
	public void setType(int type){
		this.type = type;
	}

	/**
	 * Gets the local type field
	 *
	 * @return the local type field
	 */
	public int getType(){
		return type;
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
	 * Sets the local certification field
	 *
	 * @param certification the value to set the local certification field
	 */
	public void setCertification(String certification){
		this.certification = certification;
	}

	/**
	 * Gets the local certification field
	 *
	 * @return the local certification field
	 */
	public String getCertification(){
		return certification;
	}

	/**
	 * Creates the toString for ReleaseDatesItem class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"ReleaseDatesItem{" + 
			"note = '" + note + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",type = '" + type + '\'' + 
			",iso_639_1 = '" + iso6391 + '\'' + 
			",certification = '" + certification + '\'' + 
			"}";
		}
}