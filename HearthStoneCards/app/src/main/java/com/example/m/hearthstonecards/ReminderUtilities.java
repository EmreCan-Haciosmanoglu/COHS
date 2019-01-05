package com.example.m.hearthstonecards;

import android.content.Context;
import android.support.annotation.NonNull;

import java.sql.Driver;

public class ReminderUtilities
{
    private static final int REMINDER_INTERVAL_MINUTES = 15;
    private static final int REMINDER_INTERVAL_SECONDS = REMINDER_INTERVAL_MINUTES*60;
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;
    private static final String REMINDER_JOB_TAG = "hydration_reminder_tag";
    private static boolean sInitialized;
    synchronized public static void scheduleChargingReminder(@NonNull final Context context) {
        if (!sInitialized) {
            Driver driver = new GooglePlayDriver(context);
            FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
            Job constraintReminderJob = dispatcher.newJobBuilder()
                    .setService(WaterReminderFirebaseJobService.class)
                    .setTag(REMINDER_JOB_TAG)
                    .setConstraints(Constraint.DEVICE_CHARGING)
                    .setLifetime(Lifetime.FOREVER)
                    .setRecurring(true)
                    .setTrigger(Trigger.executionWindow(
                            REMINDER_INTERVAL_SECONDS,
                            REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                    .setReplaceCurrent(true)
                    .build();
            dispatcher.schedule(constraintReminderJob);
            sInitialized = true;
        }
    }
}