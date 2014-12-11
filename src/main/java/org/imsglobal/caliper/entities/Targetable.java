package org.imsglobal.caliper.entities;

/**
 * The targetable interface marks an object type that provides coordinates to a target destination
 * required by certain actions.  The interface allows Caliper to catch errors at compile time for
 * instances of the marked class rather than at runtime if a marker annotation was defined instead.
 */
public interface Targetable {

}
