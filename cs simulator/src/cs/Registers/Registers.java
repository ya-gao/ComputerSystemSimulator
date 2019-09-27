package Registers;

/*
 *
 * @author Ya
 */

/*
 * The registers are implemented in Registers class. The indexing of GPR and IX are decimal numbers,
 * and the content of all the registers are in the format of Binary String.
 * Functions set<RegisterName> and get<RegisterName> are implemented for each type of register.
 */
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

    // Length of different types of registers
    static int LengthLimit16Bit = 16;
    static int LengthLimit12Bit = 12;
    static int LengthLimit4Bit = 4;

    static String sixteenBit = "0000000000000000";
    static String twelveBit = "000000000000";
    static String fourBit = "0000";

    // flags to indicate the need for a GUI update, not using these at this point
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

    // Status of input instruction
    public String Valid = "Valid Register Number and Value";
    public String InvalidRegNum = "Invalid Register Number";
    public String Overflow = "The Value Causes Overflow";

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

        // give some random values to the registers
        GPR[0] = "0101101001010011";
        GPR[1] = "1010010110010110";
        GPR[2] = "01010100101010101";
        GPR[3] = "01010101010101100";
        XR[0] = "0101010110100101";
        XR[1] = "0101010101010110";
        XR[2] = "0101101011101001";
        PC = "011010111010";
        CC = "0110";
    }

    // Return the value in GPR if the register number is valid, otherwise return InvalidRegNum
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
        if (rNum >= 0 && rNum < GPR.length && value.length() <= LengthLimit16Bit) {
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
        if ((rNum < XR.length) && (value.length() <= LengthLimit16Bit)) {
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

    // Returns value of PC
    public String getPC() {
        return PC;
    }

    // Sets the PC with the value provided; Indicates an overflow if overflow occurs
    public String setPC(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit12Bit) {
            PC = value;
            PCUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // Returns value of CC
    public String getCC() {
        return CC;
    }

    // Sets the CC with the value provided; Indicates an overflow if overflow occurs
    public String setCC(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit4Bit) {
            CC = value;
            CCUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // Returns value of IR
    public String getIR() {
        return IR;
    }

    // Sets the IR with the value provided; Indicates an overflow if overflow occurs
    public String setIR(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit16Bit) {
            IR = value;
            IRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // Returns value of MAR
    public String getMAR() {
        return MAR;
    }

    // Sets the MAR with the value provided; Indicates an overflow if overflow occurs
    public String setMAR(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit16Bit) {
            MAR = value;
            MARUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // Returns value of MBR
    public String getMBR() {
        return MBR;
    }

    // Sets the MBR with the value provided; Indicates an overflow if overflow occurs
    public String setMBR(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit16Bit) {
            MBR = value;
            MBRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // Returns value of MSR
    public String getMSR() {
        return MSR;
    }

    // Sets the MSR with the value provided; Indicates an overflow if overflow occurs
    public String setMSR(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit16Bit) {
            MSR = value;
            MSRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

    // Returns value of MFR
    public String getMFR() {
        return MFR;
    }

    // Sets the MFR with the value provided; Indicates an overflow if overflow occurs
    public String setMFR(String value) {
        String status = Valid;
        if (value.length() <= LengthLimit4Bit) {
            MFR = value;
            MFRUpdateRequired = true;
        } else {
            status = Overflow;
        }
        return status;
    }

}
