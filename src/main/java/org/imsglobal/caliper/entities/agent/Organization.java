package org.imsglobal.caliper.entities.agent;

import org.imsglobal.caliper.entities.Entity;

/**
 * The organization interface marks an object type that represents a collection of people organized
 * into a community or other social, commercial or political structure.  The interface allows
 * Caliper to catch errors at compile time for instances of the marked class rather than
 * at runtime if a marker annotation was defined instead.
 */
public interface Organization extends Entity, Agent {

    /**
     * Return the parent organization.
     */
    Organization getSubOrganizationOf();
}