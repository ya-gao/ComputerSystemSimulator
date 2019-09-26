package Registers;

public class Registers {
    int numOfGPR = 4;
    int numOfXR = 3;

    String GPR[];   // GPR: general purpose registers
    String XR[];    // XR: Index Registers
    String PC;
    String CC;
    String MAR;
    String MBR;
    String MFR;
    String MSR;
    String IR;

    // max values
    static int LengthLimit16Bit = 16;
    static int LengthLimit12Bit = 12;
    static int LengthLimit4Bit = 4;

    static String sixteenBit = "0000000000000000";
    static String twelveBit = "000000000000";
    static String fourBit = "0000";

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

    public String Valid = "Valid Register Number and Value";
    public String InvalidRegNum = "Invalid Register Number";
    public String Overflow = "The Value causes Overflow";

    // Registers Constructor
    public Registers() {
        GPR = new String[numOfGPR];
        XR = new String[numOfXR];
        GPRUpdateRequired = new boolean[numOfGPR];
        XRUpdateRequired = new boolean[numOfXR];

        /* initialize the values in all the registers to 0
         * initialize all flags to false
         */
        for (int i = 0; i < GPR.length; i++) {
            GPR[i] = sixteenBit;
            GPRUpdateRequired[i] = false;
        }
        for (int i = 0; i < XR.length; i++) {
            XR[i] = sixteenBit;
            XRUpdateRequired[i] = false;
        }
        String PC = twelveBit;
        String CC = fourBit;
        String MAR = sixteenBit;
        String MBR = sixteenBit;
        String MFR = fourBit;
        String IR = sixteenBit;

        PCUpdateRequired = false;
        CCUpdateRequired = false;
        IRUpdateRequired = false;
        MARUpdateRequired = false;
        MBRUpdateRequired = false;
        MSRUpdateRequired = false;
        MFRUpdateRequired = false;

    }

    // return the value in GPR if the register number is valid, otherwise return InvalidRegNum
    public String getGPR(int rNum) {
        if (rNum >= 0 && rNum < numOfGPR) {
            return GPR[rNum];
        } else {
            return InvalidRegNum;
        }
    }

    /* Sets the GPR with the value provided if register number and value are valid;
     * Otherwise return status and print a message on the console
     */
    public String setGPR(int rNum, String value) {
        String status = Valid;
        if (rNum >= 0 && rNum < GPR.length && value.length() <= sixteenBit.length()) {
            // base case, store value in the register specified
            GPR[rNum] = value;
            GPRUpdateRequired[rNum] = true;
        } else if (rNum >= GPR.length) {
            // if register number is invalid, set status to InvalidRegNum
            status = InvalidRegNum;
        } else {
            // if value > max, set status to Overflow
            status = Overflow;
        }

        return status;
    }


    // return the value in XR if the register number is valid, otherwise return InvalidRegNum
    public String getXR(int rNum) {
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
    public String setXR(int rNum, String value) {
        String status = Valid;
        rNum = rNum - 1;
        if ((rNum < XR.length) && (value.length() <= sixteenBit.length())) {
            // base case, store value in the register specified
            XR[rNum] = value;
            XRUpdateRequired[rNum] = true;
        } else if (rNum >= XR.length) {
            // if register number is invalid, set status to InvalidRegNum
            status = InvalidRegNum;
        } else {
            // if value > max, set status to Overflow
            status = Overflow;
        }

        return status;
    }

    // PC getter, returns value of PC
    public String getPC() {
        return PC;
    }

    // PC setter, see GPR documentation
    public String setPC(String value) {
        String status = Valid;
        if (value.length() <= twelveBit.length()) {
            PC = value;
            PCUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // CC getter, returns value of CC
    public String getCC() {
        return CC;
    }

    // CC setter, see GPR documentation
    public String setCC(String value) {
        String status = Valid;
        if (value.length() <= fourBit.length()) {
            CC = value;
            CCUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // IR getter, returns value of IR
    public String getIR() {
        return IR;
    }

    // IR setter, see GPR documentation
    public String setIR(String value) {
        String status = Valid;
        if (value.length() <= sixteenBit.length()) {
            IR = value;
            IRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MAR getter, returns value of MAR
    public String getMAR() {
        return MAR;
    }

    // MAR setter, see GPR documentation
    public String setMAR(String value) {
        String status = Valid;
        if (value.length() <= sixteenBit.length()) {
            MAR = value;
            MARUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MBR getter, returns value of MBR
    public String getMBR() {
        return MBR;
    }

    // MBR setter, see GPR documentation
    public String setMBR(String value) {
        String status = Valid;
        if (value.length() <= sixteenBit.length()) {
            MBR = value;
            MBRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MSR getter, returns value of MSR
    public String getMSR() {
        return MSR;
    }

    // MSR setter, see GPR documentation
    public String setMSR(String value) {
        String status = Valid;
        if (value.length() <= sixteenBit.length()) {
            MSR = value;
            MSRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // MFR getter, returns value of MFR
    public String getMFR() {
        return MFR;
    }

    // MFR setter, see GPR documentation
    public String setMFR(String value) {
        String status = Valid;
        if (value.length() <= fourBit.length()) {
            MFR = value;
            MFRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

}
