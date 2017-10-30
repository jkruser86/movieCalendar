package org.themoviedb.moviesearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

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

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setIso6391(String iso6391){
		this.iso6391 = iso6391;
	}

	public String getIso6391(){
		return iso6391;
	}

	public void setCertification(String certification){
		this.certification = certification;
	}

	public String getCertification(){
		return certification;
	}

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