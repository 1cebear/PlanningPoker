package ru.planningpoker.repository;

import ru.planningpoker.model.Vote;

import java.util.List;

/**
 * Created by Icebear on 13.08.2017.
 */
public interface VoteRepository {
    Vote save(Vote vote, int storyId, int userId);

    // false if not found
    boolean delete(int id, int storyId, int userId);

    // null if not found
    Vote get(int id, int storyId, int userId);

    List<Vote> getAll(int storyId, int userId);
}
