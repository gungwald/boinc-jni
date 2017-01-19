/**
 * The parameters of this JNI implementation of the BOINC API are as follows:
 * <ol>
 * <li>
 * Maintain the work flow of the native C API. Taking on too much change
 * at one time is bad.
 * </li>
 * <li>
 * Make the methods names and structure names more Java-like because it
 * will be Java programmers that use the interface, not C programmers.
 * </li>
 * </ol>
 */
package edu.berkeley.boinc.api;