package com.example.itx351.taskmanagerclient;
import java.io.Serializable;
import java.util.Vector;

public class SysInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    String osArch, osDataModle, osDescription;
    Long memTotal, memUsed, memFree;
    Long swapTotal, swapFree, swapUsed;
    double cpuSys, cpuUser, cpuCombined, cpuWait, cpuIdle;
    Vector<String> procList;
}
