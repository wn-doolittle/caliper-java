package org.imsglobal.caliper.entities.w3c;

import com.google.common.collect.ImmutableList;

/**
 * Indicates the nature of an Agent's membership in an organization.  A W3C Membership represents
 * an n-ary relationship between an Agent, an Organization and a Role.  A Caliper Membership varies
 * from the W3C Membership class definition by allowing users to specify a set of roles associated
 * with an Agent's membership in an organization.  See http://www.w3.org/TR/vocab-org/#org:Membership
 */
public interface Membership {

    /**
     * @return the Person (or other Agent including Organization) involved in the Membership relationship.
     */
    //Agent getMember();
    String getMemberId();

    /**
     * @return the Organization in which the Agent is a member.
     */
    //Organization getOrganization();
    String getOrganizationId();

    /**
     * @return the set of roles that the agent plays in a membership relationship with an organization.  Each
     * string is expected to be in the form of a dereferenceable URI.
     */
    ImmutableList<String> getRoles();

    /**
     * @return The current status of a membership which applies to all roles.
     */
    String getStatus();
}