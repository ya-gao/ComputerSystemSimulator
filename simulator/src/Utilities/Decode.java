package Utilities;

import Instruction.Arithmetic;
import Instruction.IO_Operation;
import Instruction.LoadStore;
import Instruction.Logical;
import Instruction.ShiftRotate;
import Instruction.Transfer;
import Instruction.Floating;
import Registers.Registers;
import Utilities.EffectiveAddress.*;
import Memory.*;

import javax.swing.JOptionPane;

import cs.simulator.simulator;

import javax.swing.JFrame;

public class Decode {//to decode an instruction written in English into binary code
    Registers r;
    Memory m;
    LoadStore ls;
    Transfer t;
    Arithmetic a;
    Logical l;
    IO_Operation i;
    ShiftRotate s;
    Floating f;

    public Decode(Registers reg, Memory mem) {
        r = reg;
        m = mem;
    }

    public void decodeForLoadStore(String instruction) throws Exception { // Decoding method for Load and Store

        String opCode = instruction.substring(0, 3);      //split the string into sub parts using substring method
        String GPR = instruction.substring(4, 5);
        String IX = instruction.substring(6, 7);
        String I = instruction.substring(8, 9);
        String Address = instruction.substring(10);

        switch (opCode) {                        //Checking for opCode value and setting the value to it because opCode values are predefined
            case "LDR":
                opCode = "000001";
                break;
            case "STR":
                opCode = "000010";
                break;
            case "LDA":
                opCode = "000011";
                break;
            case "LDX":
                opCode = "101001";
                break;
            case "STX":
                opCode = "101010";
                break;

            default:
                break;
        }

    /*    switch (GPR) {                      //Checking for register value
            case "0":
                GPR = "00";
                break;
            case "1":
                GPR = "01";
                break;
            case "2":
                GPR = "10";
                break;
            case "3":
                GPR = "11";
                break;
            default:
                break;
        } */


        // int decimalAddress = Integer.parseInt(Address);
        Address = Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r, m);
        Address = String.valueOf(Integer.parseInt(Address));
        System.out.println("Address:" + Address);
        // System.out.println(Address);
        // System.out.println(I);
        System.out.println("working");

