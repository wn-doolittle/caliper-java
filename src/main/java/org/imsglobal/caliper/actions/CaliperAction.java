package org.imsglobal.caliper.actions;

/**
 * The CaliperAction interface allows an implementor to extend the otherwise static list of Caliper actions with
 * additional actions.  The implementer can follow up by writing an enum manager in order to join the core and custom
 * action enums together.
 */
public interface CaliperAction {

    /**
     * Action string.
     * @return action value
     */
    String value();
}