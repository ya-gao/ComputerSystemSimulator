package Utilities;

import Memory.Memory;
import Registers.Registers;

/**
 * @author Ya
 */

public class EffectiveAddress {
    public static String computeEA(int I, int IX, String Address, Registers reg, Memory mem) throws Exception {
        //Address here should be binary String type
        String EA = new String();
       // System.out.println(mem.getMemValue(Address));
        if (I == 0) {
            // No indirect addressing
            //System.out.println("I");
            if (IX == 0) {
                // EA = content of the Address field
                System.out.println("Entered EA");
                EA = Address;
                System.out.println(EA);
                
            } else if (IX == 1 || IX == 2 || IX == 3) {
                //Calculating EA = c(IX) + c(Address)
                System.out.println("Entered EA1");
                
                EA = String.valueOf((Integer.parseInt(reg.getXR(IX)) + Integer.parseInt(Address)));
                //System.out.println(EA);
            }
        } else {
            if (IX == 0) {
                // indirect addressing, but NO indexing, EA = c(Address)
                EA = mem.getMemValue(Address);
            } else {
                //Both indirect addressing and indexing, EA = c(c(IX) + Address)
                EA = String.valueOf((Integer.parseInt(reg.getXR(IX)) + Integer.parseInt(Address)));
            }
        }
        
        return EA;
    }

}

