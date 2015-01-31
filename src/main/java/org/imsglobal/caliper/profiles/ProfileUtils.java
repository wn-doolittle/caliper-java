package org.imsglobal.caliper.profiles;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ProfileUtils {

    /**
     * Get action localized value from resource bundle.
     * @param key
     * @return action string
     */
    public static String getLocalizedAction(String key) throws MissingResourceException {
        return ResourceBundle.getBundle("actions").getString(key);
    }
}
