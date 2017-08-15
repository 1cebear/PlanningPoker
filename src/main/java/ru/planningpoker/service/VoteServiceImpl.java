package ru.planningpoker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.planningpoker.model.Vote;
import ru.planningpoker.repository.VoteRepository;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

import static ru.planningpoker.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Icebear on 13.08.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote save(Vote vote, int storyId, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, storyId, userId);
    }

    public void delete(int id, int storyId, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, storyId, userId), id);
    }

    public Vote get(int id, int storyId, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, storyId, userId), id);
    }

    public List<Vote> getAll(int storyId, int userId) {
        return repository.getAll(storyId, userId);
    }

    public void update(Vote vote, int storyId, int userId) {
        Assert.notNull(vote, "vote must not be null");
        repository.save(vote, storyId, userId);
    }
}
