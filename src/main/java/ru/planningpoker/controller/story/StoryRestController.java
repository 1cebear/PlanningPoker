package ru.planningpoker.controller.story;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.planningpoker.model.Story;

import java.net.URI;
import java.util.List;

/**
 * Created by Icebear on 11.08.2017.
 */
@RestController
@RequestMapping(value = StoryRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class StoryRestController extends AbstractStoryController {

    static final String REST_URL = "/rest/storyset/{setId}/stories";

    @Override
    @GetMapping
    public List<Story> getAll(@PathVariable("setId") int setId) {
        return super.getAll(setId);
    }

    @Override
    @GetMapping("/{id}")
    public Story get(@PathVariable("id") int id, @PathVariable("setId") int setId) {
        return super.get(id, setId);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("setId") int setId) {
        super.delete(id, setId);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Story story, @PathVariable("id") int id, @PathVariable("setId") int setId) {
        super.update(story, id, setId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Story> createWithLocation(@RequestBody Story story, @PathVariable("restaurantId") int setId) {
        Story created = super.create(story, setId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(setId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
