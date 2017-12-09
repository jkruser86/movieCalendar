package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

/**
 * The movie data results from the movie database api
 *
 * @author Jamie Kruser
 */
@Generated("com.robohorse.robopojogenerator")
public class MovieDataResults{

	@JsonProperty("original_language")
	private String originalLanguage;

	@JsonProperty("imdb_id")
	private String imdbId;

	@JsonProperty("video")
	private boolean video;

	@JsonProperty("title")
	private String title;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("revenue")
	private int revenue;

	@JsonProperty("genres")
	private List<GenresItem> genres;

	@JsonProperty("popularity")
	private double popularity;

	@JsonProperty("release_dates")
	private ReleaseDatesResults releaseDatesResults;

	@JsonProperty("production_countries")
	private List<ProductionCountriesItem> productionCountries;

	@JsonProperty("id")
	private int id;

	@JsonProperty("vote_count")
	private int voteCount;

	@JsonProperty("budget")
	private int budget;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("runtime")
	private int runtime;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("spoken_languages")
	private List<SpokenLanguagesItem> spokenLanguages;

	@JsonProperty("production_companies")
	private List<ProductionCompaniesItem> productionCompanies;

	@JsonProperty("release_date")
	private String releaseDate;

	@JsonProperty("vote_average")
	private double voteAverage;

	@JsonProperty("belongs_to_collection")
	private Object belongsToCollection;

	@JsonProperty("tagline")
	private String tagline;

	@JsonProperty("adult")
	private boolean adult;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("status")
	private String status;

	/**
	 * Sets the local originalLanguage field
	 *
	 * @param originalLanguage the value to set the local originalLanguage field to
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
	 * Sets the local imdbId field
	 *
	 * @param imdbId the value to set the local imdbId field to
	 */
	public void setImdbId(String imdbId){
		this.imdbId = imdbId;
	}

	/**
	 * Gets the local imdbId field
	 *
	 * @return the local imdbId field
	 */
	public String getImdbId(){
		return imdbId;
	}

	/**
	 * Sets the local video field
	 *
	 * @param video the value to set the local video field to
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
	 * @param title the value to set the local title field to
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
	 * Sets the local backdropPath field
	 *
	 * @param backdropPath the value to set the local bathdropPath field to
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
	 * Sets the local revenue field
	 *
	 * @param revenue the value to set the local revenue field to
	 */
	public void setRevenue(int revenue){
		this.revenue = revenue;
	}

	/**
	 * Gets the local revenue field
	 *
	 * @return the local revenue field
	 */
	public int getRevenue(){
		return revenue;
	}

	/**
	 * Sets the local genres field
	 *
	 * @param genres the value to set the local genres field to
	 */
	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	/**
	 * Gets the local genres field
	 *
	 * @return the local genres field
	 */
	public List<GenresItem> getGenres(){
		return genres;
	}

	/**
	 * Sets the local popularity field
	 *
	 * @param popularity the value to set the local popularity field to
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
	 * Sets the local releaseDatesResults field
	 *
	 * @param releaseDatesResults the value to set the local releaseDatesResults field to
	 */
	public void setReleaseDatesResults(ReleaseDatesResults releaseDatesResults){
		this.releaseDatesResults = releaseDatesResults;
	}

	/**
	 * Gets the local releaseDatesResults field
	 *
	 * @return the local releaseDatesResults field
	 */
	public ReleaseDatesResults getReleaseDatesResults(){
		return releaseDatesResults;
	}

	/**
	 * Sets the local productionCountries field
	 *
	 * @param productionCountries the value to set the local productionCountries field to
	 */
	public void setProductionCountries(List<ProductionCountriesItem> productionCountries){
		this.productionCountries = productionCountries;
	}

	/**
	 * Gets the local productionCountries field
	 *
	 * @return the local productionCountries field
	 */
	public List<ProductionCountriesItem> getProductionCountries(){
		return productionCountries;
	}

	/**
	 * Sets the local id field
	 *
	 * @param id the value to set the local id field to
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
	 * Sets the local voteCount field
	 *
	 * @param voteCount the value to set the local voteCount field to
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
	 * Sets the local budget field
	 *
	 * @param budget the value to set the local budget field to
	 */
	public void setBudget(int budget){
		this.budget = budget;
	}

