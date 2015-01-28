package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Person;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.imsglobal.caliper.entities.Session;

public class PropertyTypeCheck {

    /**
     * Validate actor is a person.
     * @param actor
     * @return boolean true/false
     */
    public static boolean isAgentPerson(Agent actor) {
        return actor != null && actor instanceof Person;
    }

    /**
     * Validate actor is an edApp.
     * @param actor
     * @return boolean true/false
     */
    public static boolean isAgentSoftwareApplication(Agent actor) {
        return actor != null && actor instanceof SoftwareApplication;
    }

    /**
     * Validate object is an edApp.
     * @param object
     * @return boolean true/false
     */
    public static boolean isObjectSoftwareApplication(Object object) {
        return object != null && object instanceof SoftwareApplication;
    }

    /**
     * Validate target is a digital resource.
     * @param target
     * @return boolean true/false
     */
    public static boolean isTargetableDigitalResource(Targetable target) {
        return target != null && target instanceof DigitalResource;
    }

    /**
     * Validate target is session.
     * @param target
     * @return boolean true/false
     */
    public static boolean isTargetableSession(Targetable target) {
        return target != null && target instanceof Session;
    }

    /**
     * Validate generated object is a session.
     * @param generated
     * @return boolean true/false
     */
    public static boolean isGeneratableSession(Generatable generated) {
        return generated != null && generated instanceof Session;
    }
}