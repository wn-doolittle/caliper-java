package org.imsglobal.caliper.entities;

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;
import org.joda.time.DateTime;

import org.imsglobal.caliper.entities.foaf.Agent;

/**
 * A generic representation of a resource, analogous to schema.org's CreativeWork.
 * (see https://schema.org/CreativeWork).
 */
public interface Resource extends CreativeWork, Entity, Targetable {

    String getMediaType();

    ImmutableList<Agent> getCreators();

    ImmutableList<LearningObjective> getLearningObjectives();

    ImmutableList<String> getKeywords();

    CreativeWork getIsPartOf();

    DateTime getDatePublished();

    String getVersion();
}
