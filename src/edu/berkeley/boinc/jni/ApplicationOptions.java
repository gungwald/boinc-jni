package edu.berkeley.boinc.jni;

/**
 * Used to tell the BOINC client what kind of application is running.
 * <p>
 * Maps to C API type: <pre>BOINC_OPTIONS</pre>
 */
public class ApplicationOptions {
    
    private boolean workerThreadPriorityNormal;
    private boolean mainProgram;
    private boolean heartbeatTimeoutCheckEnabled;
    private boolean autoHandleProcessControlMessagesEnabled;
    private boolean autoSendStatusMessagesEnabled;
    private boolean autoHandleCommunicationsFailureEnabled;
    private boolean applicationMultiThreaded;
    private boolean applicationMultiProcess;
    
    /**
     * Constructs an instance and sets all properties to their default
     * values.
     * @throws BoincException 
     */
    public ApplicationOptions() throws BoincException {
        super();
        reset();
    }
    
    /**
     * Sets all properties to their default values.
     */
    public final native void reset() throws BoincException;

    /**
     * Checks the disposition of the property.
     * 
     * @see #setWorkerThreadPriorityNormal(boolean)
     * @return  true if the property is set
     */
    public boolean isWorkerThreadPriorityNormal() {
        return workerThreadPriorityNormal;
    }

    /**
     * Run worker thread at normal thread priority on Win. 
     * (default is idle priority)
     * <p>
     * Maps to C API field: <pre>BOINC_OPTIONS.normal_thread_priority</pre>
     */
    public void setWorkerThreadPriorityNormal(boolean workerThreadPriorityNormal) {
        this.workerThreadPriorityNormal = workerThreadPriorityNormal;
    }

    public boolean isMainProgram() {
        return mainProgram;
    }

    /**
     * this is the main program, so
     * - lock a lock file in the slot directory
     * - write finish file on successful boinc_finish()
     */
    public void setMainProgram(boolean mainProgram) {
        this.mainProgram = mainProgram;
    }

    public boolean isHeartbeatTimeoutCheckEnabled() {
        return heartbeatTimeoutCheckEnabled;
    }

    /**
     * check for timeout of heart beats from the client;
     * action is determined by direct_process_action (see below)
     * <pre>BOINC_OPTIONS.check_heartbeat</pre>
     */
    public void setHeartbeatTimeoutCheckEnabled(boolean heartbeatTimeoutCheckEnabled) {
        this.heartbeatTimeoutCheckEnabled = heartbeatTimeoutCheckEnabled;
    }

    public boolean isAutoHandleProcessControlMessagesEnabled() {
        return autoHandleProcessControlMessagesEnabled;
    }

    /**
     * Specifies whether the runtime system should read 
     * suspend/resume/quit/abort messages from the client.
     * Whether to take action is determined by direct_process_action (see below).
     * <p>
     * Maps to C API field:
     * <pre>BOINC_OPTIONS.handle_process_control</pre>
     */
    public void setAutoHandleProcessControlMessagesEnabled(boolean value) {
        this.autoHandleProcessControlMessagesEnabled = value;
    }

    /**
     * Gets the property value.
     * 
     * @see #setAutoHandleProcessControlMessagesEnabled(boolean)
     * @return  Property value
     */
    public boolean isAutoSendStatusMessagesEnabled() {
        return autoSendStatusMessagesEnabled;
    }

    /**
     * whether runtime system should send CPU time / fraction done messages
     * <pre>BOINC_OPTIONS.send_status_msgs</pre>
     */
    public void setAutoSendStatusMessagesEnabled(boolean autoSendStatusMessagesEnabled) {
        this.autoSendStatusMessagesEnabled = autoSendStatusMessagesEnabled;
    }

    public boolean isAutoHandleCommunicationsFailureEnabled() {
        return autoHandleCommunicationsFailureEnabled;
    }

    /**
     * If heart beat fail, or get process control message, take
     * direct action (exit, suspend, resume).
     * Otherwise just set flag in BOINC status
     * This is true for regular applications, false for wrappers
     * <pre>BOINC_OPTIONS.direct_process_action</pre>
     */
    public void setAutoHandleCommunicationsFailureEnabled(boolean autoHandleCommunicationsFailureEnabled) {
        this.autoHandleCommunicationsFailureEnabled = autoHandleCommunicationsFailureEnabled;
    }

    public boolean isApplicationMultiThreaded() {
        return applicationMultiThreaded;
    }

    /**
     * set this if application creates threads in main process
     * <pre>BOINC_OPTIONS.multi_thread</pre>
     */
    public void setApplicationMultiThreaded(boolean applicationMultiThreaded) {
        this.applicationMultiThreaded = applicationMultiThreaded;
    }

    public boolean isApplicationMultiProcess() {
        return applicationMultiProcess;
    }

    /**
     * set this if application creates subprocesses.
     * <pre>BOINC_OPTIONS.multi_process</pre>
     */
    public void setApplicationMultiProcess(boolean applicationMultiProcess) {
        this.applicationMultiProcess = applicationMultiProcess;
    }
    
}