        String[] instructionArray = {opCode, GPR, IX, I, Address};
        // System.out.println(instructionArray[0]);
        ls = new LoadStore(r, m, instructionArray);
        if (opCode == "000001") //ldr
        {
            System.out.println("ldr");
            ls.LDR();

        } else if (opCode == "000010") //str
        {
            System.out.println("STR");
            ls.STR();
        } else if (opCode == "000011") //lda
        {
            System.out.println("LDA");
            ls.LDA();
        } else if (opCode == "101001") //ldx
        {
            System.out.println("LDX");
            ls.LDX();
        } else if (opCode == "101010") //stx
        {
            System.out.println("STX");
            ls.STX();
        }

    }

    public void decodeForArithmetic(String instruction) throws Exception {
        String opCode = instruction.substring(0, 3);      //split the string into sub parts using substring method
        String GPR = instruction.substring(4, 5);

     /*   switch (GPR) {                      //Checking for register value
            case "0":
                GPR = "00";
                break;
            case "1":
                GPR = "01";
                break;
            case "2":
                GPR = "10";
                break;
            case "3":
                GPR = "11";
                break;
            default:
                break;
        }*/

        switch (opCode) {
            case "AMR": {
                opCode = "000100";
                String IX = instruction.substring(6, 7);
                String I = instruction.substring(8, 9);
                String Address = instruction.substring(10);
                //  int decimalAddress = Integer.parseInt(Address);
                //  String strBinaryAddress = DataTypeConvert.intToString(decimalAddress, 6);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                a = new Arithmetic(r, m, instructionArray);
                a.AMR(instructionArray);
                break;
                //return instructionArray;
            }
            case "SMR": {
                opCode = "000101";
                String IX = instruction.substring(6, 7);
                String I = instruction.substring(8, 9);
                String Address = instruction.substring(10);
                // int decimalAddress = Integer.parseInt(Address);
                // String strBinaryAddress = DataTypeConvert.intToString(decimalAddress, 6);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                a = new Arithmetic(r, m, instructionArray);
                a.SMR(instructionArray);
                break;
            }
            case "AIR": {
                opCode = "000110";
                String immediate = instruction.substring(6);
                String[] instructionArray = {opCode, GPR, immediate};
                a = new Arithmetic(r, m, instructionArray);
                a.AIR(instructionArray);
                break;
            }
            case "SIR": {
                opCode = "000111";
                String immediate = instruction.substring(6);
                String[] instructionArray = {opCode, GPR, immediate};
                System.out.println("Welcome SIR");
                a = new Arithmetic(r, m, instructionArray);
                a.SIR(instructionArray);
                break;
            }
            default:
                break;
        }


// if meets the condition, set PC to EA; otherwise increment PC by 1
    }

    public void decodeForTransfer(String instruction) throws Exception {
        String opCode = instruction.substring(0, 3);      //split the string into sub parts using substring method
        String GPR, IX, I, Address, cc;

        switch (opCode) {
            case "JZ ": {
                System.out.println("jz:" + opCode);
                opCode = "001010";
                GPR = instruction.substring(4, 5);
                IX = instruction.substring(6, 7);
                I = instruction.substring(8, 9);
                Address = instruction.substring(10);
                // System.out.println(opCode+""+""+GPR+""+IX+""+I+""+Address);
                // Address= Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r , m);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                t = new Transfer(r, m, instructionArray);
                t.JZ();
                break;
            }

            case "JNE": {
                System.out.println("jne:" + opCode);
                opCode = "001011";
                GPR = instruction.substring(4, 5);
                IX = instruction.substring(6, 7);
                I = instruction.substring(8, 9);
                Address = instruction.substring(10);
                // System.out.println(opCode+""+""+GPR+""+IX+""+I+""+Address);
                //Address= Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r , m);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                t = new Transfer(r, m, instructionArray);
                t.JNE();
                break;
            }

            case "JCC": {
                opCode = "001100";
                cc = instruction.substring(4, 5);
                IX = instruction.substring(6, 7);
                I = instruction.substring(8, 9);
                Address = instruction.substring(10);
                System.out.println("JCC");
                System.out.println(opCode + "" + "" + cc + "" + IX + "" + I + "" + Address);
                String[] instructionArray = {opCode, cc, IX, I, Address};
                t = new Transfer(r, m, instructionArray);
                t.JCC();

                break;
            }
            case "JMA": {
                System.out.println("jma:" + opCode);
                opCode = "001101";
                IX = instruction.substring(4, 5);
                System.out.println("IX:" + IX);
                I = instruction.substring(6, 7);
                System.out.println("I:" + I);
                Address = instruction.substring(8);
                System.out.println(Address);
                GPR = "";
                System.out.println("JMA");
                //System.out.println(Address);
                // System.out.println(opCode+""+IX+""+I+""+Address);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                t = new Transfer(r, m, instructionArray);
                t.JMA();
                break;

            }
            case "JSR": {
                opCode = "001110";
                IX = instruction.substring(4, 5);
                I = instruction.substring(6, 7);
                Address = instruction.substring(8);
                System.out.println(Address);
                GPR = "";
                System.out.println("JSR");
                //  System.out.println(opCode+""+IX+""+I+""+Address);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                t = new Transfer(r, m, instructionArray);
                t.JSR();
                break;
            }
            case "RFS": {
                System.out.println("RFS");
                opCode = "001111";
                Address = instruction.substring(4);
                System.out.println("Address:" + Address);
                String[] instructionArray = {opCode, Address};
                t = new Transfer(r, m, instructionArray);
                t.RFS();
                break;
            }
            case "SOB": {
                opCode = "010000";
                GPR = instruction.substring(4, 5);
                IX = instruction.substring(6, 7);
                I = instruction.substring(8, 9);
                Address = instruction.substring(10);
                //  Address= Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r , m);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                System.out.println("SOB:" + opCode + "" + "" + GPR + "" + IX + "" + I + "" + Address);
                t = new Transfer(r, m, instructionArray);
                t.SOB();
                break;
            }

            case "JGE": {
                opCode = "010001";
                GPR = instruction.substring(4, 5);
                IX = instruction.substring(6, 7);
                I = instruction.substring(8, 9);
                Address = instruction.substring(10);
                System.out.println("JGE:" + opCode + "" + "" + GPR + "" + IX + "" + I + "" + Address);
                //Address= Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r , m);
                String[] instructionArray = {opCode, GPR, IX, I, Address};
                t = new Transfer(r, m, instructionArray);
                t.JGE();
                break;
            }
            default:
                break;
        }

     /*   switch (GPR) {                      //Checking for register value
            case "0":
                GPR = "00";
                break;
            case "1":
                GPR = "01";
                break;
            case "2":
                GPR = "10";
                break;
            case "3":
                GPR = "11";
                break;
            default:
                break;
        }*/
     /*   switch (IX) {                         // checking for index register value
            case "0":
                IX = "00";
                break;
            case "1":
                IX = "01";
                break;
            case "2":
                IX = "10";
                break;
            case "3":
                IX = "11";
                break;
            default:
                break;
        }*/
        //int decimalAddress = Integer.parseInt(Address);
        //  Address= Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r , m);
        //  Address=String.valueOf(Integer.parseInt(Address));
        // String strBinaryAddress = DataTypeConvert.intToString(decimalAddress, 6);

        // return new String[]{opCode, GPR, IX, I, strBinaryAddress};
    }

    public void decodeForLogical(String instruction) throws Exception {
        String opCode = instruction.substring(0, 3);      //split the string into sub parts using substring method
        String RX = instruction.substring(4, 5);
        String RY = " ";
        switch (opCode) {
            case "MLT": {
                System.out.println("MLT");
                opCode = "010100";
                RY = instruction.substring(6, 7);
                // System.out.println(opCode+""+RX+""+RY);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.MLT();
                break;
            }
            case "MIN": {
                System.out.println("MIN");
                opCode = "100001";
                RY = instruction.substring(6, 7);
                // System.out.println(opCode+""+RX+""+RY);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.MIN();
                break;
            }
            case "DVD": {
                opCode = "010101";
                RY = instruction.substring(6, 7);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.DVD();

                break;
            }
            case "TRR": {
                System.out.println("TRR");
                opCode = "010110";
                RY = instruction.substring(6, 7);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.TRR();
                break;
            }
            case "AND": {
                System.out.println("AND");
                opCode = "010111";
                RY = instruction.substring(6, 7);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.AND();
                break;
            }
            case "ORR": {
                System.out.println("ORR");
                opCode = "011000";
                RY = instruction.substring(6, 7);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.ORR();
                break;
            }
             case "MUL": {
                System.out.println("MUL");
                opCode = "100010";
                RY = instruction.substring(6, 7);
                // System.out.println(opCode+""+RX+""+RY);
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.MUL();
                break;
            }
            case "NOT":
                System.out.println("NOT");
                opCode = "011001";
                RY = " "; //RY is not used here, so I set it as null to avoid null pointer.
                String[] instructionArray = {opCode, RX, RY};
                l = new Logical(r, m, instructionArray);
                l.NOT();
                break;
            default:
                break;
        }
        //return new String[]{opCode,RX,RY};
    }

    public void decodeForShiftRotate(String instruction) {
        //opCode (0-5) + reg (6-7) + al (8) + lr (9) + black (10-11) + count (12-15);
        String opCode = instruction.substring(0, 3);
        String GPR = instruction.substring(4, 5);
        String count = instruction.substring(6, 7);
        String LR = instruction.substring(8, 9);
        String AL = instruction.substring(10, 11);
        System.out.println(opCode);
        switch (opCode) {

            case "SRC": {
                opCode = "011111";
                String[] instructionArray = {opCode, GPR, count, LR, AL};
                s = new ShiftRotate(r, m, instructionArray);
                s.Shift();
                break;
            }
            case "RRC": {
                opCode = "100000";
                String[] instructionArray = {opCode, GPR, count, LR, AL};
                s = new ShiftRotate(r, m, instructionArray);
                s.Rotate();
                break;
            }

        }

    }

    public void decodeForIO(String instruction, String input) throws Exception {
        String opCode = instruction.substring(0, 3);
        String GPR = instruction.substring(4, 5);
        String DEVID = instruction.substring(6);
        // System.out.println(input);
        // cs.simulator.simulator.pc;
        //  JFrame frame = new JFrame(cs.simulator);
        // int result = JOptionPane.showConfirmDialog(null, simulator.frame1, "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        switch (opCode) {
            case "IN ": {
                opCode = "111101";
                String[] instructionArray = {opCode, GPR, DEVID, input};

                i = new IO_Operation(r, m, instructionArray);
                i.input();
                break;
            }

            case "OUT": {
                opCode = "111110";
                String[] instructionArray = {opCode, GPR, DEVID};
                i = new IO_Operation(r, m, instructionArray);
                i.output();
                break;
            }
            case "CHK": {
                opCode = "111111";
                String[] instructionArray = {opCode, GPR, DEVID};
                i = new IO_Operation(r, m, instructionArray);
                i.CHK();
                break;
            }
            default:
                break;
        }

    }

    public void decodeForFloating(String instruction) throws Exception { // Decoding method for Floating point and vector

        String opCode = instruction.substring(0, 5);      //split the string into sub parts using substring method
        String FR = instruction.substring(6, 7);
        String IX = instruction.substring(8, 9);
        String I = instruction.substring(10, 11);
        String Address = instruction.substring(12);

        switch (opCode) {                        //Checking for opCode value and setting the value to it because opCode values are predefined
            case "FADD ":
                opCode = "100001";
                break;
            case "FSUB ":
                opCode = "100010";
                break;
            case "VADD ":
                opCode = "100011";
                break;
            case "VSUB ":
                opCode = "100100";
                break;
            case "CNVRT":
                opCode = "100101";
                break;
            case "LDFR ":
                opCode = "110010";
                break;
            case "STFR ":
                opCode = "110011";
                break;
            default:
                break;
        }



        // int decimalAddress = Integer.parseInt(Address);
        Address = Utilities.EffectiveAddress.computeEA(Integer.parseInt(I), Integer.parseInt(IX), Address, r, m);
        Address = String.valueOf(Integer.parseInt(Address));
        System.out.println("Address:" + Address);
        // System.out.println(Address);
        // System.out.println(I);
        System.out.println("working");

        String[] instructionArray = {opCode, FR, IX, I, Address};
        // System.out.println(instructionArray[0]);
        f = new Floating(r, m, instructionArray);
        if (opCode == "100001") //ldr
        {
            System.out.println("FADD");
            f.FADD();

        } else if (opCode == "100010") //str
        {
            System.out.println("FSUB");
            f.FSUB();
        } else if (opCode == "100011") //lda
        {
            System.out.println("VADD");
            f.VADD();
        } else if (opCode == "100100") //ldx
        {
            System.out.println("VSUB");
            f.VSUB();
        } else if (opCode == "100101") //stx
        {
            System.out.println("CNVRT");
            f.CNVRT();
        } else if (opCode == "110010") //ldx
        {
            System.out.println("LDFR");
            f.LDFR();
        } else if (opCode == "110011") //stx
        {
            System.out.println("STFR");
            f.STFR();
        }

    }
}

