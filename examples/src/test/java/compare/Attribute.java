package compare;

//import com.google.common.collect.ComparisonChain;

import com.google.common.collect.ComparisonChain;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author radler
 *         Class Description ...
 */
public class Attribute implements Comparable<Attribute> {

    private String type;
    private String value;

    public Attribute(String type, String value) {
        this.type = type;
        this.value = value;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (!type.equals(attribute.type)) return false;
        if (!value.equals(attribute.value)) return false;

        return true;
    }


    @Override
    public String toString() {
        return "Attribute [type=" + type + ", value=" + value + "]";
    }

    public boolean equals1(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }


    @Override
    public int compareTo(Attribute that) {
        return ComparisonChain.start()
                .compare(this.type, that.type)
                .compare(this.value, that.value)
                .result();
    }

}


