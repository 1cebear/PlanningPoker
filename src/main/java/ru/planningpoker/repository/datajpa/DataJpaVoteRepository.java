package ru.planningpoker.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.planningpoker.model.Vote;
import ru.planningpoker.repository.VoteRepository;

import java.util.List;

/**
 * Created by Icebear on 13.08.2017.
 */
@Repository
public class DataJpaVoteRepository implements VoteRepository {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudStoryRepository crudStoryRepository;

    @Autowired
    private CrudVoteRepository crudVoteRepository;

    @Transactional
    public Vote save(Vote vote, int storyId, int userId) {
        if (!vote.isNew() && get(vote.getId(), storyId, userId) == null) {
            return null;
        }
        vote.setStory(crudStoryRepository.getOne(storyId));
        vote.setUser(crudUserRepository.getOne(userId));
        return crudVoteRepository.save(vote);
    }

    public boolean delete(int id, int storyId, int userId) {
        return crudVoteRepository.delete(id, storyId, userId) != 0;
    }

    public Vote get(int id, int storyId, int userId) {
        Vote vote = crudVoteRepository.findOne(id);
        return vote != null && vote.getStory().getId() == storyId && vote.getUser().getId() == userId ? vote : null;
    }

    public List<Vote> getAll(int storyId, int userId) {
        return crudVoteRepository.getAll(storyId, userId);
    }

    public List<Vote> getAllForSet(int setId) {
        return crudVoteRepository.getAllForSet(setId);
    }
}
