package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The release dates results from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class ReleaseDatesResults {

	@JsonProperty("results")
	private List<ReleaseDatesResultsItem> results;

	/**
	 * Sets the local results field
	 *
	 * @param results the value to set the local results field
	 */
	public void setResults(List<ReleaseDatesResultsItem> results){
		this.results = results;
	}

	/**
	 * Gets the local results field
	 *
	 * @return the local results field
	 */
	public List<ReleaseDatesResultsItem> getResults(){
		return results;
	}

	/**
	 * Creates the toString for ReleaseDatesResults class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"ReleaseDatesResults{" +
			"results = '" + results + '\'' + 
			"}";
		}
}