package org.imsglobal.caliper.entities.resource;

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.LearningObjective;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.agent.Agent;
import org.joda.time.DateTime;

/**
 * A generic representation of a resource, analogous to schema.org's CreativeWork.
 * (see https://schema.org/CreativeWork).
 */
public interface Resource extends Entity, Generatable, Targetable {

    String getMediaType();

    ImmutableList<Agent> getCreators();

    ImmutableList<LearningObjective> getLearningObjectives();

    ImmutableList<String> getKeywords();

    Entity getIsPartOf();

    DateTime getDatePublished();

    String getVersion();
}
