package ru.planningpoker.service;

import ru.planningpoker.model.Story;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Icebear on 06.08.2017.
 */
public interface StoryService {
    Story save(Story story, int setId);

    void delete(int id, int setId) throws NotFoundException;

    Story get(int id, int setId) throws NotFoundException;

    List<Story> getAll(int setId);

    void update(Story story, int setId);
}
