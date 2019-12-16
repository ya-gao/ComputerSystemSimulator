package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.EffectiveAddress;
import cs.simulator.simulator;

import  static Registers.Registers.Overflow;
import  static Utilities.DataTypeConvert.padding;

public class Logical {
     Memory mem;
     Registers R;
    simulator GUI;
     String address;
     int intGPR;
     int RX;
     int RY;
     String op;
     String s;

    public Logical(Registers register, Memory memory, String[] instruction) throws Exception {
        R=register;
        mem=memory;
        op=instruction[0];
        RX=Integer.parseInt(instruction[1]);
        String ry=instruction[2];
        if(ry.equals(" "))
        {
            
        }
        else
        {
        RY=Integer.parseInt(instruction[2]);
    }
    }
    public void MUL(){
        System.out.println("MUL");
        R.setGPR(RX,String.valueOf(Integer.parseInt(R.getGPR(RX))*Integer.parseInt(R.getGPR(RY))));
        System.out.println(R.getGPR(RX));
        R.incrementPC();
        cs.simulator.simulator.pc.setText(R.getPC());
    }
    public void MIN(){     // Strore smaller value in RX
       System.out.println("hey min");
       System.out.println(RX+""+RY);
       System.out.println(Integer.parseInt(R.getGPR(RX)));
       System.out.println(Integer.parseInt(R.getGPR(RY)));
        if (Integer.parseInt(R.getGPR(RY)) < Integer.parseInt(R.getGPR(RX))) { 
            // if RY < RX, RX=RY.
            R.setGPR(RX, R.getGPR(RY));
            System.out.println("Register value:"+R.getGPR(RX));
            R.incrementPC();
             cs.simulator.simulator.pc.setText(R.getPC());
        }
        else
        {
             R.incrementPC();
             cs.simulator.simulator.pc.setText(R.getPC());
        }
       // System.out.println("Register value:"+R.getGPR(RX));
    }    

