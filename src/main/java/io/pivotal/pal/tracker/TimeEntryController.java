package io.pivotal.pal.tracker;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    public TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdTimeEntry = this.timeEntryRepository.create(timeEntryToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTimeEntry);
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry foundTimeEntry = this.timeEntryRepository.find(timeEntryId);
        if(foundTimeEntry != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundTimeEntry);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundTimeEntry);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {

        TimeEntry foundTimeEntry = this.timeEntryRepository.update(timeEntryId, expected);

        if(foundTimeEntry != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundTimeEntry);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(foundTimeEntry);
        }
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
