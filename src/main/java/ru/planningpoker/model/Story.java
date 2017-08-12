package ru.planningpoker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Icebear on 06.08.2017.
 */
@Entity
@Table(name = "stories")
public class Story extends BaseEntity{

    @Column(name = "summary", nullable = false)
    @NotBlank
    private String summary;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "link", nullable = false)
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "set_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private StorySet storySet;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public StorySet getStorySet() {
        return storySet;
    }

    public void setStorySet(StorySet storySet) {
        this.storySet = storySet;
    }

    public Story() {
        super();
    }

    public Story(Integer id, String summary, String description, String link) {
        super(id);
        this.summary = summary;
        this.description = description;
        this.link = link;
    }
}
