package ru.planningpoker.repository;

import ru.planningpoker.model.Story;


import java.util.List;

/**
 * Created by Icebear on 06.08.2017.
 */
public interface StoryRepository {

    Story save(Story story, int setId);

    // false if not found
    boolean delete(int id, int setId);

    // null if not found
    Story get(int id, int setId);

    List<Story> getAll(int setId);
}
