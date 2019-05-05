package com.androidwind.annotation.annotation;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class TagInfo {
    public enum Type {
        ACTIVITY, FRAGMENT
    }

    private Type type;

    private String location;

    private String detail;

    private String description;

    public TagInfo(Type type, String location, String detail, String description) {
        this.type = type;
        this.location = location;
        this.detail = detail;
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static TagInfo build(Type type, String location, String detail, String description) {
        return new TagInfo(type, location, detail, description);
    }
}
