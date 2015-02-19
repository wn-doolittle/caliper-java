package org.imsglobal.caliper.profiles;

import javax.annotation.Nonnull;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ProfileUtils {

    /**
     * Get action localized value from resource bundle.
     * @param key
     * @return action string
     */
    public static String getLocalizedAction(@Nonnull String key) throws MissingResourceException {
        return ResourceBundle.getBundle("actions").getString(key);
    }
}
