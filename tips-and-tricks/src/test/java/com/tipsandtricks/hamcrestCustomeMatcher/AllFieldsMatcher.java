package com.tipsandtricks.hamcrestCustomeMatcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.lang.reflect.Field;

/**
 * Created by ozapolska on 09.11.2014.
 */
public class AllFieldsMatcher extends BaseMatcher<SomeObject> {

    private final SomeObject expected;
    private StringBuffer errorLog = new StringBuffer();

    private AllFieldsMatcher(SomeObject expected) {
        this.expected = expected;
    }

    public static Matcher<SomeObject> samePropertyValuesAs(SomeObject expectedSomeObject) {
        return new AllFieldsMatcher(expectedSomeObject);
    }

    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendDescriptionOf(matcher);
            throw new AssertionError(description.toString());
        }
    }

    @Override
    public boolean matches(Object item) {
        if (expected == item) return true;
        if (item == null || expected.getClass() != item.getClass()) return false;

        SomeObject actual = (SomeObject) item;

        for (Field f : expected.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                matches(f, f.get(expected), (f.get(actual)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return errorLog.length() == 0;
    }

    private void matches(Field f, Object expected, Object actual) {
        if (expected != null ? !expected.equals(actual) : actual != null) {
            errorLog.append("\n");
            errorLog.append(f.getName() + "\n");
            errorLog.append("Expected: " + (expected != null ? expected : "null") + "\n");
            errorLog.append("but was: " + (actual != null ? actual : "null") + "\n");
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(errorLog.toString());
    }

    @Override
    public void describeMismatch(Object item, Description description) {
    }
}
