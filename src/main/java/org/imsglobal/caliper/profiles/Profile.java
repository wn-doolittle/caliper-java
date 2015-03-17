package org.imsglobal.caliper.profiles;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.*;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.events.*;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class Profile {

    public enum Action {
        NAVIGATED_TO("http://purl.imsglobal.org/caliper/v1/Action/NavigatedTo"),

        LOGGED_IN("http://purl.imsglobal.org/caliper/v1/Action/LoggedIn"),
        LOGGED_OUT("http://purl.imsglobal.org/caliper/v1/Action/LoggedOut"),
        TIMED_OUT("http://purl.imsglobal.org/caliper/v1/Action/TimedOut"),

        SEARCHED("http://purl.imsglobal.org/caliper/v1/Action/Searched"),
        VIEWED("http://purl.imsglobal.org/caliper/v1/Action/Viewed"),
        GRADED("http://purl.imsglobal.org/caliper/v1/Action/Graded"),
        ENABLED_CLOSED_CAPTIONING("http://purl.imsglobal.org/caliper/v1/Action/EnabledClosedCaptioning"),
        DISABLED_CLOSED_CAPTIONING("http://purl.imsglobal.org/caliper/v1/Action/DisabledClosedCaptioning"),
        CHANGED_VOLUME("http://purl.imsglobal.org/caliper/v1/Action/ChangedVolume"),

        MUTED("http://purl.imsglobal.org/caliper/v1/Action/Muted"),
        UNMUTED("http://purl.imsglobal.org/caliper/v1/Action/Unmuted"),
        CHANGED_SPEED("http://purl.imsglobal.org/caliper/v1/Action/ChangedSpeed"),

        ENDED("http://purl.imsglobal.org/caliper/v1/Action/Ended"),
        JUMPED_TO("http://purl.imsglobal.org/caliper/v1/Action/JumpedTo"),
        FORWARDED_TO("http://purl.imsglobal.org/caliper/v1/Action/ForwardedTo"),
        PAUSED("http://purl.imsglobal.org/caliper/v1/Action/Paused"),
        RESUMED("http://purl.imsglobal.org/caliper/v1/Action/Resumed"),
        REWINDED("http://purl.imsglobal.org/caliper/v1/Action/Rewinded"),
        STARTED("http://purl.imsglobal.org/caliper/v1/Action/Started"),
        CHANGED_RESOLUTION("http://purl.imsglobal.org/caliper/v1/Action/ChangedResolution"),
        CHANGED_SIZE("http://purl.imsglobal.org/caliper/v1/Action/ChangedSize"),
        CLOSED_POPOUT("http://purl.imsglobal.org/caliper/v1/Action/ClosedPopout"),
        ENTERED_FULLSCREEN("http://purl.imsglobal.org/caliper/v1/Action/EnteredFullscreen"),
        EXITED_FULLSCREEN("http://purl.imsglobal.org/caliper/v1/Action/ExitedFullscreen"),
        OPENED_POPOUT("http://purl.imsglobal.org/caliper/v1/Action/OpenedPopout"),

        ABANDONED("http://purl.imsglobal.org/caliper/v1/Action/Abandoned"),
        ACTIVATED("http://purl.imsglobal.org/caliper/v1/Action/Activated"),
        COMPLETED("http://purl.imsglobal.org/caliper/v1/Action/Completed"),
        DEACTIVATED("http://purl.imsglobal.org/caliper/v1/Action/Deactivated"),
        HID("http://purl.imsglobal.org/caliper/v1/Action/Hid"),
        REVIEWED("http://purl.imsglobal.org/caliper/v1/Action/Reviewed"),
        SHOWED("http://purl.imsglobal.org/caliper/v1/Action/Showed"),
        UNRECOGNIZED("http://purl.imsglobal.org/caliper/v1/Action/Unrecognized"),

        RESTARTED("http://purl.imsglobal.org/caliper/v1/Action/Restarted"),
        SUBMITTED("http://purl.imsglobal.org/caliper/v1/Action/Submitted"),

        SKIPPED("http://purl.imsglobal.org/caliper/v1/Action/Skipped"),

        ATTACHED("http://purl.imsglobal.org/caliper/v1/Action/Attached"),
        BOOKMARKED("http://purl.imsglobal.org/caliper/v1/Action/Bookmarked"),
        CLASSIFIED("http://purl.imsglobal.org/caliper/v1/Action/Classified"),
        COMMENTED("http://purl.imsglobal.org/caliper/v1/Action/Commented"),
        DESCRIBED("http://purl.imsglobal.org/caliper/v1/Action/Described"),
        HIGHLIGHTED("http://purl.imsglobal.org/caliper/v1/Action/Highlighted"),
        IDENTIFIED("http://purl.imsglobal.org/caliper/v1/Action/Identified"),
        LIKED("http://purl.imsglobal.org/caliper/v1/Action/Liked"),
        LINKED("http://purl.imsglobal.org/caliper/v1/Action/Linked"),
        RANKED("http://purl.imsglobal.org/caliper/v1/Action/Ranked"),
        QUESTIONED("http://purl.imsglobal.org/caliper/v1/Action/Questioned"),
        RECOMMENDED("http://purl.imsglobal.org/caliper/v1/Action/Recommended"),
        REPLIED("http://purl.imsglobal.org/caliper/v1/Action/Replied"),
        SHARED("http://purl.imsglobal.org/caliper/v1/Action/Shared"),
        SUBSCRIBED("http://purl.imsglobal.org/caliper/v1/Action/Subscribed"),
        TAGGED("http://purl.imsglobal.org/caliper/v1/Action/Tagged");

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