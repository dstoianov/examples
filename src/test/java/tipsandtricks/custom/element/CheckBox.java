package tipsandtricks.custom.element;

/**
 * Created by dstoianov on 2014-12-26.
 */
public interface CheckBox extends Element {
    void toggle();

    void check();

    void uncheck();

    boolean isChecked();
}
