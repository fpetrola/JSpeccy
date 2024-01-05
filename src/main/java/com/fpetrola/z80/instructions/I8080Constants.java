package com.fpetrola.z80.instructions;
public interface I8080Constants
{

	/** Result is signed (negative) */
	public static final int FLAG_S  = 0x80;

	/**  Result is 0 */
	public static final int FLAG_Z  = 0x40;

	/** Reserved copy bit 5 of result */
	public static final int FLAG_5  = 0x20;

	/** Half carry */
	public static final int FLAG_H  = 0x10;

	/** Reserved copy bit 3 of result */
	public static final int FLAG_3  = 0x08;

	/** Parity / overflow */
	public static final int FLAG_PV = 0x04;

	/** Set if last aritmetis is SUB,SBC,DEC  */
	public static final int FLAG_N  = 0x02;

	/** Carry flag  */
	public static final int FLAG_C  = 0x01;
}