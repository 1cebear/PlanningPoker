package ru.planningpoker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Icebear on 06.08.2017.
 */
@Entity
@Table(name = "storyset")
public class StorySet extends BaseEntity{

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "voted", nullable = false)
    @NotNull
    private boolean voted;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "storySet")
    @JsonManagedReference
    private Set<Story> stories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public StorySet() {
        super();
    }

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }

    public StorySet(Integer id, String name, boolean voted) {
        super(id);
        this.name = name;
        this.voted = voted;
    }
}
