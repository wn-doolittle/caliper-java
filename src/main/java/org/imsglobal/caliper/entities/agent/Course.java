package org.imsglobal.caliper.entities.agent;

/**
 * The Course interface marks an object type that represents a formal course offering.  The interface allows
 * Caliper to catch errors at compile time for instances of the marked class rather than
 * at runtime if a marker annotation was defined instead.
 */
public interface Course extends Organization {

    String getCourseNumber();

    String getAcademicSession();
}