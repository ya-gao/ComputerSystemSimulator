package Registers;

public class Registers {
    int numOfGPR = 4;
    int numOfXR = 3;

    int GPR[];   // GPR: general purpose registers
    int XR[];    // XR: Index Registers
    int PC;
    int CC;
    int MAR;
    int MBR;
    int MFR;
    int MSR;
    int IR;

    // max values
    static int sixteenBitMax = Integer.parseInt("FFFF", 16);
    static int twelveBitMax = Integer.parseInt("FFF", 16);
    static int fourBitMax = Integer.parseInt("F", 16);

    // flags to indicate the need for a GUI update
    public boolean[] GPRUpdateRequired;
    public boolean[] XRUpdateRequired;
    public boolean[] FRUpdateRequired;
    public boolean PCUpdateRequired;
    public boolean CCUpdateRequired;
    public boolean IRUpdateRequired;
    public boolean MARUpdateRequired;
    public boolean MBRUpdateRequired;
    public boolean MSRUpdateRequired;
    public boolean MFRUpdateRequired;

    public int Valid = 0;
    public int InvalidRegNum = 1;
    public int Overflow = 2;

    // Registers Constructor
    public Registers() {
        GPR = new int[numOfGPR];
        XR = new int[numOfXR];
        GPRUpdateRequired = new boolean[numOfGPR];
        XRUpdateRequired = new boolean[numOfXR];

        /* initialize the values in all the registers to zero
        initialize all flags to false
         */
        for (int i = 0; i < GPR.length; i++) {
            GPR[i] = 0;
            GPRUpdateRequired[i] = false;
        }
        for (int i = 0; i < XR.length; i++) {
            XR[i] = 0;
            XRUpdateRequired[i] = false;
        }
        int PC = 0;
        int CC = 0;
        int MAR = 0;
        int MBR = 0;
        int MFR = 0;
        int IR = 0;

        PCUpdateRequired = false;
        CCUpdateRequired = false;
        IRUpdateRequired = false;
        MARUpdateRequired = false;
        MBRUpdateRequired = false;
        MSRUpdateRequired = false;
        MFRUpdateRequired = false;

    }

    // return the value in GPR if the register number is valid, otherwise return InvalidRegNum
    public int getGPR(int rNum) {
        if (rNum >= 0 && rNum < GPR.length) {
            return GPR[rNum];
        } else {
            return InvalidRegNum;
        }
    }

    /* Sets the GPR with the value provided if register number and value are valid;
     * Otherwise return status and print a message on the console
     */
    public int setGPR(int rNum, int value) {
        int status = Valid;
        int max = sixteenBitMax;
        if (rNum >= 0 && rNum < GPR.length && value <= max) {
            // base case, store value in the register specified
            GPR[rNum] = value;
            GPRUpdateRequired[rNum] = true;
        } else if (rNum >= GPR.length) {
            // if register number is invalid, set status to InvalidRegNum
            status = InvalidRegNum;
        } else {
            // if value > max, set status to Overflow and store value in specified register
            GPR[rNum] = value & sixteenBitMax;
            GPRUpdateRequired[rNum] = true;
            status = Overflow;
        }

        return status;
    }


    // return the value in XR if the register number is valid, otherwise return InvalidRegNum
    public int getXR(int rNum) {
        rNum = rNum - 1;
        if (rNum < 0 || rNum > XR.length) {
            return InvalidRegNum;
        } else {
            return XR[rNum];
        }
    }

    /* Sets the XR with the value provided if register number and value are valid;
     * Otherwise return status and print a message on the console
     */
    public int setXR(int rNum, int value) {
        int status = Valid;
        int max = twelveBitMax;
        rNum = rNum - 1;
        if ((rNum < XR.length) && (value <= max)) {
            // base case, store value in the register specified
            XR[rNum] = value;
            XRUpdateRequired[rNum] = true;
        } else if (rNum >= XR.length) {
            // if register number is invalid, set status to InvalidRegNum
            status = InvalidRegNum;
        } else {
            // if value > max, set status to Overflow and store value in specified register
            value = value & sixteenBitMax;
            XR[rNum] = value;
            XRUpdateRequired[rNum] = true;
            status = Overflow;
        }

        return status;
    }

    // PC getter, returns value of PC
    public int getPC() {
        return PC;
    }

    // PC setter, see GPR documentation
    public int setPC(int value) {
        int status = Valid;
        int max = twelveBitMax;
        if (value <= max) {
            PC = value;
            PCUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // CC getter, returns value of CC
    public int getCC() {
        return CC;
    }

    // CC setter, see GPR documentation
    public int setCC(int value) {
        int status = Valid;
        int max = fourBitMax;
        if (value <= max) {
            CC = value;
            CCUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // IR getter, returns value of IR
    public int getIR() {
        return IR;
    }

    // IR setter, see GPR documentation
    public int setIR(int value) {
        int status = Valid;
        int max = sixteenBitMax;
        if (value <= max) {
            IR = value;
            IRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MAR getter, returns value of MAR
    public int getMAR() {
        return MAR;
    }

    // MAR setter, see GPR documentation
    public int setMAR(int value) {
        int status = Valid;
        int max = sixteenBitMax;
        if (value <= max) {
            MAR = value;
            MARUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MBR getter, returns value of MBR
    public int getMBR() {
        return MBR;
    }

    // MBR setter, see GPR documentation
    public int setMBR(int value) {
        int status = Valid;
        int max = sixteenBitMax;
        if (value <= max) {
            MBR = value;
            MBRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MSR getter, returns value of MSR
    public int getMSR() {
        return MSR;
    }

    // MSR setter, see GPR documentation
    public int setMSR(int value) {
        int status = Valid;
        int max = sixteenBitMax;
        if (value <= max) {
            MSR = value;
            MSRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MFR getter, returns value of MFR
    public int getMFR() {
        return MFR;
    }

    // MFR setter, see GPR documentation
    public int setMFR(int value) {
        int status = Valid;
        int max = fourBitMax;
        if (value <= max) {
            MFR = value;
            MFRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

}
