package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.EffectiveAddress;
import cs.simulator.simulator;

/* Floating Instructions deal with floating point numbers
 * Vector operations are performed memory to memory.
 * format same as Load/Store: opcode (0-5) + reg (6-7) + ireg (8-9) + i (10) + address (11-15)
 */
public class Floating {
    Memory mem;
    Registers R;
    simulator GUI;
    String address;
    int intFR;
    String s;
    String q;
    String op;
    String FR;
    String IX;
    String I;
    int intIX, intI;

    public Floating(Registers register, Memory memory, String[] instruction) throws Exception {
        R = register;
        mem = memory;
        
        op = instruction[0];
        FR = instruction[1];
        //intFR=Integer.parseInt(FR);
        IX = instruction[2];
        I = instruction[3];
        intIX = Integer.parseInt(IX);
        intI = Integer.parseInt(I);
        if (FR.equals("")) {
        } else {
            intFR = Integer.parseInt(FR);
        }
        address = EffectiveAddress.computeEA(intI, intIX, instruction[4], R, mem);
        System.out.println("address:" + address);
        
    }


    // FADD: Floating Add Memory To Register
    public void FADD() throws Exception {
        System.out.println("PC value before execution:"+R.getPC());
        System.out.println("I value:"+I);
        if(I.equals("0"))
        {
            System.out.println(mem.getMemValue(address)+""+R.getFR(intFR));
        String value = String.valueOf(Float.parseFloat(R.getFR(intFR)) + Float.parseFloat(mem.getMemValue(address))); // value = c(FR) + c(EA)
        R.setFR(intFR,value);
        System.out.println("result is: " + value);
            System.out.println("register data: " + R.getFR(intFR));
        }
        else
        {
            System.out.println(mem.getMemValue(address)+""+R.getFR(intFR));
            String value = String.valueOf(Float.parseFloat(R.getFR(intFR)) + Float.parseFloat(mem.getMemValue(mem.getMemValue(address)))); // value = c(FR) + c(EA)
            R.setFR(intFR,value);
            System.out.println("result is: " + value);
            System.out.println("register data: " + R.getFR(intFR));
        }

        // link updated FR with user interface
        switch (intFR) {
            case 0:
                cs.simulator.simulator.fr0.setText(R.getFR(intFR));
                break;
            case 1:
                cs.simulator.simulator.fr1.setText(R.getFR(intFR));
                break;
            default:
                break;
        }


        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1;

        int count = 0;
       /* while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }*/
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
        // System.out.println("hi");
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(FR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

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

        R.incrementPC();
        System.out.println("PC value after execution:"+R.getPC());

        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intFR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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

    // FSUB: Floating Subtract Memory From Register
   public void FSUB() throws Exception {
       System.out.println("PC value before execution:"+R.getPC());
        if(I=="0")
        {
        String value = String.valueOf(Float.parseFloat(R.getFR(intFR)) - Float.parseFloat(mem.getMemValue(address))); // value = c(FR) + c(EA)
        R.setFR(intFR,value);
            System.out.println("result is: " + value);
            System.out.println("register data: " + R.getFR(intFR));
        }
        else
        {
             String value = String.valueOf(Float.parseFloat(R.getFR(intFR)) - Float.parseFloat(mem.getMemValue(mem.getMemValue(address)))); // value = c(FR) + c(EA)
            R.setFR(intFR,value);
            System.out.println("result is: " + value);
            System.out.println("register data: " + R.getFR(intFR));
        }

       // link updated FR with user interface
       switch (intFR) {
           case 0:
               cs.simulator.simulator.fr0.setText(R.getFR(intFR));
               break;
           case 1:
               cs.simulator.simulator.fr1.setText(R.getFR(intFR));
               break;
           default:
               break;
       }

        int j = Integer.parseInt(address);

        q = Integer.toBinaryString(j);
        int j1;

        int count = 0;
       /* while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;

        }
        if (count < 5) {
            int count1 = 5 - count;
            while (count1 != 0) {
                q = 0 + q;
                count1--;
            }
        }*/
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
        // System.out.println("hi");
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(FR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + q;

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

       R.incrementPC();
        System.out.println("PC value after execution:"+R.getPC());

        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intFR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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

    // VADD: Vector Add
    public void VADD() throws Exception {
        System.out.println("PC value before execution:"+R.getPC());
        String address_V1 = address;
        String address_V2 = String.valueOf(Integer.parseInt(address) + 1);
        int int_addressV1 = Integer.parseInt(address_V1);
        int int_addressV2 = Integer.parseInt(address_V2);
        for (int i = 0; i < (int)(Float.parseFloat(R.getFR(intFR))); i++) {
            R.setMAR((int_addressV1 + i) + "");
            R.setMBR(mem.getMemValue(R.getMAR()));

            // address1
            String address1 = String.valueOf(i + Integer.parseInt(mem.getMemValue(address_V1)));
            System.out.println("address 1 should be 50 or 51: " + address1);

            // vector1 value
            int v1 = Integer.parseInt(mem.getMemValue(address1));
            System.out.println("v1 value should be 7 or 9: " + String.valueOf(v1));


            R.setMAR((int_addressV2 + i) + "");
            R.setMBR(mem.getMemValue(R.getMAR()));

            // address2
            String address2 = String.valueOf(i + Integer.parseInt(mem.getMemValue(address_V2)));
            System.out.println("address 2 should be 52 or 53: " + address2);

            // vector2 value
            int v2 = Integer.parseInt(mem.getMemValue(address2));
            System.out.println("v2 value should be 2 or 5: " + String.valueOf(v2));

            //int v2 = Integer.parseInt(R.getMBR());
            int result = v1 + v2;
            System.out.println(result);

            int MAX_VALUE = (int)(Math.pow(2, 6));
            int MIN_VALUE = -(int)(Math.pow(2, 6));
            if (result > MAX_VALUE || result < MIN_VALUE) {
                System.out.println("IN WRONG BLOCK");
                R.setCC(0, "1");
            } else {
                System.out.println("IN RIGHT BLOCK");
                R.setMBR(result+"");
                //R.setMAR((int_addressV1 + i)+"");
                mem.setMemValue(address1, String.valueOf(result));
            }
            System.out.println("Checking mem[" + address1  + "]: " + mem.getMemValue(address1));
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

        R.incrementPC();
        System.out.println("PC value after execution:"+R.getPC());

        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intFR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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

    // VSUB: Vector Subtract
    public void VSUB() throws Exception {
        System.out.println("PC value before execution:"+R.getPC());
        String address_V1 = address;
        String address_V2 = String.valueOf(Integer.parseInt(address) + 1);
        int int_addressV1 = Integer.parseInt(address_V1);
        int int_addressV2 = Integer.parseInt(address_V2);
        for (int i = 0; i < (int)(Float.parseFloat(R.getFR(intFR))); i++) {
            R.setMAR((int_addressV1 + i) + "");
            R.setMBR(mem.getMemValue(R.getMAR()));

            // address1
            String address1 = String.valueOf(i + Integer.parseInt(mem.getMemValue(address_V1)));
            System.out.println("address 1 should be 54 or 55: " + address1);

            // vector1 value
            int v1 = Integer.parseInt(mem.getMemValue(address1));
            System.out.println("v1 value should be 7 or 9: " + String.valueOf(v1));


            R.setMAR((int_addressV2 + i) + "");
            R.setMBR(mem.getMemValue(R.getMAR()));

            // address2
            String address2 = String.valueOf(i + Integer.parseInt(mem.getMemValue(address_V2)));
            System.out.println("address 2 should be 56 or 57: " + address2);

            // vector2 value
            int v2 = Integer.parseInt(mem.getMemValue(address2));
            System.out.println("v2 value should be 2 or 5: " + String.valueOf(v2));

            //int v2 = Integer.parseInt(R.getMBR());
            int result = v1 - v2;
            System.out.println(result);

            int MAX_VALUE = (int)(Math.pow(2, 6));
            int MIN_VALUE = -(int)(Math.pow(2, 6));
            if (result > MAX_VALUE || result < MIN_VALUE) {
                System.out.println("IN WRONG BLOCK");
                R.setCC(0, "1");
            } else {
                System.out.println("IN RIGHT BLOCK");
                R.setMBR(result+"");
                //R.setMAR((int_addressV1 + i)+"");
                mem.setMemValue(address1, String.valueOf(result));
            }
            System.out.println("Checking mem[" + address1  + "]: " + mem.getMemValue(address1));
        }

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

        R.incrementPC();
        System.out.println("PC value after execution:"+R.getPC());

        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intFR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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

    // CNVRT: Convert to Fixed/FloatingPoint:
    public void CNVRT() throws Exception {

        System.out.println("PC value before execution:"+R.getPC());
        R.setGPR(intFR, "0");
            int F = Integer.parseInt(R.getGPR(intFR));
            R.setMAR(address);
            R.setMBR(mem.getMemValue(R.getMAR()));

            if (F == 0) {
                String converted_number = String.format("%.4f", Float.parseFloat(R.getMBR()));
                R.setGPR(intFR, converted_number);
                System.out.println(R.getGPR(intFR));
            }
            if (F == 1) {
                R.setFR(0, String.valueOf(Float.parseFloat(R.getMBR())));
                System.out.println(R.getFR(0));
            }

        // link updated FR with user interface
        switch (F) {
            case 0:
                cs.simulator.simulator.fr0.setText(R.getGPR(intFR));
                break;
            case 1:
                cs.simulator.simulator.fr0.setText(R.getFR(intFR));
                break;
            default:
                break;
        }



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

        R.incrementPC();
        System.out.println("PC value after execution:"+R.getPC());

        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R3):");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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


    // LDFR: Load Floating Register From Memory
    public void LDFR() throws Exception {
        System.out.println("PC value before execution:"+R.getPC());
        R.setFR(intFR, mem.getMemValue(address));
        System.out.println("Register number:" + intFR);
        System.out.println("Register Data:" + R.getFR(intFR));
        switch (intFR) {
            case 0:
            {
                if((Character.isLetter(R.getFR(intFR).charAt(0))==true))
                {
                    cs.simulator.simulator.r0.setText(IO_Operation.convertWordToInt(R.getFR(intFR)));
                }
                else
                {
                cs.simulator.simulator.r0.setText(R.getFR(intFR));
                }
                break;
            }
            case 1:
            {
                if((Character.isLetter(R.getFR(intFR).charAt(0))==true))
                {
                    cs.simulator.simulator.r1.setText(IO_Operation.convertWordToInt(R.getFR(intFR)));
                }
                else
                {
                cs.simulator.simulator.r1.setText(R.getFR(intFR));
                }
                break;
            }
            default:
            break;
        }

        switch (intFR) {
            case 0:
                cs.simulator.simulator.fr0.setText(R.getFR(intFR));
                break;
            case 1:
                cs.simulator.simulator.fr1.setText(R.getFR(intFR));
                break;
            default:
                break;
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
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(FR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(Integer.parseInt(q));

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
       // int dec=0;
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
        R.incrementPC();
        System.out.println("PC value after execution:"+R.getPC());
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intFR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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


    // STFR: Store Floating Register to Memory
    public void STFR() throws Exception {
        System.out.println("PC value before execution:"+R.getPC());
        mem.setMemValue(address, R.getFR(intFR));
        System.out.println("Memory value at " +address+" is "+ mem.getMemValue(address));
        System.out.println("Register: " + intFR);

        switch (intFR) {
            case 0:
                cs.simulator.simulator.r0.setText(R.getGPR(intFR));
                break;
            case 1:
                cs.simulator.simulator.r1.setText(R.getGPR(intFR));
                break;
            
            default:
                break;
        }
      int j = Integer.parseInt(address);
        System.out.println("j:"+j);
        q = Integer.toBinaryString(j);
        System.out.println("q:"+q);
        int j1;
      //  System.out.println("j1:"+j1);
        int count=0;
        //  System.out.println(j1);
     /*   while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;
        }
        if (count < 5 && j<63) {
            int count1 = 5 - count;
            // System.out.println(count);
            while (count1 != 0) {
                q = 0 + q;
                count1--;
                //  System.out.println("hi");
            }
        }*/
   //  q = String.format("%06d", q);
    // System.out.println(q);
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
    

        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(FR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(q);

        //  s="0000011010010110";
        //System.out.println("s:" + s);
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
        //Whenever the the next instruction is ready pc value will increment by one*/
        R.incrementPC();
        System.out.println("PC value before execution:"+R.getPC());
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(R.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intFR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getFR(intFR));
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
