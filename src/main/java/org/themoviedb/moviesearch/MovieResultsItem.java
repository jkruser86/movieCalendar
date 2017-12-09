package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The movie results item from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class MovieResultsItem {

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("video")
	private boolean video;

	@JsonProperty("title")
	private String title;

	@JsonProperty("genre_ids")
	private List<Integer> genreIds;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("release_date")
	private String releaseDate;

	@JsonProperty("vote_average")
	private double voteAverage;

	@JsonProperty("popularity")
	private double popularity;

	@JsonProperty("id")
	private int id;

	@JsonProperty("adult")
	private boolean adult;

	@JsonProperty("vote_count")
	private int voteCount;

	/**
	 * Sets the local overview field
	 *
	 * @param overview the value to set the local overview field
	 */
	public void setOverview(String overview){
		this.overview = overview;
	}

	/**
	 * Gets the local overview field
	 *
	 * @return the local overview field
	 */
	public String getOverview(){
		return overview;
	}

	/**
	 * Sets the local originalLanguage field
	 *
	 * @param originalLanguage the value to set the local originalLanguage field
	 */
	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	/**
	 * Gets the local originalLanguage field
	 *
	 * @return the local originalLanguage field
	 */
	public String getOriginalLanguage(){
		return originalLanguage;
	}

	/**
	 * Sets the local originalTitle field
	 *
	 * @param originalTitle the value to set the local originalTitle field
	 */
	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	/**
	 * Gets the local originalTitle field
	 *
	 * @return the local originalTitle field
	 */
	public String getOriginalTitle(){
		return originalTitle;
	}

	/**
	 * Sets the local video field
	 *
	 * @param video the value to set the local video field
	 */
	public void setVideo(boolean video){
		this.video = video;
	}

	/**
	 * Gets the local video field
	 *
	 * @return the local video field
	 */
	public boolean isVideo(){
		return video;
	}

	/**
	 * Sets the local title field
	 *
	 * @param title the value to set the local title field
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * Gets the local title field
	 *
	 * @return the local title field
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * Sets the local genreIds field
	 *
	 * @param genreIds the value to set the local genreIds field
	 */
	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	/**
	 * Gets the local genreIds field
	 *
	 * @return the local genreIds field
	 */
	public List<Integer> getGenreIds(){
		return genreIds;
	}

	/**
	 * Sets the local posterPath field
	 *
	 * @param posterPath the value to set the local posterPath field
	 */
	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	/**
	 * Gets the local posterPath field
	 *
	 * @return the local posterPath field
	 */
	public String getPosterPath(){
		return posterPath;
	}

	/**
	 * Sets the local backdropPath field
	 *
	 * @param backdropPath the value to set the local backdropPath field
	 */
	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	/**
	 * Gets the local backdropPath field
	 *
	 * @return the local backdropPath field
	 */
	public String getBackdropPath(){
		return backdropPath;
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
	 * Sets the local voteAverage field
	 *
	 * @param voteAverage the value to set the local voteAverage field
	 */
	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	/**
	 * Gets the local voteAverage field
	 *
	 * @return the local voteAverage field
	 */
	public double getVoteAverage(){
		return voteAverage;
	}

	/**
	 * Sets the local popularity field
	 *
	 * @param popularity the value to set the local popularity field
	 */
	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	/**
	 * Gets the local popularity field
	 *
	 * @return the local popularity field
	 */
	public double getPopularity(){
		return popularity;
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
	 * Sets the local adult field
	 *
	 * @param adult the value to set the local adult field
	 */
	public void setAdult(boolean adult){
		this.adult = adult;
	}

	/**
	 * Gets the local adult field
	 *
	 * @return the local adult field
	 */
	public boolean isAdult(){
		return adult;
	}

	/**
	 * Sets the local voteCount field
	 *
	 * @param voteCount the value to set the local voteCount field
	 */
	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	/**
	 * Gets the local voteCount field
	 *
	 * @return the local voteCount field
	 */
	public int getVoteCount(){
		return voteCount;
	}

	/**
	 * Creates the toString for MovieResultsItem class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"MovieResultsItem{" +
			"overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",original_title = '" + originalTitle + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",genre_ids = '" + genreIds + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",id = '" + id + '\'' + 
			",adult = '" + adult + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}