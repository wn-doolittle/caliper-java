package org.imsglobal.caliper.entities.w3c;

import com.google.common.collect.ImmutableList;

/**
 * A collection of people organized together into a community or other social, commercial or political structure.
 * The group has some common purpose or reason for existence which goes beyond the set of people belonging to it
 * and can act as an Agent. Organizations are often decomposable into hierarchical structures.
 */
public interface Organization {

    /**
     * An identifier that can be used to used to uniquely identify the organization.  Equivalent to org:identifier.
     * @return the identifier.
     */
    String getId();

    /**
     * @return the type.
     */
    String getType();

    /**
     * A membership entity that records the role of some Agent within this Organization.
     * @return membership
     */
    ImmutableList<Membership> getMembership();

    /**
     * The container holding the Memberships for this Organization.
     * @return memberships;
     */
    // ImmutableList<MembershipContainer> getMemberships();

    /**
     * Equivalent of W3C Organization Ontology org:subOrganizationOf property.  Represents hierarchical containment
     * of Organizations or OrganizationalUnits; indicates an Organization which contains this Organization.
     * @return parent organization
     */
    Organization getSubOrganizationOf();
}