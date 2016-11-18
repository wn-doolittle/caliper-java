package org.imsglobal.caliper.entities;

/**
 * The referable interface marks an object type that can supply a referrer context to another Entity in the
 * course of web navigation.  The interface allows Caliper to catch errors at compile time for
 * instances of the marked class rather than at runtime if a marker annotation was defined instead.
 */
public interface Referrerable {

}
