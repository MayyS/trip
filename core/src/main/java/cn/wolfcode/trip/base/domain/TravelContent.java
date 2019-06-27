package cn.wolfcode.trip.base.domain;

/**
 * @Auther:S
 * @Date:19/6/26
 */
public class TravelContent {
    private Long id;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