	/**
	 * Gets the local budget field
	 *
	 * @return the local budget field
	 */
	public int getBudget(){
		return budget;
	}

	/**
	 * Sets the local overview field
	 *
	 * @param overview the value to set the local overview field to
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
	 * Sets the local originalTitle field
	 *
	 * @param originalTitle the value to set the local originalTitle field to
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
	 * Sets the local runtime field
	 *
	 * @param runtime the value to set the local runtime field to
	 */
	public void setRuntime(int runtime){
		this.runtime = runtime;
	}

	/**
	 * Gets the local runtime field
	 *
	 * @return the local runtime field
	 */
	public int getRuntime(){
		return runtime;
	}

	/**
	 * Sets the local posterPath field
	 *
	 * @param posterPath the value to set the local posterPath field to
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
	 * Sets the local spokenLanguages field
	 *
	 * @param spokenLanguages the value to set the local spokenLanguages field to
	 */
	public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages){
		this.spokenLanguages = spokenLanguages;
	}

	/**
	 * Gets the local spokenLanguages field
	 *
	 * @return the local spokenLanguages field
	 */
	public List<SpokenLanguagesItem> getSpokenLanguages(){
		return spokenLanguages;
	}

	/**
	 * Sets the local productionCompanies field
	 *
	 * @param productionCompanies the value to set the local productionCompanies field to
	 */
	public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies){
		this.productionCompanies = productionCompanies;
	}

	/**
	 * Gets the local productionCompanies field
	 *
	 * @return the local productionCompanies field
	 */
	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	/**
	 * Sets the local releaseDate field
	 *
	 * @param releaseDate the value to set the local releaseDate field to
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
	 * @param voteAverage the value to set the local voteAverage field to
	 */
	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	/**
	 * Gets the local voteAveragefield
	 *
	 * @return the local voteAverage field
	 */
	public double getVoteAverage(){
		return voteAverage;
	}

	/**
	 * Sets the local belongsToCollection field
	 *
	 * @param belongsToCollection the value to set the local belongsToCollection field to
	 */
	public void setBelongsToCollection(Object belongsToCollection){
		this.belongsToCollection = belongsToCollection;
	}

	/**
	 * Gets the local belongsToCollection field
	 *
	 * @return the local belongsToCollection field
	 */
	public Object getBelongsToCollection(){
		return belongsToCollection;
	}

	/**
	 * Sets the local tagline field
	 *
	 * @param tagline the value to set the local tagline field to
	 */
	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	/**
	 * Gets the local tagline field
	 *
	 * @return the local tagline field
	 */
	public String getTagline(){
		return tagline;
	}

	/**
	 * Sets the local adult field
	 *
	 * @param adult the value to set the local adult field to
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
	 * Sets the local homepage field
	 *
	 * @param homepage the value to set the local homepage field to
	 */
	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	/**
	 * Gets the local homepage field
	 *
	 * @return the local homepage field
	 */
	public String getHomepage(){
		return homepage;
	}

	/**
	 * Sets the local status field
	 *
	 * @param status the value to set the local status field to
	 */
	public void setStatus(String status){
		this.status = status;
	}

	/**
	 * Gets the local status field
	 *
	 * @return the local status field
	 */
	public String getStatus(){
		return status;
	}

	/**
	 * Creates the toString for MovieDataResults class
	 *
	 * @return the toString value for the class
	 */
	@Override
 	public String toString(){
		return 
			"MovieDataResults{" + 
			"original_language = '" + originalLanguage + '\'' + 
			",imdb_id = '" + imdbId + '\'' + 
			",video = '" + video + '\'' + 
			",title = '" + title + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",revenue = '" + revenue + '\'' + 
			",genres = '" + genres + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",release_dates = '" + releaseDatesResults + '\'' +
			",production_countries = '" + productionCountries + '\'' + 
			",id = '" + id + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			",budget = '" + budget + '\'' + 
			",overview = '" + overview + '\'' + 
			",original_title = '" + originalTitle + '\'' + 
			",runtime = '" + runtime + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",spoken_languages = '" + spokenLanguages + '\'' + 
			",production_companies = '" + productionCompanies + '\'' + 
			",release_date = '" + releaseDate + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",belongs_to_collection = '" + belongsToCollection + '\'' + 
			",tagline = '" + tagline + '\'' + 
			",adult = '" + adult + '\'' + 
			",homepage = '" + homepage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}