package com.example.hospitalreservation.service;

import java.time.LocalDateTime;

public interface TimeEntity {
    default boolean conflictsWith(TimeEntity otherEntity) {
        return this.containsTime(otherEntity.getStartTime())
                || this.containsTime(otherEntity.getEndTime())
                || otherEntity.containsTime(this.getStartTime())
                || otherEntity.containsTime(this.getEndTime());
    }

    default boolean containsTime(LocalDateTime time) {
        return this.getStartTime().isBefore(time)
                && this.getEndTime().isAfter(time);
    }

    default boolean isFullyContainedIn(LocalDateTime startTime, LocalDateTime endTime) {
        return ((startTime.isBefore(this.getStartTime()) || startTime.isEqual(this.getStartTime()))
                && (endTime.isAfter(this.getStartTime()))) &&
                (startTime.isBefore(this.getEndTime())
                        && (endTime.isAfter(this.getEndTime()) || endTime.isEqual(this.getEndTime())));
    }

    Number getId();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();
}
