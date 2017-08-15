package ru.planningpoker.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Icebear on 12.08.2017.
 */
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
    @NotNull
    private Story story;

    @Column(name = "vote")
    @NotNull
    private String vote;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story storyr) {
        this.story = storyr;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Vote() {
        super();
    }

    public Vote(Integer id, User user, Story story, String vote) {
        super(id);
        this.user = user;
        this.story = story;
        this.vote = vote;
    }
}
