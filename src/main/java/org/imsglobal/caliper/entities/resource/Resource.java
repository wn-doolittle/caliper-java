package org.imsglobal.caliper.entities.resource;

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Referrer;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.agent.Agent;
import org.joda.time.DateTime;

/**
 * A generic representation of a resource, analogous to schema.org's CreativeWork.
 */
public interface Resource extends Entity, Generatable, Referrer, Targetable {

    String getMediaType();

    ImmutableList<Agent> getCreators();

    ImmutableList<LearningObjective> getLearningObjectives();

    ImmutableList<String> getKeywords();

    Entity getIsPartOf();

    DateTime getDatePublished();

    String getVersion();
}
