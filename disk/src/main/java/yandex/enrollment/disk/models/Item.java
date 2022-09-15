package yandex.enrollment.disk.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
    @Id
    @Column(name = "id")
    private  String id;

    @Nullable
    @Column(name = "url")
    private String url;

    @Nullable
    @Column(name = "parentid")
    private String parentId;

    @Column(name = "type")
    private ItemType type;

    @Nullable
    @Column(name = "size")
    private Integer size;

    @Column(name = "date")
    private Date date;

    /*
    @Nullable
    @OneToMany
    @Column(name = "children")
    private List<Item> children;*/

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nullable
    public String getUrl() {
        return url;
    }

    public void setParentId(@Nullable String parentId) {
        this.parentId = parentId;
    }

    @Nullable
    public String getParentId() {
        return parentId;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    public void setSize(@Nullable Integer size) {
        this.size = size;
    }

    @Nullable
    public Integer getSize() {
        return size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
