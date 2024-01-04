package com.fpetrola.z80.instructions;

/**
 * This condition is always true
 *
 * @author fpreto
 */
public class ConditionAlwaysTrue implements Condition {

    @Override
    public boolean conditionMet() {
        return true;
    }

    @Override
    public String toString() {
        return "";
    }
}
