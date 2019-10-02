package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> inMemoryRepo = null;
    private Long counter = 0l;

    public InMemoryTimeEntryRepository() {
        this.inMemoryRepo = new HashMap<Long, TimeEntry>();
    }

    public TimeEntry create(TimeEntry timeEntry) {
        ++counter;
        timeEntry.setId(counter);
        inMemoryRepo.put(counter, timeEntry);
        return inMemoryRepo.get(counter);
    }

    public TimeEntry find(long timeEntryId) {
        return inMemoryRepo.get(timeEntryId);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(inMemoryRepo.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(inMemoryRepo.get(id) != null) {
            timeEntry.setId(id);
            inMemoryRepo.put(id, timeEntry);
            return inMemoryRepo.get(id);
        }
        else {
            return null;
        }
    }

    public void delete(long id) {
        inMemoryRepo.remove(id);
    }
}
