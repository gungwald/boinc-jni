package edu.berkeley.boinc.jni;

import java.io.Serializable;

public class Boinc implements Serializable {

    /**
     * Necessary for Serializable interface implementation.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Lazy and thread-safe. A getInstance method would not be thread-safe
     * unless it was synchronized. But that makes it 100 times slower.
     */
    public static Boinc SINGLETON = new Boinc();
    
    private Boinc() {
        // Thwart instantiation of multiple instances.
    }
    
    private Object readResolve() {
        // Avoid multiple instances that would otherwise be possible by
        // de-serializing multiple times.
        return SINGLETON;
    }
    
    /**
     * Performs initialization with a default set of options. Can be used for
     * single-threaded applications.
     */
    public native void init() throws BoincException;
    
    /**
     * Performs initialization with a given set of options. Used with 
     * multi-threaded applications.
     * 
     * @param options   Specifies configuration of application
     * 
     * @return 0 for success or an error code
     */
    public native int init(ApplicationOptions options);
    
    /**
     * When the application has completed it must call this.
     * Status is nonzero if an error was encountered. This call does not return.
     * Do not call exit(0). If you do, BOINC will restart the app, which is probably not what you want. 
     * @param status Non-zero if an error was encountered
     * @return
     */
    public native int finish(int status);
    
    /**
     *  Alternatively, if you want to show a message to the user (e.g. because of an error condition that the user can remedy) use
     * If is_notice is true, the message will be shown as a notice in the GUI (works with 7.5+ clients; for others, no message will be shown). 
     * @param status
     * @param message
     * @param isNotice
     * @return
     */
    public native int finish(int status, String message, boolean isNotice);
    
    /**
     * Applications that use named input or output files must call this.
     * @param logicalName
     * @return Physical name
     */
    public native String resolveFileName(String logicalName);
    
    /**
     * This is not a good method signature for JNI. Need to figure out something better.
     * @param data
     * @return
     */
    public native int getApplicationInitData(ApplicationInitData data);
    public native int parseInitDataFile();
    public native int sendTrickleUp(String variety, String text);
    public native int setMinCheckpointPeriod(int minCheckpointPeriod);
    public native int checkpointCompleted();
    public native int reportFractionDone(double fraction);
    public native int suspendOtherActivities();
    public native int resumeOtherActivities();
    public native int reportAppStatus(double cpuTime, double checkpointCpuTime, double fractionDone);
    public native boolean isTimeToCheckpoint();
}
