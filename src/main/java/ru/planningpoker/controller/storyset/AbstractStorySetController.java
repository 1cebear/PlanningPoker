package ru.planningpoker.controller.storyset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.planningpoker.model.StorySet;
import ru.planningpoker.service.StorySetService;

import java.util.List;

import static ru.planningpoker.util.ValidationUtil.checkIdConsistent;
import static ru.planningpoker.util.ValidationUtil.checkNew;

/**
 * Created by Icebear on 10.08.2017.
 */
public class AbstractStorySetController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StorySetService service;

    public List<StorySet> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public StorySet get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public StorySet create(StorySet storySet) {
        log.info("create {}", storySet);
        checkNew(storySet);
        return service.save(storySet);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(StorySet storySet, int id) {
        log.info("update {}", storySet);
        checkIdConsistent(storySet, id);
        service.update(storySet);
    }

}
