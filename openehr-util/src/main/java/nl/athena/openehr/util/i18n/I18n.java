package nl.athena.openehr.util.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Utility class for internationalization.
 */
@Component
@Lazy(false)
public final class I18n {

    private static MessageSource messageSource;

    /**
     * Create a new instance of I18n and initialize the message source.
     *
     * @param theMessageSource the message source
     */
    @Autowired
    public I18n(MessageSource theMessageSource) {
        messageSource = theMessageSource;
    }

    /**
     * Get a message from the message source.
     *
     * @param key The key of the message.
     * @return The message.
     */
    public static String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * Get a message from the message source.
     *
     * @param key The key of the message.
     * @param args The arguments to replace in the message.
     * @return The message.
     */
    public static String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    /**
     * Get a message from the message source.
     *
     * @param key The key of the message.
     * @param args The arguments to replace in the message.
     * @param defaultMessage The default message.
     * @return The message.
     */
    public static String getMessage(String key, String defaultMessage, Object... args) {
        return messageSource.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale());
    }

}
