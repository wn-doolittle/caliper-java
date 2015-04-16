package org.imsglobal.caliper.entities.foaf;

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.Type;
import org.imsglobal.caliper.entities.w3c.Membership;

/**
 * An entity that can do things. Typical kinds of agents include people, organizations and software systems.
 * See http://xmlns.com/foaf/spec/#term_Agent
 */
public interface Agent {

    String getId();

    Type getType();

    ImmutableList<Membership> hasMembership();
}