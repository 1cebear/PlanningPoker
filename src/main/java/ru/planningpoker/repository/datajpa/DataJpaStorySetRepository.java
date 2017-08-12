package ru.planningpoker.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.planningpoker.model.StorySet;
import ru.planningpoker.repository.StorySetRepository;

import java.util.List;

/**
 * Created by Icebear on 09.08.2017.
 */
@Repository
public class DataJpaStorySetRepository implements StorySetRepository {

    private static final Sort SORT_NAME = new Sort("name");

    @Autowired
    private CrudStorySetRepository crudRepository;

    public StorySet save(StorySet storySet) {
        return crudRepository.save(storySet);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public StorySet get(int id) {
        return crudRepository.findOne(id);
    }

    public List<StorySet> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }
}
