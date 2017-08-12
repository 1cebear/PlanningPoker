package ru.planningpoker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.planningpoker.model.Story;
import ru.planningpoker.repository.StoryRepository;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

import static ru.planningpoker.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Icebear on 06.08.2017.
 */
@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository repository;

    @Autowired
    public StoryServiceImpl(StoryRepository repository) {
        this.repository = repository;
    }

    public Story save(Story story, int setId) {
        Assert.notNull(story, "story must not be null");
        return repository.save(story, setId);
    }

    public void delete(int id, int setId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, setId), id);
    }

    public Story get(int id, int setId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, setId), id);
    }

    public List<Story> getAll(int setId) {
        return repository.getAll(setId);
    }

    public void update(Story story, int setId) {
        Assert.notNull(story, "story must not be null");
        repository.save(story, setId);
    }
}
