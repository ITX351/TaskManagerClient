package com.example.itx351.taskmanagerclient.dummy;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    public static class DummyItem {
        public final String ImageName; //每个进程有五种属性
        public final String PID;
        public final String SessionName;
        public final String Session;
        public final String MemUsage;

        public DummyItem(String _ImageName, String _PID, String _SessionName, String _Session, String _MemUsage) {
            ImageName = _ImageName;
            PID = _PID;
            SessionName = _SessionName;
            Session = _Session;
            MemUsage = _MemUsage;
        }
    }
}
