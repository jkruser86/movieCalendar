package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The movie results from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class MovieResults {

	@JsonProperty("page")
	private int page;

	@JsonProperty("total_pages")
	private int totalPages;

	@JsonProperty("results")
	private List<MovieResultsItem> results;

	@JsonProperty("total_results")
	private int totalResults;

	/**
	 * Sets the local page field
	 *
	 * @param page the value to set the local page field
	 */
	public void setPage(int page){
		this.page = page;
	}

	/**
	 * Gets the local page field
	 *
	 * @return the local page field
	 */
	public int getPage(){
		return page;
	}

	/**
	 * Sets the local totalPages field
	 *
	 * @param totalPages the value to set the local totalPages field
	 */
	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	/**
	 * Gets the local totalPages field
	 *
	 * @return the local totalPages field
	 */
	public int getTotalPages(){
		return totalPages;
	}

	/**
	 * Sets the local results field
	 *
	 * @param results the value to set the local results field
	 */
	public void setResults(List<MovieResultsItem> results){
		this.results = results;
	}

	/**
	 * Gets the local results field
	 *
	 * @return the local results field
	 */
	public List<MovieResultsItem> getResults(){
		return results;
	}

	/**
	 * Sets the local totalResults field
	 *
	 * @param totalResults the value to set the local totalResults field
	 */
	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	/**
	 * Gets the local totalResults field
	 *
	 * @return the local totalResults field
	 */
	public int getTotalResults(){
		return totalResults;
	}

	/**
	 * Creates the toString for MovieResults class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"MovieResults{" +
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}