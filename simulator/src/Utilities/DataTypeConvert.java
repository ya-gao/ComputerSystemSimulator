package Utilities;

public class DataTypeConvert {
    public static String intToString(int num, int addressSize){//decimal Integer to binary String
        String value = Integer.toBinaryString(num);
        String formatter = "%" + addressSize + "s";
        return String.format(formatter, value).replace(' ', '0');
    }
    public static String padding(String str, int length) {
 /* This function adds leading zeros to binary strings until length of str reaches length
 * this func is needed because after some cumputation we get some binary strings shorter than its standard length,
 * we want some padding zeros so that the stadard size of strings can be assigned to registers
 */
        if (length == 16) {
            str = String.format("%016d", Integer.parseInt(str));
        } else if (length == 12) {
            str = String.format("%012d", Integer.parseInt(str));
        } else if (length == 4) {
            str = String.format("%04d", Integer.parseInt(str));
        }
        return str;
    }
    public static int DecimalToBinary(int num,int addressSize){
        String str=intToString(num,addressSize);
        num=Integer.parseInt(str,2);
        return num;
    }
}
