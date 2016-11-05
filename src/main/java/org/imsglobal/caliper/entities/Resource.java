package org.imsglobal.caliper.entities;

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.joda.time.DateTime;

/**
 * A generic representation of a resource, analogous to schema.org's CreativeWork.
 * (see https://schema.org/CreativeWork).
 */
public interface Resource extends Entity, Targetable {

    String getMediaType();

    ImmutableList<Agent> getCreators();

    ImmutableList<LearningObjective> getLearningObjectives();

    ImmutableList<String> getKeywords();

    Entity getIsPartOf();

    DateTime getDatePublished();

    String getVersion();
}
