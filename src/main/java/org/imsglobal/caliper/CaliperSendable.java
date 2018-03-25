package org.imsglobal.caliper;

/**
 * The sendable interface marks an object type that can be added to an Envelope's data payload.
 * The interface allows Caliper to catch errors at compile time for instances of the marked class rather
 * than at runtime if a marker annotation was defined instead.
 */
public interface CaliperSendable {

}