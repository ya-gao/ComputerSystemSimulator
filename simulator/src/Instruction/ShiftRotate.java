package Instruction;

import Memory.Memory;
import Registers.Registers;
import Utilities.DataTypeConvert;
import cs.simulator.simulator;

import java.util.Arrays;

/**
 * @author Zixiang
 */

public class ShiftRotate {
     Memory mem;
     Registers R;
     String address;
     int intGPR;
     int count;
     int al;
     int lr;

    //Shift and rotate should both be divided into four conditions. ">>" and ">>>" can be used into implementation of right shift,
    //while left shift rotate can only be implemented by data change for a String.
    // 0 represents for right, 1 represents for left.
    // 0 represents for arithmetic, 1 represents for logical.
    public void Shift() {
       // R.setGPR(3,"6");
        System.out.println("shift");
        String registerValue = DataTypeConvert.intToString(Integer.parseInt(R.getGPR(intGPR)), 16);
        System.out.println(registerValue);
        if (count == 0) {
        } else {

            int firstDigit = registerValue.charAt(0);
            char[] shiftResult = new char[16];
            if (lr == 0) {//right
                if (al == 0) {//arithmetic
                    for (int i = 1; i < count + 1; i++) {
                        shiftResult[i] = 0;
                    }
                    for (int i = count + 1; i < 16; i++) {
                        shiftResult[i] = registerValue.charAt(i - count);
                    }
                    shiftResult[0] = registerValue.charAt(0);
                } else {//logical
                    for (int i = 1; i < count; i++) {
                        shiftResult[i] = 0;
                    }
                    for (int i = count; i < 16; i++) {
                        shiftResult[i] = registerValue.charAt(i - count);
                    }
                }
            } else {//left
                if (al == 0) {//arithmetic
                    for (int i = 15; i > 15 - count; i--) {
                        shiftResult[i] = 0;
                    }
                    for (int i = 15 - count; i > 0; i--) {
                        shiftResult[i] = registerValue.charAt(i + count);
                    }
                    shiftResult[0] = (char) firstDigit;
                } else {//logical
                    for (int i = 15; i > 15 - count; i--) {
                        shiftResult[i] = '0';
                    }
                    for (int i = 15 - count; i >= 0; i--) {
                        shiftResult[i] = registerValue.charAt(i + count);
                    }
                }
            }
            registerValue = "";
            for (char i : shiftResult) {
                registerValue += i;
            }
        }
        System.out.println("result: " + registerValue);
        R.setGPR(intGPR, registerValue);
        switch(intGPR)
        {
              case 0:
                {
                    cs.simulator.simulator.r0.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                    break;
                }
                 case 1:
                {
                    cs.simulator.simulator.r1.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                    break;
                }
                 case 2:
                {
                    cs.simulator.simulator.r2.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                    break;
                }
                 case 3:
                {
                    cs.simulator.simulator.r3.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                    cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                    break;
                }
                 default:
                     break;
                    
        }
    }

    public void Rotate() {//rotate instruction should be implemented as below:
        //1.store the numbers that will be replaced by others in result array
        //2.store the rest numbers in result array

        String registerValue = DataTypeConvert.intToString(Integer.parseInt(R.getGPR(intGPR)), 16);
        System.out.println("registerValue: " + registerValue);

        if (count != 0) {
            int firstDigit = registerValue.charAt(0);
            char[] rotateResult = new char[16];
            if (lr == 0) {//right
                if (al == 0) {//arithmetic
                    for (int i = 1; i < count + 1; i++) {
                        rotateResult[i] = registerValue.charAt(16 - count - 1 + i);
                    }
                    for (int i = count + 1; i < 16; i++) {
                        rotateResult[i] = registerValue.charAt(i - count);
                    }
                    rotateResult[0] = (char) firstDigit;


                    //registerValue = Arrays.toString(rotateResult);
                } else {//logical
                    for (int i = 0; i < 16 - count; i++) {
                        rotateResult[i + count] = registerValue.charAt(i);
                    }
                    for (int i = 0; i < count; i++) {
                        rotateResult[i] = registerValue.charAt(16 - count + i);
                    }


                    //registerValue = Arrays.toString(rotateResult);
                }

            } else {//left
                if (al == 0) {//arithmetic
                    for (int i = 1; i < 16 - count; i++) {
                        rotateResult[i] = registerValue.charAt(i + count);
                    }
                    for (int i = 16 - count; i < 16; i++) {
                        rotateResult[i] = registerValue.charAt(i - 15 + count);
                    }
                    rotateResult[0] = (char) firstDigit;


                    //registerValue = Arrays.toString(rotateResult);
                } else {//logical
                    for (int i = 0; i < 16 - count; i++) {
                        rotateResult[i] = registerValue.charAt(i + count);
                    }
                    for (int i = 16 - count; i < 16; i++) {
                        rotateResult[i] = registerValue.charAt(i - 16 + count);
                    }

                    //registerValue = Arrays.toString(rotateResult);
                }
            }
            registerValue = "";
            for (char i : rotateResult) {
                registerValue += i;
            }
        }
        R.setGPR(intGPR, registerValue);
        System.out.println("result: " + registerValue);
        switch(intGPR)
        {
            case 0:
            {
                cs.simulator.simulator.r0.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                break;
            }
            case 1:
            {
                cs.simulator.simulator.r1.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                break;
            }
            case 2:
            {
                cs.simulator.simulator.r2.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                break;
            }
            case 3:
            {
                cs.simulator.simulator.r3.setText(String.valueOf(Integer.parseInt(R.getGPR(intGPR),2)));
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "Register value(R" + intGPR + ")" + ":");     //Log is used to display the description of what is happening to the values of MAR,MBR,IR,PC whenever we execute a instruction
                cs.simulator.simulator.log.setText(cs.simulator.simulator.log.getText() + "" + R.getGPR(intGPR));
                break;
            }
            default:
                break;

        }
    }

    public ShiftRotate(Registers register, Memory memory, String[] instruction) {
        mem = memory;
        R = register;
        intGPR = Integer.parseInt(instruction[1]);
        count = Integer.parseInt(instruction[2]);
        al = Integer.parseInt(instruction[3]);
        lr = Integer.parseInt(instruction[4]);
    }

   
}
