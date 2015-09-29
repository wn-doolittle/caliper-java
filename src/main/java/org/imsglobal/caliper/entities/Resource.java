package org.imsglobal.caliper.entities;

import com.google.common.collect.ImmutableList;
import org.imsglobal.caliper.entities.schemadotorg.CreativeWork;
import org.joda.time.DateTime;

public interface Resource extends CreativeWork, Entity, Targetable {

    ImmutableList<String> getObjectTypes();

    ImmutableList<LearningObjective> getLearningObjectives();

    ImmutableList<String> getKeywords();

    CreativeWork getIsPartOf();

    DateTime getDatePublished();

    String getVersion();
}
