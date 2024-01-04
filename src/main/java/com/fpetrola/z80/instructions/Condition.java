package com.fpetrola.z80.instructions;

/**
 * Condition to be used on opcodes.
 *
 * @author fpreto
 */
public interface Condition {

    /**
     * Return true if condition is met.
     *
     * @return true if condition is met
     */
    boolean conditionMet();

}
