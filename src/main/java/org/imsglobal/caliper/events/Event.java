package org.imsglobal.caliper.events;

import org.imsglobal.caliper.entities.Entity;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.joda.time.DateTime;

public interface Event {

    /**
     * The JSON-LD context provides a mapping of terms to IRIs.  The identifier
     * should be expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the context IRI.
     */
    String getContext();

    /**
     * Specifies the type of event or node in the graph as defined by JSON-LD.  The type should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the event type IRI
     */
    String getType();

    /**
     * The actor engaged in the interaction.  Analogous to a subject.
     * @return
     */
    Agent getActor();

    /**
     * The action undertaken by the actor.  Analogous to a predicate or verb.  The action should be
     * expressed as a unique IRI in conformance with the JSON-LD specification.
     * @return the action undertaken by the actor
     */
    String getAction();

    /**
     *  The object of the interaction.  The object should be expressed as a unique IRI in conformance
     *  with the JSON-LD specification.
     * @return the object of the interaction
     */
    Entity getObject();

    /**
     * A combined date and time representation (including milliseconds) of when an event occurred
     * formatted in accordance with the ISO 8601 standard.
     * @return the event time
     */
    DateTime getEventTime();
}