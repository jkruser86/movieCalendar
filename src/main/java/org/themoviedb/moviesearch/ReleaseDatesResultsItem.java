package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ReleaseDatesResultsItem {

	@JsonProperty("release_dates")
	private List<ReleaseDatesItem> releaseDates;

	@JsonProperty("iso_3166_1")
	private String iso31661;

	public void setReleaseDates(List<ReleaseDatesItem> releaseDates){
		this.releaseDates = releaseDates;
	}

	public List<ReleaseDatesItem> getReleaseDates(){
		return releaseDates;
	}

	public void setIso31661(String iso31661){
		this.iso31661 = iso31661;
	}

	public String getIso31661(){
		return iso31661;
	}

	@Override
 	public String toString(){
		return 
			"ReleaseDatesResultsItem{" +
			"release_dates = '" + releaseDates + '\'' + 
			",iso_3166_1 = '" + iso31661 + '\'' + 
			"}";
		}
}