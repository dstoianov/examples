package com.tipsandtricks.webelement.custom.element.elements;

import com.tipsandtricks.webelement.custom.element.core.ImplementedBy;
import com.tipsandtricks.webelement.custom.element.elements.impl.CheckBoxImpl;

/**
 * Created by dstoianov on 2014-12-26.
 */

@ImplementedBy(CheckBoxImpl.class)
public interface CheckBox extends Element {

    void toggle();

    void check();

    void uncheck();

    boolean isChecked();
}
