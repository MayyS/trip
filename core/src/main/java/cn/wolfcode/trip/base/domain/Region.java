package cn.wolfcode.trip.base.domain;

public class Region {
    private Long id;

    private String name;

    private Region parent;

    private Integer state=0;

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

    public Region getParent() {
        return parent;
    }

    public void setParent(Region parent) {
        this.parent = parent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", state=" + state +
                '}';
    }
}