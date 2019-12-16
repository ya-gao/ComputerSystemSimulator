package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.EffectiveAddress;
import cs.simulator.simulator;

/* Transfer Instructions change control of program execution
 * The same format as Load/Store instructions: opcode (0-5) + reg (6-7) + ireg (8-9) + i (10) + address (11-15)
 */
public class Transfer {
    Memory mem;
    Registers R;
    simulator GUI;
    String address;
    int intGPR;
    String s;
    String q;
    String op;
    String GPR;
    String IX;
    String I;
    int intIX, intI;

    public Transfer(Registers register, Memory memory, String[] instruction) throws Exception {
        R = register;
        mem = memory;
        if (instruction.length != 2) {
            op = instruction[0];
            GPR = instruction[1];
            //intGPR=Integer.parseInt(GPR);
            IX = instruction[2];
            I = instruction[3];
            intIX = Integer.parseInt(IX);
            intI = Integer.parseInt(I);
            if (GPR.equals("")) {

            } else {
                intGPR = Integer.parseInt(GPR);
            }
            address = EffectiveAddress.computeEA(intI, intIX, instruction[4], R, mem);
            System.out.println("address:" + address);
        } else {
            op = instruction[0];
            address = instruction[1];
            System.out.println("address:" + address);
        }

    }

    public void JZ() {//Jump if zero
        if (Integer.parseInt(R.getGPR(intGPR)) == 0) {  // if c(reg) == 0, jump to EA
            R.setPC(address);
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else PC + 1
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }

        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }
        // System.out.println("hi");
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

        //  s="0000011010010110";
        // System.out.println("s:"+s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        // System.out.println("dec:"+dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
    }

    public void JNE() {//// JNE: Jump If Not Equal
        if (Integer.parseInt(R.getGPR(intGPR)) != 0) {  // if c(reg) != 0, jump to address
            R.setPC(address);
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else PC + 1
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }
        System.out.println("hi");
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

    }

    public void JCC() {
        System.out.println("jcc1");
        if (R.getCC(intGPR).equals("1")) {                      // if of cc[intIX] == 1, jump to address
            System.out.println("jumping");
            R.setPC(address);
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else PC + 1
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }

        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }

        }
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;
        System.out.println("s:" + s);
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

    }

    public void JMA() {// JMA: Unconditional Jump To Address
        R.setPC(address);
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }

        }
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;
        //  System.out.println("s:"+s);
        int dec = Integer.parseInt(s, 2);
        // System.out.println("dec:"+dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        //  System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

    }

    public void JSR() {// JSR: Jump and Save Return Address
        R.setGPR(3, R.incrementPC());                    // R3 <- PC + 1
        cs.simulator.simulator.r3.setText(R.getGPR(3));
        R.setPC(address);                                     // PC <- EA
// R0 should contain pointer to arguments
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }

        }
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;
        System.out.println("s:" + s);
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R3):");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(3));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

    }

    public void RFS() throws Exception {// RFS: return from subroutine with return code stored in address
        R.setGPR(0, mem.getMemValue(address));              // R0 <- return code
        cs.simulator.simulator.r0.setText(R.getGPR(0));
        R.setPC(R.getGPR(3));// PC <- R3
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }
        // System.out.println("hi");
        s = op + q;

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R0)" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(0));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }

    public void SOB() {// SOB: Subtract One and Branch
        R.setGPR(intGPR, String.valueOf(Integer.parseInt(R.getGPR(intGPR)) - 1)); // reg <- c(reg) - 1
        switch (intGPR) {
            case 0:
                cs.simulator.simulator.r0.setText(R.getGPR(intGPR));
                break;
            case 1:
                cs.simulator.simulator.r1.setText(R.getGPR(intGPR));
                break;
            case 2:
                cs.simulator.simulator.r2.setText(R.getGPR(intGPR));
                break;
            case 3:
                cs.simulator.simulator.r3.setText(R.getGPR(intGPR));
                break;
            default:
                break;
        }
        if (Integer.parseInt(R.getGPR(intGPR)) > 0) {   // if c(reg) > 0, jump to address
            R.setPC(address);
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else PC + 1
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }
        // System.out.println("hi");
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

        //  s="0000011010010110";
        //  System.out.println("s:"+s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }

    public void JGE() {// JGE: Jump Grater Than or Equal To
        if (Integer.parseInt(R.getGPR(intGPR)) > 0) {  // if c(reg) >= 0, jump to address
            R.setPC(address);
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else PC + 1
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }
        //    System.out.println("hi");
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

        //  s="0000011010010110";
        // System.out.println("s:"+s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        // System.out.println("dec:"+dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        // System.out.println(R.getPC());
        R.setMAR(R.getPC());

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());
        // System.out.println("GPR:"+GPR);

        switch (GPR) {
            case "0":
                cs.simulator.simulator.r0.setText(R.getGPR(intGPR));
                break;
            case "1":
                cs.simulator.simulator.r1.setText(R.getGPR(intGPR));
                break;
            case "2":
                cs.simulator.simulator.r2.setText(R.getGPR(intGPR));
                break;
            case "3":
                cs.simulator.simulator.r3.setText(R.getGPR(intGPR));
                break;
            default:
                break;
        }
        switch (IX) {
            //  case 0:
            //cs.simulator.simulator.ixr1.setText(reg.getGPR(intIX));
            //    break;
            case "1":
                cs.simulator.simulator.ixr1.setText(R.getXR(intIX));
                break;
            case "2":
                cs.simulator.simulator.ixr2.setText(R.getXR(intIX));
                //System.out.println("case 2");
                break;
            case "3":
                cs.simulator.simulator.ixr3.setText(R.getXR(intIX));
                break;
            default:
                break;
        }
        cs.simulator.simulator.r0.setText(R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }


}
