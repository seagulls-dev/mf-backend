package com.bezkoder.springjwt.models;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="scenes")
public class SceneModel {
	
	public SceneModel() {
		super();
	}
	
	
	
	
	public SceneModel(String name2, String tags2, long imageId2, String userId2, String imageName2, String overlays2, Timestamp timestamp) {
		this.name=name2;
		this.tags=tags2;
		this.imageId=imageId2;
		this.userId=userId2;
		this.imageName=imageName2;
		this.overlays=overlays2;
		this.creationDate=timestamp;
	}




	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "scene_name")
	private String name;
	@Column(name = "tags")
	private String tags;
	
	@Column(name="user_id")
	private String userId;

	@Column(name = "image_id")
	private long imageId;
	
	@Column(name = "marker_image")
	private byte[] markerImage;
	
	@Column(name = "marker_pattern")
	private byte[] markerPattern;
	
	@Column(name= "image_name")
	private String imageName;
	
	@Column(name="overlays")
	private String overlays;

	@Column(name = "creation_date", nullable = true, length = 19)
	private Timestamp creationDate;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public long getImageId() {
		return imageId;
	}


	public void setImageId(long imageId) {
		this.imageId = imageId;
	}


	public byte[] getMarkerImage() {
		return markerImage;
	}


	public void setMarkerImage(byte[] markerImage) {
		this.markerImage = markerImage;
	}


	public byte[] getMarkerPattern() {
		return markerPattern;
	}


	public void setMarkerPattern(byte[] markerPattern) {
		this.markerPattern = markerPattern;
	}


	public String getImageName() {
		return imageName;
	}


	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getOverlays() {
		return overlays;
	}


	public void setOverlays(String overlays) {
		this.overlays = overlays;
	}


	public Timestamp getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}


	@Override
	public String toString() {
		return "SceneModel [id=" + id + ", name=" + name + ", tags=" + tags + ", userId=" + userId + ", imageId="
				+ imageId + ", markerImage=" + Arrays.toString(markerImage) + ", markerPattern="
				+ Arrays.toString(markerPattern) + ", overlays=" + overlays + ", creationDate=" + creationDate + "]";
	}



	
	
	

}