    public void MLT(){// MLT: Multiply Register by Register
        System.out.println("Rx::"+RX+"Ry:"+RY);
        System.out.println(Integer.parseInt(R.getGPR(RX)+""+Integer.parseInt(R.getGPR(RY))));
        String value = Integer.toBinaryString(Integer.parseInt(R.getGPR(RX)) * Integer.parseInt(R.getGPR(RY))); // value = c(rx)* c(ry)
        System.out.println(value);
        if (R.setGPR(RY, value).equals(Registers.Valid)) {            // if no overflow, rx + 1 <- result.
            // Valid and Overflow are declared in Registers class
            R.setGPR(RX + 1, String.valueOf(Integer.parseInt(padding(value, 16),2)));
            System.out.println("Padding done");
            switch(RX+1)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX+1));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX+1));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX+1));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX+1));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 default:
                     break;
                    }
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        } else if (R.setGPR(RY, value).equals(Overflow)) {  // if Overflow, rx <- higher order bits, rx+1 <- lower order bits
            // seperate the product into overflow and result
            String overflow = String.valueOf(Integer.parseInt(padding(value.substring(0, value.length() - 16), 16),2));
            String result = String.valueOf(Integer.parseInt(padding(value.substring(value.length() - 16), 16),2));
            // rx <- overflow, rx + 1 <- result
            R.setGPR(RX, overflow);
            switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
            R.setGPR(RX + 1, result);
            switch(RX+1)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX+1));
                     cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX+1));
                     cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX+1));
                     cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX+1));
                     cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + (RX+1) + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX+1));
                    break;
                }
                 default:
                     break;
                    }
            // set OVERFLOW Flag (set cc[0] to 1)
            R.setCC(0, "1");
            cs.simulator.simulator.cc.setText(R.getCC(0));
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
            s=op+Integer.toBinaryString(RX)+Integer.toBinaryString(RY);
            System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
            System.out.println("hi");
            int dec=Integer.parseInt(s,2);
        System.out.println("dec:"+dec);
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

    public void DVD(){// DVD: Divide Register by Register
        if (Integer.parseInt(R.getGPR(RY)) == 0 ){   // if ry == 0, set DIVZERO flag (set CC[2] to 1)
            R.setCC(2, "1");
            cs.simulator.simulator.cc.setText(R.getCC(2));
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {
            // do division in integer format
            int quotient = Integer.parseInt(R.getGPR(RX)) / Integer.parseInt(R.getGPR(RY));
            int remainder = Integer.parseInt(R.getGPR(RX)) % Integer.parseInt(R.getGPR(RY));
            // convert integer results into binary strings with atandard size
            String quo = padding(Integer.toBinaryString(quotient),16);
            String rem = padding(Integer.toBinaryString(remainder),16);
            // rx <- quotient, ry <- remainder
            R.setGPR(RX, String.valueOf(Integer.parseInt(quo,2)));
            switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
            R.setGPR(RY, String.valueOf(Integer.parseInt(rem,2)));
            switch(RY)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RY));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RY + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RY));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RY));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RY + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RY));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RY));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RY + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RY));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RY));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RY + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RY));
                    break;
                }
                 default:
                     break;
                    }
            
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
             s=op+Integer.toBinaryString(RX)+Integer.toBinaryString(RY);
            System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
            System.out.println("hi");
            int dec=Integer.parseInt(s,2);
        System.out.println("dec:"+dec);
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
    public void TRR(){// TRR: Test the Equality of Register and Register
        if (R.getGPR(RX).equals(R.getGPR(RY))) {          // if rx == ry, CC[3] <- 1
            R.setCC(3, "1");
            cs.simulator.simulator.cc.setText(R.getCC(3));
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else CC[3] <- 0
            R.setCC(3, "0");
            cs.simulator.simulator.cc.setText(R.getCC(3));
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
         s=op+Integer.toBinaryString(RX)+Integer.toBinaryString(RY);
            System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
           // System.out.println("hi");
            int dec=Integer.parseInt(s,2);
        System.out.println("dec:"+dec);
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
           // cs.simulator.simulator.pc.setText(R.getPC());
            

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
    public void AND(){// AND: Logical AND of Register and Register
        if (Integer.parseInt(R.getGPR(RX)) != 0 && Integer.parseInt(R.getGPR(RY)) != 0) {
           // R.setGPR(RX, padding("1", 16));           // if c(rx) AND c(ry) is True, rx <- 1
           R.setGPR(RX,"1");
           switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
           R.incrementPC();
           cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else, rx <- 0
           // R.setGPR(RX, padding("0", 16));
            R.setGPR(RX,"0");
            switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
           R.incrementPC();
           cs.simulator.simulator.pc.setText(R.getPC());
        }
         s=op+Integer.toBinaryString(RX)+Integer.toBinaryString(RY);
            System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
           // System.out.println("hi");
            int dec=Integer.parseInt(s,2);
        System.out.println("dec:"+dec);
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
           // cs.simulator.simulator.pc.setText(R.getPC());
            

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
    public void ORR(){// ORR: Logical OR of Register and Register
        if (Integer.parseInt(R.getGPR(RX)) == 0 && Integer.parseInt(R.getGPR(RY)) == 0 ){
           // R.setGPR(RX, padding("0", 16));           // if c(rx) OR c(ry) is False, rx <- 0
            R.setGPR(RX, "0");
            switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
           R.incrementPC();
           cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else, rx <- 1
            //R.setGPR(RX, padding("1", 16));
           R.setGPR(RX,"1");
           switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
        s=op+Integer.toBinaryString(RX)+Integer.toBinaryString(RY);
            System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
           // System.out.println("hi");
            int dec=Integer.parseInt(s,2);
        System.out.println("dec:"+dec);
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
           // cs.simulator.simulator.pc.setText(R.getPC());
            

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
    public void NOT(){// NOT: Logical NOT of Register and Register
        if (Integer.parseInt(R.getGPR(RX)) == 0 ){   // if c(rx) == 0, rx <- 1
           // R.setGPR(RX, padding("1", 16));
           R.setGPR(RX, "1");
           switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
           R.incrementPC();
           cs.simulator.simulator.pc.setText(R.getPC());
        } else {                                         // else, rx <- 0
            //R.setGPR(RX, padding("0", 16));
            R.setGPR(RX,"0");
            switch(RX)
                    {
                case 0:
                {
                    cs.simulator.simulator.r0.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(R.getGPR(RX));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + RX + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
            cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(RX));
                    break;
                }
                 default:
                     break;
                    }
            R.incrementPC();
            cs.simulator.simulator.pc.setText(R.getPC());
        }
        s=op+Integer.toBinaryString(RX);
          //  System.out.println(Integer.toBinaryString(RY));
            System.out.println(s);
           // System.out.println("hi");
            int dec=Integer.parseInt(s,2);
        System.out.println("dec:"+dec);
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
           // cs.simulator.simulator.pc.setText(R.getPC());
            

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
