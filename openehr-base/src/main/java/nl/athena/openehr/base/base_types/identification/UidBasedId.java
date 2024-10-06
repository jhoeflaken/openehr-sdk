package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * Abstract model of UID-based identifiers consisting of a root part and an optional extension; lexical form: root
 * '::' extension.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_uid_based_id_class">UidBasedId</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class UidBasedId extends ObjectId {

    @JsonIgnore
    @XmlTransient
    private final Uid root;

    @JsonIgnore
    @XmlTransient
    private final String extension;

    public UidBasedId(String theValue) {
        super(theValue);

        final int index = value.indexOf("::");
        root = Uuid.of(value.substring(0, index));
        if (index > 0) {
            extension = value.substring(index + 2);
        } else {
            extension = null;
        }
    }

    public Uid root() {
        return root;
    }

    public String extension() {
        return extension;
    }

    public Boolean hasExtension() {
        return StringUtils.isNotBlank(extension);
    }

}
