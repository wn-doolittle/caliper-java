package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonValue;

public abstract class Profile {

    public enum Action {
        ABANDONED("http://purl.imsglobal.org/caliper/v1/Action/Abandoned"),
        ACTIVATED("http://purl.imsglobal.org/caliper/v1/Action/Activated"),
        ATTACHED("http://purl.imsglobal.org/caliper/v1/Action/Attached"),
        BOOKMARKED("http://purl.imsglobal.org/caliper/v1/Action/Bookmarked"),
        CHANGED_RESOLUTION("http://purl.imsglobal.org/caliper/v1/Action/ChangedResolution"),
        CHANGED_SIZE("http://purl.imsglobal.org/caliper/v1/Action/ChangedSize"),
        CHANGED_SPEED("http://purl.imsglobal.org/caliper/v1/Action/ChangedSpeed"),
        CHANGED_VOLUME("http://purl.imsglobal.org/caliper/v1/Action/ChangedVolume"),
        CLASSIFIED("http://purl.imsglobal.org/caliper/v1/Action/Classified"),
        CLOSED_POPOUT("http://purl.imsglobal.org/caliper/v1/Action/ClosedPopout"),
        COMMENTED("http://purl.imsglobal.org/caliper/v1/Action/Commented"),
        COMPLETED("http://purl.imsglobal.org/caliper/v1/Action/Completed"),
        DEACTIVATED("http://purl.imsglobal.org/caliper/v1/Action/Deactivated"),
        DESCRIBED("http://purl.imsglobal.org/caliper/v1/Action/Described"),
        DISABLED_CLOSED_CAPTIONING("http://purl.imsglobal.org/caliper/v1/Action/DisabledClosedCaptioning"),
        ENABLED_CLOSED_CAPTIONING("http://purl.imsglobal.org/caliper/v1/Action/EnabledClosedCaptioning"),
        ENDED("http://purl.imsglobal.org/caliper/v1/Action/Ended"),
        ENTERED_FULLSCREEN("http://purl.imsglobal.org/caliper/v1/Action/EnteredFullscreen"),
        EXITED_FULLSCREEN("http://purl.imsglobal.org/caliper/v1/Action/ExitedFullscreen"),
        FORWARDED_TO("http://purl.imsglobal.org/caliper/v1/Action/ForwardedTo"),
        GRADED("http://purl.imsglobal.org/caliper/v1/Action/Graded"),
        HID("http://purl.imsglobal.org/caliper/v1/Action/Hid"),
        HIGHLIGHTED("http://purl.imsglobal.org/caliper/v1/Action/Highlighted"),
        JUMPED_TO("http://purl.imsglobal.org/caliper/v1/Action/JumpedTo"),
        IDENTIFIED("http://purl.imsglobal.org/caliper/v1/Action/Identified"),
        LIKED("http://purl.imsglobal.org/caliper/v1/Action/Liked"),
        LINKED("http://purl.imsglobal.org/caliper/v1/Action/Linked"),
        LOGGED_IN("http://purl.imsglobal.org/caliper/v1/Action/LoggedIn"),
        LOGGED_OUT("http://purl.imsglobal.org/caliper/v1/Action/LoggedOut"),
        MUTED("http://purl.imsglobal.org/caliper/v1/Action/Muted"),
        NAVIGATED_TO("http://purl.imsglobal.org/caliper/v1/Action/NavigatedTo"),
        OPENED_POPOUT("http://purl.imsglobal.org/caliper/v1/Action/OpenedPopout"),
        PAUSED("http://purl.imsglobal.org/caliper/v1/Action/Paused"),
        RANKED("http://purl.imsglobal.org/caliper/v1/Action/Ranked"),
        QUESTIONED("http://purl.imsglobal.org/caliper/v1/Action/Questioned"),
        RECOMMENDED("http://purl.imsglobal.org/caliper/v1/Action/Recommended"),
        REPLIED("http://purl.imsglobal.org/caliper/v1/Action/Replied"),
        RESTARTED("http://purl.imsglobal.org/caliper/v1/Action/Restarted"),
        RESUMED("http://purl.imsglobal.org/caliper/v1/Action/Resumed"),
        REVIEWED("http://purl.imsglobal.org/caliper/v1/Action/Reviewed"),
        REWOUND("http://purl.imsglobal.org/caliper/v1/Action/Rewound"),
        SEARCHED("http://purl.imsglobal.org/caliper/v1/Action/Searched"),
        SHARED("http://purl.imsglobal.org/caliper/v1/Action/Shared"),
        SHOWED("http://purl.imsglobal.org/caliper/v1/Action/Showed"),
        SKIPPED("http://purl.imsglobal.org/caliper/v1/Action/Skipped"),
        STARTED("http://purl.imsglobal.org/caliper/v1/Action/Started"),
        SUBMITTED("http://purl.imsglobal.org/caliper/v1/Action/Submitted"),
        SUBSCRIBED("http://purl.imsglobal.org/caliper/v1/Action/Subscribed"),
        TAGGED("http://purl.imsglobal.org/caliper/v1/Action/Tagged"),
        TIMED_OUT("http://purl.imsglobal.org/caliper/v1/Action/TimedOut"),
        VIEWED("http://purl.imsglobal.org/caliper/v1/Action/Viewed"),
        UNMUTED("http://purl.imsglobal.org/caliper/v1/Action/Unmuted");

        private String value;

        @JsonValue
        public String getValue() {
            return value;
        }

        private Action(String value){
            this.value = value;
        }
    }
}