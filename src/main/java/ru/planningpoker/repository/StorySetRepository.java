package ru.planningpoker.repository;

import ru.planningpoker.model.StorySet;

import java.util.List;

/**
 * Created by Icebear on 06.08.2017.
 */
public interface StorySetRepository {
    StorySet save(StorySet storySet);

    // false if not found
    boolean delete(int id);

    // null if not found
    StorySet get(int id);

    List<StorySet> getAll();
}
