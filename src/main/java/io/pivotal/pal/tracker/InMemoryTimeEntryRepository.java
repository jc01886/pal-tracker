package io.pivotal.pal.tracker;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    Map<Long, TimeEntry> timeEntryData = new HashMap<Long, TimeEntry>();

    private long setId = 0;

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry created = new TimeEntry(++setId, timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());

        timeEntryData.put(created.getId(), created);
        return created;
    }

    public TimeEntry find(long id) {
        System.out.println("Inside find method");
        return timeEntryData.get(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry created = new TimeEntry(id, timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());

        if (timeEntryData.get(created.getId()) == null) {
            return null;
        }

        timeEntryData.put(created.getId(), created);
        return created;

    }

    public List<TimeEntry> list() {
        return timeEntryData.values().stream().collect(Collectors.toList());


    }

    public void delete(long id) {
        timeEntryData.remove(id);

    }
}
