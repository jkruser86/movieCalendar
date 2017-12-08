package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The release dates results item from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class ReleaseDatesResultsItem {

	@JsonProperty("release_dates")
	private List<ReleaseDatesItem> releaseDates;

	@JsonProperty("iso_3166_1")
	private String iso31661;

	/**
	 * Sets the local releaseDates field
	 *
	 * @param releaseDates the value to set the local releaseDates field
	 */
	public void setReleaseDates(List<ReleaseDatesItem> releaseDates){
		this.releaseDates = releaseDates;
	}

	/**
	 * Gets the local releaseDates field
	 *
	 * @return the local releaseDates field
	 */
	public List<ReleaseDatesItem> getReleaseDates(){
		return releaseDates;
	}

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
	 * Creates the toString for ReleaseDatesResultsItem class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"ReleaseDatesResultsItem{" +
			"release_dates = '" + releaseDates + '\'' + 
			",iso_3166_1 = '" + iso31661 + '\'' + 
			"}";
		}
}