package org.vikl5.scheduler

import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.vikl5.service.MessageJob

class ScheduleJob {

    fun builder() {
        val job = JobBuilder.newJob(MessageJob::class.java)
            .withIdentity("postSlackMessage", "grp1")
            .build()
        val trigger = TriggerBuilder.newTrigger()
            .withIdentity("dailyTrigger", "grp1")
            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(19,49))
            .build()
        val scheduler = StdSchedulerFactory.getDefaultScheduler()
        scheduler.start()
        scheduler.scheduleJob(job, trigger)
    }


}