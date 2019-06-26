package cn.wolfcode.trip.base.domain;

import java.util.Date;

public class Travel {
    private Long id;

    private String title;

    private Date travelTime;

    private String perExpends;

    private Integer days;

    private Integer person;

    private Long author_id;

    private Date createTime;

    private Date releaseTime;

    private Boolean isPublic;

    private Long place_id;

    private String coverUrl;

    private Date lastUpdateTime;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }

    public String getPerExpends() {
        return perExpends;
    }

    public void setPerExpends(String perExpends) {
        this.perExpends = perExpends;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Long place_id) {
        this.place_id = place_id;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    private TravelContent travelContent;

    public TravelContent getTravelContent() {
        return travelContent;
    }

    public void setTravelContent(TravelContent travelContent) {
        this.travelContent = travelContent;
    }

    private User author;
    private Region place;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Region getPlace() {
        return place;
    }

    public void setPlace(Region place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", travelTime=" + travelTime +
                ", perExpends='" + perExpends + '\'' +
                ", days=" + days +
                ", person=" + person +
                ", author_id=" + author_id +
                ", createTime=" + createTime +
                ", releaseTime=" + releaseTime +
                ", isPublic=" + isPublic +
                ", place_id=" + place_id +
                ", coverUrl='" + coverUrl + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", state=" + state +
                ", travelContent=" + travelContent +
                ", author=" + author +
                ", place=" + place +
                '}';
    }
}