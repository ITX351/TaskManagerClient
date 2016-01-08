package com.example.itx351.taskmanagerclient.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    public static void clear() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.PID, item);
    }

//    private static DummyItem createDummyItem(int position) {
//        return new DummyItem("ImageName " + position, String.valueOf(position), "SessionName " + position, "Session " + position, "MemUsage " + position);
//    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String ImageName;
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
