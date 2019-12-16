package Memory;

import java.util.LinkedList;

/**
 * @author Zixiang
 */

public class Cache {//Cache is composed of many cachelines,and in this case, the number of cachelines should be 16
    int address;
    int value;
    public class CacheLine {

        int tag;//indicates where the data stores in physical memory
        int data;

        public CacheLine(int tag, int data) {
            this.tag = tag;
            this.data = data;
        }

        public int getTag() {
            return this.tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    LinkedList<CacheLine> cacheLines;//LinkedList is a data structure that satisfies FIFO

    public Cache() {
        this.cacheLines = new LinkedList<CacheLine>();
    }

    public LinkedList<CacheLine> getCacheLines() {
        return cacheLines;
    }

    public void add(int address, int value) {
        if (this.cacheLines.size() >= 16) {
            this.cacheLines.removeLast();
        }
        this.cacheLines.addFirst(new CacheLine(address, value));
    }
}
