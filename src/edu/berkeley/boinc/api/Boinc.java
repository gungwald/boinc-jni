package edu.berkeley.boinc.api;

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
     * 
     * @return 0 for success or an error code
     */
    public native int init();
    
    /**
     * Performs initialization with a given set of options. Used with 
     * multi-threaded applications.
     * 
     * @param options   Specifies configuration of application
     * 
     * @return 0 for success or an error code
     */
    public native int init(ApplicationOptions options);
    
    public native int finish(int status);
    public native int getInitData(AppInitData data);
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
