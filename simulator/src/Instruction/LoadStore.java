package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.DataTypeConvert;
import cs.simulator.simulator.*;


public class LoadStore {
    Memory mem;
    Registers reg;
    // static simulator s=new simulator();
    // simulator GUI;
    String address;
    String IX;
    String I;
    String GPR;
    String op;
    int intAddress;
    int intIX;
    int intI;
    int intGPR;
    //static int dec;
    String s;
    String q;


    /**
     * @throws Exception
     */
    public void LDR() throws Exception {//Load register from memory
        reg.setGPR(intGPR, mem.getMemValue(address));
        System.out.println("Register number:" + intGPR);
        System.out.println("Register Data:" + reg.getGPR(intGPR));
        switch (intGPR) {
            case 0:
            {
                if((Character.isLetter(reg.getGPR(intGPR).charAt(0))==true))
                {
                    cs.simulator.simulator.r0.setText(IO_Operation.convertWordToInt(reg.getGPR(intGPR)));
                }
                else
                {
                cs.simulator.simulator.r0.setText(reg.getGPR(intGPR));
                }
                break;
            }
            case 1:
            {
                if((Character.isLetter(reg.getGPR(intGPR).charAt(0))==true))
                {
                    cs.simulator.simulator.r1.setText(IO_Operation.convertWordToInt(reg.getGPR(intGPR)));
                }
                else
                {
                cs.simulator.simulator.r1.setText(reg.getGPR(intGPR));
                }
                break;
            }
            case 2:
            {
                if((Character.isLetter(reg.getGPR(intGPR).charAt(0))==true))
                {
                    cs.simulator.simulator.r2.setText(IO_Operation.convertWordToInt(reg.getGPR(intGPR)));
                }
                else
                {
                cs.simulator.simulator.r2.setText(reg.getGPR(intGPR));
                }
                break;
            }
            case 3:
            {
                if((Character.isLetter(reg.getGPR(intGPR).charAt(0))==true))
                {
                    cs.simulator.simulator.r3.setText(IO_Operation.convertWordToInt(reg.getGPR(intGPR)));
                }
                else
                {
                cs.simulator.simulator.r3.setText(reg.getGPR(intGPR));
                }
                break;
            }
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
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(Integer.parseInt(q));

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
       // int dec=0;
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(reg.getPC());
        reg.setMAR(reg.getPC());

        cs.simulator.simulator.mar.setText(reg.getMAR());                    //Displaying MAR value
        reg.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(reg.getMBR());                    //Displaying MBR value
        reg.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(reg.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        reg.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

    }

    public void LDA() {//Load memory from address
        reg.setGPR(intGPR, address);
        switch (intGPR) {
            case 0:
                cs.simulator.simulator.r0.setText(reg.getGPR(intGPR));
                break;
            case 1:
                cs.simulator.simulator.r1.setText(reg.getGPR(intGPR));
                break;
            case 2:
                cs.simulator.simulator.r2.setText(reg.getGPR(intGPR));
                break;
            case 3:
                cs.simulator.simulator.r3.setText(reg.getGPR(intGPR));
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
        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(Integer.parseInt(q));

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(reg.getPC());
        reg.setMAR(reg.getPC());


        System.out.println("The current value in GPR"+intGPR+" is "+reg.getGPR(intGPR));

        cs.simulator.simulator.mar.setText(reg.getMAR());                    //Displaying MAR value
        reg.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(reg.getMBR());                    //Displaying MBR value
        reg.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(reg.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        reg.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }

    public void LDX() throws Exception {//Load memory from index register
        reg.setXR(intIX, mem.getMemValue(address));
        System.out.println(intIX);
        switch (intIX) {
            //  case 0:
            //cs.simulator.simulator.ixr1.setText(reg.getGPR(intIX));
            //    break;
            case 1:
                cs.simulator.simulator.ixr1.setText(reg.getXR(intIX));
                break;
            case 2:
                cs.simulator.simulator.ixr2.setText(reg.getXR(intIX));
                //System.out.println("case 2");
                break;
            case 3:
                cs.simulator.simulator.ixr3.setText(reg.getXR(intIX));
                break;
            default:
                break;
        }

        int j = Integer.parseInt(address);
        System.out.println(j);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        System.out.println(j1);
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;


        }

        if (count < 5) {
            int count1 = 5 - count;
            System.out.println(count);
            while (count1 != 0) {
                q = 0 + q;
                count1--;
                //  System.out.println("hi");
            }
        }


        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(Integer.parseInt(q));

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(reg.getPC());
        reg.setMAR(reg.getPC());

        cs.simulator.simulator.mar.setText(reg.getMAR());                    //Displaying MAR value
        reg.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(reg.getMBR());                    //Displaying MBR value
        reg.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(reg.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        reg.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }

    public void STR() throws Exception {//store register into memory
        mem.setMemValue(address, reg.getGPR(intGPR));
        System.out.println("Memory value at " +address+" is "+ mem.getMemValue(address));
        System.out.println("Register:" + intGPR);

        switch (intGPR) {
            case 0:
                cs.simulator.simulator.r0.setText(reg.getGPR(intGPR));
                break;
            case 1:
                cs.simulator.simulator.r1.setText(reg.getGPR(intGPR));
                break;
            case 2:
                cs.simulator.simulator.r2.setText(reg.getGPR(intGPR));
                break;
            case 3:
                cs.simulator.simulator.r3.setText(reg.getGPR(intGPR));
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
    

        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(q);

        //  s="0000011010010110";
        //System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(reg.getPC());
        reg.setMAR(reg.getPC());

        cs.simulator.simulator.mar.setText(reg.getMAR());                    //Displaying MAR value
        reg.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(reg.getMBR());                    //Displaying MBR value
        reg.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(reg.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one*/
        System.out.println("Before pc:"+reg.getPC());
        reg.incrementPC();
        System.out.println("After pc:"+reg.getPC());
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");

    }
    //cs.simulator.simulator.r0.setText(reg.getGPR(intGPR));


    public void STX() throws Exception {//store index register into memory
        mem.setMemValue(address, reg.getXR(intIX));
        switch (intIX) {
            //  case 0:
            //cs.simulator.simulator.ixr1.setText(reg.getGPR(intIX));
            //    break;
            case 1:
                cs.simulator.simulator.ixr1.setText(reg.getXR(intIX));
                break;
            case 2:
                cs.simulator.simulator.ixr2.setText(reg.getXR(intIX));

                break;
            case 3:
                cs.simulator.simulator.ixr3.setText(reg.getXR(intIX));
                break;
            default:
                break;
        }

        int j = Integer.parseInt(address);
        System.out.println(j);

        q = Integer.toBinaryString(j);
        int j1 = Integer.parseInt(q);

        int count = 0;
        System.out.println(j1);
        while (j1 > 0) {
            j1 = j1 / 10;
            count = count + 1;


        }

        if (count < 5) {
            int count1 = 5 - count;
            System.out.println(count);
            while (count1 != 0) {
                q = 0 + q;
                count1--;
                //  System.out.println("hi");
            }
        }


        s = op + String.valueOf(Integer.toBinaryString(Integer.parseInt(GPR))) + String.valueOf(Integer.toBinaryString(Integer.parseInt(IX))) + I + String.valueOf(Integer.parseInt(q));

        //  s="0000011010010110";
        System.out.println("s:" + s);
        //System.out.println(Integer.parseInt(s,2));
        int dec = Integer.parseInt(s, 2);
        System.out.println("dec:" + dec);
        //reg.setMAR(reg.getPC());                        //setting MAR value
        System.out.println(reg.getPC());
        reg.setMAR(reg.getPC());

        cs.simulator.simulator.mar.setText(reg.getMAR());                    //Displaying MAR value
        reg.setMBR(String.valueOf(dec));              //setting MBR value
        cs.simulator.simulator.mbr.setText(reg.getMBR());                    //Displaying MBR value
        reg.setIR(String.valueOf(dec));               //setting IR value
        cs.simulator.simulator.ir.setText(reg.getIR());                      //Displaying IR value
        //Whenever the the next instruction is ready pc value will increment by one
        reg.incrementPC();
        //reg.setPC(String.valueOf(t));
        cs.simulator.simulator.pc.setText(reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getGPR(intGPR));
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MAR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMAR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "MBR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getMBR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "IR value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getIR());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "PC value:");
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + reg.getPC());
        cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "\n");


    }


    public LoadStore(Registers register, Memory memory, String[] instruction) { //get value from Decode
        reg = register;
        mem = memory;
        op = instruction[0];
        GPR = instruction[1];
        IX = instruction[2];
        I = instruction[3];
        address = instruction[4];
        // System.out.println("loadstore"+address);
        intAddress = Integer.parseInt(address);
        intIX = Integer.parseInt(IX);
        intI = Integer.parseInt(I);
        intGPR = Integer.parseInt(GPR);
    }

    public void Instruction(Registers register, Memory memory) {

    }
}
