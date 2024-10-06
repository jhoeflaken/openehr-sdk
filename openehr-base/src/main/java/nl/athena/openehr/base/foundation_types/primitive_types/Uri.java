package nl.athena.openehr.base.foundation_types.primitive_types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import nl.athena.openehr.base.Messages;
import nl.athena.openehr.util.i18n.I18n;

import java.net.URI;
import java.util.regex.Pattern;

/**
 * A kind of String constrained to obey the syntax of RFC 3986. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_uri_class">
 *     Uri</a> class.
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
public class Uri {

    @JsonValue
    @XmlValue
    private String value;

    @JsonIgnore
    @XmlTransient
    private String scheme;

    @JsonIgnore
    @XmlTransient
    private String user;

    @JsonIgnore
    @XmlTransient
    private String password;

    @JsonIgnore
    @XmlTransient
    private String host;

    @JsonIgnore
    @XmlTransient
    private String port;

    @JsonIgnore
    @XmlTransient
    private String path;

    @JsonIgnore
    @XmlTransient
    private String query;

    @JsonIgnore
    @XmlTransient
    private String fragment;

    /**
     * The URI RFC3986 pattern.
     */
    private static final Pattern URI_RFC3986 = Pattern.compile("^" +
            "(?:(?<scheme>[a-zA-Z][a-zA-Z\\d+-.]*):)?" + // protocol
            "(?://" +
            "(?:(?<user>[a-zA-Z\\d\\-._~!$&'()*+,;=%]*)(?::(?<pass>[a-zA-Z\\d\\-._~!$&'()*+,;=:%]*))?@)?" + // userinfo
            "(?<host>[a-zA-Z\\d-.%]+|\\[[a-fA-F\\d:.]+])?" + // host or IP
            "(?::(?<port>\\d*))?" + // port
            ")?" +
            "(?<path>(?:/[a-zA-Z\\d\\-._~!$&'()*+,;=:@%]*)*)" + // path
            "(?:\\?(?<query>[a-zA-Z\\d\\-._~!$&'()*+,;=:@%/?]*))?" + // query
            "(?:#(?<fragment>[a-zA-Z\\d\\-._~!$&'()*+,;=:@%/?]*))?" + // fragment
            "$"
    );

    /**
     * Creates a new Uri with the given value.
     *
     * @param theValue The RFC3986 compliant URI string.
     * @return The created Uri.
     */
    public static Uri of(@Nonnull String theValue) {
        return new Uri(theValue);
    }

    /**
     * Creates a new Uri with the given value.
     *
     * @param theValue The RFC3986 compliant URI string.
     */
    public Uri(@Nonnull String theValue) {
        value = theValue;
        parseUri(theValue);
    }

    /**
     * Parses the URI string so that the individual components can be accessed.
     *
     * @param theValue The URI string to parse.
     */
    private void parseUri(@Nonnull String theValue) {
        var matcher = URI_RFC3986.matcher(theValue);
        if (matcher.matches()) {
            scheme = matcher.group("scheme");
            user = matcher.group("user");
            password = matcher.group("pass");
            host = matcher.group("host");
            port = matcher.group("port");
            path = matcher.group("path");
            query = matcher.group("query");
            fragment = matcher.group("fragment");
        } else {
            throw new IllegalArgumentException(I18n.getMessage(Messages.INVALID_URI, theValue));
        }
    }

    /**
     * Returns the URI as a java.net.URI object.
     *
     * @return The URI as a java.net.URI object.
     */
    public URI toURI() {
        return URI.create(value);
    }

}
