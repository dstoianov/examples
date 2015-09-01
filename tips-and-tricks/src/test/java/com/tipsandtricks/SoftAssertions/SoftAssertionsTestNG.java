package com.tipsandtricks.SoftAssertions;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Map;

/**
 * Created by Funker on 22.08.2015.
 */
public class SoftAssertionsTestNG extends Assertion {

    // LinkedHashMap to preserve the order
    private Map<AssertionError, IAssert> m_errors = Maps.newLinkedHashMap();

    @Override
    public void executeAssert(IAssert a) {
        try {
            a.doAssert();
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
        }
    }

    public void assertAll() {
        try {
            if (!m_errors.isEmpty()) {
                StringBuilder sb = new StringBuilder();

                sb.append("\n\tThe following '").append(m_errors.size()).append("' assertions failed:\n");
                boolean first = true;
                int count = 1;
                for (Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet()) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append(",\n");
                    }
                    IAssert ia = ae.getValue();
                    sb.append("\t\t").append(count++).append(")");
                    if (ia.getMessage() != null) {
                        sb.append(" [").append(ia.getMessage()).append("]");
                    }
                    sb.append(" expected: '").append(ia.getExpected()).append("' but was: '").append(ia.getActual()).append("'");
                }
                throw new AssertionError(sb.toString());
            }
        } finally {
            m_errors.clear();
        }

    }
}
