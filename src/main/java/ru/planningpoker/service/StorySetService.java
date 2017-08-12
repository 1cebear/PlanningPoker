package ru.planningpoker.service;

import ru.planningpoker.model.StorySet;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Icebear on 06.08.2017.
 */
public interface StorySetService {
    StorySet save(StorySet storySet);

    void delete(int id) throws NotFoundException;

    StorySet get(int id) throws NotFoundException;

    List<StorySet> getAll();

    void update(StorySet storySet);
}
