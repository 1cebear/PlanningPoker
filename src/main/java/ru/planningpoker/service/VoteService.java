package ru.planningpoker.service;

import ru.planningpoker.model.Vote;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Icebear on 13.08.2017.
 */
public interface VoteService {
    Vote save(Vote vote, int storyId, int userId);

    void delete(int id, int storyId, int userId) throws NotFoundException;

    Vote get(int id, int storyId, int userId) throws NotFoundException;

    List<Vote> getAll(int storyId, int userId);

    void update(Vote vote, int storyId, int userId);

    List<Vote> getAllForSet(int setId);
}
