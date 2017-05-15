package org.imsglobal.caliper.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("isCoercedToId")
public interface CaliperCoercible {

    /**
     * Coerce this entity to its id when serializing.
     * @return boolean
     */
    boolean isCoercedToId();

    /**
     * Identifier
     * @return id
     */
    String getId();
}
