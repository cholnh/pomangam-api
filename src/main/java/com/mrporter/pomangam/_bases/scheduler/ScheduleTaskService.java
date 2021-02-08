package com.mrporter.pomangam._bases.scheduler;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleTaskService {

    // Task Scheduler
    private TaskScheduler scheduler;

    // A map for keeping scheduled tasks
    private Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public ScheduleTaskService(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }


    // Schedule Task to be executed every night at 00 or 12 am
    public void addTaskToScheduler(int id, String cron, Runnable task) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(cron, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        jobsMap.put(id, scheduledTask);
    }

    // Remove scheduled task
    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(id, null);
        }
    }

    // A context refresh event listener
    @EventListener({ ContextRefreshedEvent.class })
    void contextRefreshedEvent() {
        // Get all tasks from DB and reschedule them in case of context restarted
    }
}