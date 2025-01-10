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

package org.imsglobal.caliper.actions;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Caliper action vocabulary.
 */
public enum Action implements CaliperAction {
    ABANDONED("Abandoned"),
    ACTIVATED("Activated"),
    ADDED("Added"),
    ASSIGNED_ALIAS("AssignedAlias"),
    ATTACHED("Attached"),
    BOOKMARKED("Bookmarked"),
    CHANGED_RESOLUTION("ChangedResolution"),
    CHANGED_SIZE("ChangedSize"),
    CHANGED_SPEED("ChangedSpeed"),
    CHANGED_VOLUME("ChangedVolume"),
    CLASSIFIED("Classified"),
    CLOSED_POPOUT("ClosedPopout"),
    COMMENTED("Commented"),
    COMPLETED("Completed"),
    CREATED("Created"),
    DEACTIVATED("Deactivated"),
    DELETED("Deleted"),
    DESCRIBED("Described"),
    DISABLED_CLOSED_CAPTIONING("DisabledClosedCaptioning"),
    DISLIKED("Disliked"),
    ENABLED_CLOSED_CAPTIONING("EnabledClosedCaptioning"),
    ENDED("Ended"),
    ENTERED_FULLSCREEN("EnteredFullScreen"),
    EXITED_FULLSCREEN("ExitedFullScreen"),
    FORWARDED_TO("ForwardedTo"),
    GRADED("Graded"),
    HID("Hid"),
    HIGHLIGHTED("Highlighted"),
    JUMPED_TO("JumpedTo"),
    IDENTIFIED("Identified"),
    LIKED("Liked"),
    LINKED("Linked"),
    LOGGED_IN("LoggedIn"),
    LOGGED_OUT("LoggedOut"),
    MARKED_AS_READ("MarkedAsRead"),
    MARKED_AS_UNREAD("MarkedAsUnread"),
    MODIFIED("Modified"),
    MUTED("Muted"),
    NAVIGATED_TO("NavigatedTo"),
    NAVIGATED_AWAY_FROM("NavigatedAwayFrom"),
    OPENED_POPOUT("OpenedPopout"),
    PAUSED("Paused"),
    POSTED("Posted"),
    QUESTIONED("Questioned"),
    RANKED("Ranked"),
    RECOMMENDED("Recommended"),
    REMOVED("Removed"),
    REPLIED("Replied"),
    RESET("Reset"),
    RESTARTED("Restarted"),
    RESUMED("Resumed"),
    RETRIEVED("Retrieved"),
    REVIEWED("Reviewed"),
    REWOUND("Rewound"),
    SEARCHED("Searched"),
    SHARED("Shared"),
    SHOWED("Showed"),
    SKIPPED("Skipped"),
    STARTED("Started"),
    SUBMITTED("Submitted"),
    SUBSCRIBED("Subscribed"),
    TAGGED("Tagged"),
    TIMED_OUT("TimedOut"),
    UNMUTED("Unmuted"),
    UNSUBSCRIBED("Unsubscribed"),
    USED("Used"),
    VIEWED("Viewed"),
    TAKE_LAUNCHED("TakeLaunched"),
    TAKE_COMPLETED("TakeCompleted"),
    SAVED("Saved");

    private String value;

    /**
     * Constructor
     * @param value
     */
    private Action(String value){
        this.value = value;
    }

    /**
     * Enum string value.
     * @return string value.
     */
    @Override
    @JsonValue
    public String value() {
        return value;
    }
}