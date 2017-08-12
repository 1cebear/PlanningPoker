package ru.planningpoker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.planningpoker.model.StorySet;
import ru.planningpoker.repository.StorySetRepository;
import ru.planningpoker.util.exception.NotFoundException;

import java.util.List;

import static ru.planningpoker.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Icebear on 06.08.2017.
 */
@Service
public class StorySetServiceImpl implements StorySetService{

    private final StorySetRepository repository;

    @Autowired
    public StorySetServiceImpl(StorySetRepository repository) {
        this.repository = repository;
    }

    public StorySet save(StorySet storySet) {
        Assert.notNull(storySet, "storyset must not be null");
        return repository.save(storySet);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public StorySet get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<StorySet> getAll() {
        return repository.getAll();
    }

    public void update(StorySet storySet) {
        Assert.notNull(storySet, "storyset must not be null");
        repository.save(storySet);
    }
}
