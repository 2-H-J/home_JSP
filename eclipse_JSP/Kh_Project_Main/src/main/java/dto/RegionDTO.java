package dto;

import java.sql.Timestamp;

public class RegionDTO {
	private int regionNumber;
	private String title;
	private String description;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String imageUrl;
	private double latitude;
	private double longitude;

	public RegionDTO() {
	}

	public RegionDTO(int regionNumber, String title, String description, Timestamp createTime, Timestamp updateTime) {
		this.regionNumber = regionNumber;
		this.title = title;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public RegionDTO(String title, String description, String imageUrl, double latitude, double longitude) {
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		// null 값이면 빈 문자열로 설정
		this.imageUrl = (imageUrl == null) ? "" : imageUrl;
	}

	public int getRegionNumber() {
		return regionNumber;
	}

	public void setRegionNumber(int regionNumber) {
		this.regionNumber = regionNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "RegionDTO{" + "regionNumber=" + regionNumber + ", title='" + title + '\'' + ", description='"
				+ description + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
	}
}
