/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2007
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.done.entity;

import java.io.Serializable;

public class Item implements Serializable {

    private boolean nullEnabled = false;

    private String key = null;

    private String value = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isNullEnabled() {
        return nullEnabled;
    }

    public void setNullEnabled(boolean nullEnabled) {
        this.nullEnabled = nullEnabled;
    }

}
