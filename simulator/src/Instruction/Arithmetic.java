package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.EffectiveAddress;
import cs.simulator.simulator;

/**
 * @author Ya
 */

public class Arithmetic {

    Memory mem;
    Registers R;
    simulator GUI;
    String address;
    int intGPR;
    String opCode;
    String GPR;
    String IX;
    String I;
    String immediate;
    String q;
    String s;

    public Arithmetic(Registers register, Memory memory, String[] instruction) throws Exception {
        R = register;
        mem = memory;

    }

    // AMR: Add Memory To Register
    public void AMR(String[] instruction) throws Exception {
        GPR = instruction[1];
        IX = instruction[2];
        I = instruction[3];
        int intIX = Integer.parseInt(IX);
        int intI = Integer.parseInt(I);
        intGPR = Integer.parseInt(GPR);
        address = EffectiveAddress.computeEA(intI, intIX, instruction[4], R, mem);
        // value = c(reg) + c(EA)
        String value = String.valueOf(Integer.parseInt(R.getGPR(intGPR)) + Integer.parseInt(mem.getMemValue(address))); 
        // reg <- c(reg) + c(EA)
        R.setGPR(intGPR, value);                         
        System.out.println("after amr:"+R.getGPR(intGPR));
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
        R.incrementPC();
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(address);
        System.out.println(j);

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
        // System.out.println(q);
        s = instruction[0] + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

        //  s="0000011010010110";
        System.out.println("s for Amr:" + s);
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
        cs.simulator.simulator.ir.setText(R.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        // R.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(R.getPC());
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

    public void SMR(String[] instruction) throws Exception {// SMR: Subtract Memory From Register
        GPR = instruction[1];
        intGPR = Integer.parseInt(GPR);
        IX = instruction[2];
        I = instruction[3];
        int intIX = Integer.parseInt(IX);
        int intI = Integer.parseInt(I);
        address = EffectiveAddress.computeEA(intI, intIX, instruction[4], R, mem);
        System.out.println("Address:" + address);
        System.out.println("Address data "+address+" stores "+mem.getMemValue(address));
        System.out.println("Address 600 stores "+mem.getMemValue(600+""));
        System.out.println("Address 1000 stores "+mem.getMemValue(1000+""));
        System.out.println("Address 1003 stores "+mem.getMemValue(1003+""));


        /*for(int i=100;i<750;i++){
            System.out.println("Data in address "+i+" is "+mem.getMemValue(i+""));
        }*/

        System.out.println("Register NO:" + intGPR);
        System.out.println("Register Data:" + R.getGPR(intGPR));
        String value = String.valueOf(Integer.parseInt(R.getGPR(intGPR)) - Integer.parseInt(mem.getMemValue(address))); // value = c(reg) + c(EA)
        R.setGPR(intGPR, value);                         // reg <- c(reg) - c(EA)
        System.out.println("After SMR op:");
        System.out.println("Register Data:" + R.getGPR(intGPR));
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
        R.incrementPC();
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1 ;

        int count = 0;
     
        j1=(int)(Math.log(j) /Math.log(2) + 1);
        System.out.println("j1:"+j1);
    
        if(j1<=6)
        {
            count=6-j1;
            while(count!=0)
            {
                q=0+q;
                count--;
            }
        }
        else
        {
           
        }
        s = instruction[0] + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

        //  s="0000011010010110";
        System.out.println("s for Smr:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());

        //  int dec=0;

        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        // R.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(R.getPC());
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

    public void AIR(String[] instruction) {// AIR: Add Immediate To Register
        GPR = instruction[1];
        intGPR = Integer.parseInt(GPR);
        immediate = instruction[2];
        String value = String.valueOf(Integer.parseInt(R.getGPR(intGPR)) + Integer.parseInt(immediate)); // value = c(reg) + immediate
        R.setGPR(intGPR, value);                         // reg <- c(reg) + immediate
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
        R.incrementPC();
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(immediate);

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
        s = instruction[0] + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + q;

        //  s="0000011010010110";
        System.out.println("s for Air:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(R.getPC());
        R.setMAR(R.getPC());


        System.out.println("Current GPR"+intGPR+" is "+R.getGPR(intGPR));


        cs.simulator.simulator.mar.setText(R.getMAR());                    //Displaying MAR value
        R.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(R.getMBR());                    //Displaying MBR value
        R.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(R.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        // R.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(R.getPC());
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

    public void SIR(String[] instruction) {// SIR: Subtract Immediate To Register
        GPR = instruction[1];
        intGPR = Integer.parseInt(GPR);
        immediate = instruction[2];
        String value = String.valueOf(Integer.parseInt(R.getGPR(intGPR)) - Integer.parseInt(immediate)); // value = c(reg) - immediate
        R.setGPR(intGPR, value);                         // reg <- c(reg) - immediate
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
        R.incrementPC();
        cs.simulator.simulator.pc.setText(R.getPC());
        int j = Integer.parseInt(immediate);

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
        s = instruction[0] + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + q;

        //  s="0000011010010110";
        System.out.println("s for Sir:" + s);
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
        cs.simulator.simulator.ir.setText(R.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        // R.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(R.getPC());
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
