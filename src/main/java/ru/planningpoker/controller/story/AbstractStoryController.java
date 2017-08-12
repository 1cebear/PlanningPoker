package ru.planningpoker.controller.story;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.planningpoker.model.Story;
import ru.planningpoker.service.StoryService;

import java.util.List;

import static ru.planningpoker.util.ValidationUtil.checkIdConsistent;
import static ru.planningpoker.util.ValidationUtil.checkNew;

/**
 * Created by Icebear on 11.08.2017.
 */
public class AbstractStoryController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StoryService service;

    public List<Story> getAll(int setId) {
        log.info("getAll");
        return service.getAll(setId);
    }

    public Story get(int id, int setId) {
        log.info("get {}", id);
        return service.get(id, setId);
    }

    public Story create(Story story, int setId) {
        log.info("create {}", story);
        checkNew(story);
        return service.save(story, setId);
    }

    public void delete(int id, int setId) {
        log.info("delete {}", id);
        service.delete(id, setId);
    }

    public void update(Story story, int id, int setId) {
        log.info("update {}", story);
        checkIdConsistent(story, id);
        service.update(story, setId);
    }
}
