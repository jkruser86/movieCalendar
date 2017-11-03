package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ReleaseDatesResults {

	@JsonProperty("results")
	private List<ReleaseDatesResultsItem> results;

	public void setResults(List<ReleaseDatesResultsItem> results){
		this.results = results;
	}

	public List<ReleaseDatesResultsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ReleaseDatesResults{" +
			"results = '" + results + '\'' + 
			"}";
		}
}