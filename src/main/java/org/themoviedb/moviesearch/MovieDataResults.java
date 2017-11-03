package org.themoviedb.moviesearch;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

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

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setImdbId(String imdbId){
		this.imdbId = imdbId;
	}

	public String getImdbId(){
		return imdbId;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setRevenue(int revenue){
		this.revenue = revenue;
	}

	public int getRevenue(){
		return revenue;
	}

	public void setGenres(List<GenresItem> genres){
		this.genres = genres;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setReleaseDatesResults(ReleaseDatesResults releaseDatesResults){
		this.releaseDatesResults = releaseDatesResults;
	}

	public ReleaseDatesResults getReleaseDatesResults(){
		return releaseDatesResults;
	}

	public void setProductionCountries(List<ProductionCountriesItem> productionCountries){
		this.productionCountries = productionCountries;
	}

	public List<ProductionCountriesItem> getProductionCountries(){
		return productionCountries;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public void setBudget(int budget){
		this.budget = budget;
	}

	public int getBudget(){
		return budget;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setRuntime(int runtime){
		this.runtime = runtime;
	}

	public int getRuntime(){
		return runtime;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages){
		this.spokenLanguages = spokenLanguages;
	}

	public List<SpokenLanguagesItem> getSpokenLanguages(){
		return spokenLanguages;
	}

	public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies){
		this.productionCompanies = productionCompanies;
	}

	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setBelongsToCollection(Object belongsToCollection){
		this.belongsToCollection = belongsToCollection;
	}

	public Object getBelongsToCollection(){
		return belongsToCollection;
	}

	public void setTagline(String tagline){
		this.tagline = tagline;
	}

	public String getTagline(){
		return tagline;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setHomepage(String homepage){
		this.homepage = homepage;
	}

	public String getHomepage(){
		return homepage;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

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