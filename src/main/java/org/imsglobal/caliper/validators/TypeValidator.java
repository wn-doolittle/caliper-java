/**
 * This file is part of IMS Caliper Analytics™ and is licensed to
 * IMS Global Learning Consortium, Inc. (http://www.imsglobal.org)
 * under one or more contributor license agreements.  See the NOTICE
 * file distributed with this work for additional information.
 *
 * IMS Caliper is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, version 3 of the License.
 *
 * IMS Caliper is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.imsglobal.caliper.validators;

import org.imsglobal.caliper.entities.CaliperGeneratable;
import org.imsglobal.caliper.entities.CaliperTargetable;
import org.imsglobal.caliper.entities.agent.CaliperAgent;

import static com.google.common.base.Preconditions.checkArgument;

public class TypeValidator {

    /**
     * Check actor type
     * @param actor
     * @param type
     * @throws IllegalArgumentException
     */
    protected static void checkActorType(CaliperAgent actor, Class<?> type) throws IllegalArgumentException {
        checkArgument(actor != null, "actor must be specified");
        checkArgument(isOfType(actor, type),
            "expected actor %s but was %s",
            type.getCanonicalName(),
            actor.getClass().getCanonicalName());
    }

    /**
     * Check object type.
     * @param object
     * @param type
     * @return Validation result
     */
    protected static void checkObjectType(Object object, Class<?> type) throws IllegalArgumentException {
        checkArgument(object != null, "object must be specified");
        checkArgument(isOfType(object, type),
            "expected event object %s but was %s",
            type.getCanonicalName(),
            object.getClass().getCanonicalName());
    }

    /**
     * Check target type.
     * @param target
     * @param type
     * @return Validation result
     */
    protected static void checkTargetType(CaliperTargetable target, Class<?> type) throws IllegalArgumentException {
        checkArgument(target != null, "target must be specified");
        checkArgument(isOfType(target, type),
            "expected event targetable %s but was %s",
            type.getCanonicalName(),
            target.getClass().getCanonicalName());
    }

    /**
     * Check generated type.
     * @param generated
     * @param type
     * @return Validation result
     */
    protected static void checkGeneratedType(CaliperGeneratable generated, Class<?> type) throws IllegalArgumentException {
        checkArgument(generated != null, "generated object must be specified");
        checkArgument(isOfType(generated, type),
                "expected event generatable %s but was %s",
                type.getCanonicalName(),
                generated.getClass().getCanonicalName());
    }

    /**
     * Validate object type equality against a provided class.  Determines if the specified
     * Object is assignment-compatible with the object represented by this Class. This method
     * is the dynamic equivalent of the Java language instanceof operator. The method returns
     * true if the specified Object argument is non-null and can be cast to the reference type
     * represented by this Class object without raising a ClassCastException. It returns false
     * otherwise.
     * @param object
     * @param type
     * @return boolean true/false
     */
    private static boolean isOfType(Object object, Class<?> type) {
        return type.isInstance(object);
    }
}