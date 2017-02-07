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

package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.imsglobal.caliper.actions.Action;
import org.imsglobal.caliper.entities.agent.Person;
import org.imsglobal.caliper.entities.resource.Media;
import org.imsglobal.caliper.validators.EventValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SupportedActions({
    Action.OPENED_POPOUT,
    Action.CLOSED_POPOUT,
    Action.EXITED_FULLSCREEN,
    Action.ENTERED_FULLSCREEN,
    Action.CHANGED_SIZE,
    Action.CHANGED_RESOLUTION,
    Action.STARTED,
    Action.REWOUND,
    Action.RESUMED,
    Action.FORWARDED_TO,
    Action.PAUSED,
    Action.JUMPED_TO,
    Action.ENDED,
    Action.CHANGED_SPEED,
    Action.UNMUTED,
    Action.MUTED,
    Action.CHANGED_VOLUME,
    Action.DISABLED_CLOSED_CAPTIONING,
    Action.ENABLED_CLOSED_CAPTIONING
})
public class MediaEvent extends AbstractEvent {

    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(MediaEvent.class);

    /**
     * Utilize builder to construct MediaEvent.  Validate Media object copy rather than the
     * Media builder.  This approach protects the class against parameter changes from another
     * thread during the "window of vulnerability" between the time the parameters are checked
     * until when they are copied.
     *
     * @param builder
     */
    protected MediaEvent(Builder<?> builder) {
        super(builder);

        EventValidator.checkType(this.getType(), EventType.MEDIA);
        EventValidator.checkActorType(this.getActor(), Person.class);
        EventValidator.checkAction(this.getAction(), MediaEvent.class);
        EventValidator.checkObjectType(this.getObject(), Media.class);
    }

    /**
     * Initialize default parameter values in the builder.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends AbstractEvent.Builder<T>  {

        /*
         * Constructor
         */
        public Builder() {
            type(EventType.MEDIA);
        }

        /**
         * Client invokes build method in order to create an immutable profile object.
         * @return a new MediaEvent instance.
         */
        public MediaEvent build() {
            return new MediaEvent(this);
        }
    }

    /**
     * Self-reference that permits sub-classing of builder.
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}