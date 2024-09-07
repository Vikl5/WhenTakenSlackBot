package org.vikl5.scheduler

import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.vikl5.service.MessageJob

class ScheduleJob {
    companion object {
        private const val DEFAULT_CRON_SCHEDULE = "0 31 14 ? * MON-FRI"
    }

    fun builder() {
        val job = JobBuilder.newJob(MessageJob::class.java)
            .withIdentity("postSlackMessage", "grp1")
            .build()

        val cronSchedule = System.getenv("CRON_SCHEDULE") ?: DEFAULT_CRON_SCHEDULE

        val trigger = TriggerBuilder.newTrigger()
            .withIdentity("dailyTrigger", "grp1")
            .withSchedule(CronScheduleBuilder.cronSchedule(cronSchedule))
            .build()
        val scheduler = StdSchedulerFactory.getDefaultScheduler()
        scheduler.start()
        scheduler.scheduleJob(job, trigger)
    }


}
