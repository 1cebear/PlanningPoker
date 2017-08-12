package ru.planningpoker.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.planningpoker.model.Story;
import ru.planningpoker.repository.StoryRepository;

import java.util.List;

/**
 * Created by Icebear on 09.08.2017.
 */
@Repository
public class DataJpaStoryRepository implements StoryRepository {

    @Autowired
    private CrudStorySetRepository crudStorySetRepository;

    @Autowired
    private CrudStoryRepository crudStoryRepository;

    @Transactional
    public Story save(Story story, int setId) {
        if (!story.isNew() && get(story.getId(), setId) == null) {
            return null;
        }
        story.setStorySet(crudStorySetRepository.getOne(setId));
        return crudStoryRepository.save(story);
    }

    public boolean delete(int id, int setId) {
        return crudStoryRepository.delete(id, setId) != 0;
    }

    public Story get(int id, int setId) {
        Story story = crudStoryRepository.findOne(id);
        return story != null && story.getStorySet().getId() == setId ? story : null;
    }

    public List<Story> getAll(int setId) {
        return crudStoryRepository.getAll(setId);
    }
}
