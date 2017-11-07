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

    private boolean workerThreadPriorityNormal = false;
    private boolean mainProgram = true;
    private boolean heartbeatEnabled = true;
    private boolean messageHandlingEnabled = true;
    private boolean statusReportingEnabled = true;
    private boolean actionEnabled = true;
    private boolean multiThreaded = false;
    private boolean creatorOfSubprocesses = false;
    
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
     * @throws BoincException If the native boinc function call failed
     */
    public void init() throws BoincException {
    	init(workerThreadPriorityNormal, mainProgram, heartbeatEnabled, messageHandlingEnabled, statusReportingEnabled, actionEnabled, multiThreaded, creatorOfSubprocesses);
    }

    /**
     * Private flattened out version if init(options) for JNI efficiency.
     * 
     * @param normalThreadPriority
     * @param mainProgram
     * @param checkHeartbeat
     * @param handleProcessControl
     * @param sendStatusMessages
     * @param directProcessAction
     * @param multiThread
     * @param multiProcess
     * @throws BoincException
     */
    private native void init(boolean normalThreadPriority, boolean mainProgram, boolean checkHeartbeat, boolean handleProcessControl, boolean sendStatusMessages, boolean directProcessAction, boolean multiThread, boolean multiProcess) throws BoincException;

    /**
     * When the application has completed it must call this. Status is nonzero
     * if an error was encountered. This call does not return. Do not call
     * exit(0). If you do, BOINC will restart the app, which is probably not
     * what you want.
     * 
     * @param status Non-zero if an error was encountered
     * @return
     */
    public native void finish(int status) throws BoincException;

    /**
     * Alternatively, if you want to show a message to the user (e.g. because of
     * an error condition that the user can remedy) use If is_notice is true,
     * the message will be shown as a notice in the GUI (works with 7.5+
     * clients; for others, no message will be shown).
     * 
     * @param status
     * @param message
     * @param isNotice
     * @return
     */
    public native void finish(int status, String message, boolean isNotice) throws BoincException;

    /**
     * Applications that use named input or output files must call this.
     * 
     * @param logicalName
     * @return Physical name
     */
    public native String resolveFileName(String logicalName);

    /**
     * This is not a good method signature for JNI. Need to figure out something
     * better.
     * 
     * @param data
     * @return
     */
    public native int getApplicationInitData(ApplicationInitData data);

    public native int parseInitDataFile();

    public native int sendTrickleUp(String variety, String text);

    public native int reportFractionDone(double fraction);

    public native int suspendOtherActivities();

    public native int resumeOtherActivities();

    public native int reportAppStatus(double cpuTime, double checkpointCpuTime, double fractionDone);

    /**
     * Long jobs may want to periodically write the current state of the
     * computation to disk. This is known as checkpointing. The checkpoint file
     * must include everything required to restart the computation at the same
     * point. On startup, the application reads the checkpoint file to determine
     * where to begin computation. If the BOINC client quits or exits, the
     * computation can be restarted from the most recent checkpoint.
     * 
     * Most applications are able to checkpoint only at specific points, e.g. at
     * the end the outer loop. When the application is at such a point, it must
     * call this method.
     * 
     * @return true If it is time to checkpoint
     */
    public native boolean isTimeToCheckpoint();

    public native void setMinimumCheckpointPeriod(int minCheckpointPeriodInSeconds);

    public native void reportThatCheckpointIsComplete();

    public native void beginCriticalSection();

    public native void endCriticalSection();

    public native void registerTimerCallback(TimerHandler timerHandler);

    /**
     * If an application is unable to run because of a transient condition, it
     * should call
     * 
     * int boinc_temporary_exit(int delay, const char* reason=NULL, bool
     * is_notice=false);
     * 
     * This will exit the application and tell the BOINC client to restart it
     * again in at least delay seconds. (This works with 6.10.25+ client; on
     * other clients, it will potentially restart immediately). Reason, if
     * supplied, is shown to the user as the explanation for the deferral. If
     * is_notice is true, it's shown as a notice (this should be used only for
     * conditions that the user can fix).
     * 
     * Examples:
     * 
     * A GPU application fails to allocate GPU RAM because non-BOINC programs
     * have GPU RAM allocated. The Vboxwrapper sees that an incompatible version
     * of VirtualBox is installed. In this case "is_notice" would be true,
     * because the user can update VirtualBox.
     */
    public native void exitAndRestart(int delaySeconds, String reason, boolean isNotice);
}
