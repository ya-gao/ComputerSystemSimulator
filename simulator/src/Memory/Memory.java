package Memory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Zixiang
 */

public class Memory {
    public static int MEMORY_MAX_LENGTH= 2048; //Max size is 2048 words
    public static int MaxValue = 65536; //Max value is 2^16
    String memory[];
    String memory1[];
    


    public Memory(){
        //Here we can choose to load ROM from files or automatically load.
        //Memory.initializeMemory();
        memory=new String[2048];
        memory1=new String[2048];
        System.out.println("Hello Memory");
                for(int i=0;i<(MEMORY_MAX_LENGTH*2);i++){
            if(i<MEMORY_MAX_LENGTH)
            {
                memory[i]="0";
            }
            else
            {
                memory1[i-2048]="0";
            }
        }
    }

    public int getMemoryCapacity(){// get current memory capacity that has been used
        return memory.length+memory1.length;
    }

    public String getMemValue(String address) throws Exception{//get memory value by address
        int addr= Integer.parseInt(address);
        String result = "";
        if(addr>MEMORY_MAX_LENGTH-1 &&addr<MEMORY_MAX_LENGTH*2){
            result= memory1[addr];
        }
        else if(addr>getMemoryCapacity()){
            throw new Exception("Memory not defined");
        }
        else if(addr<MEMORY_MAX_LENGTH){
            result = memory[addr];
        }
        else {
            throw new Exception("Exceeds the MEMORY_MAX_LENGTH*2 words limit");
        }
        return result;
    }

    public void setMemValue(String address, String Value) throws Exception{// set memory value with address and data
        int value=0;
        float fvalue = 0;
        if((Character.isLetter(Value.charAt(0))==true))
        {

        }
        else
        {
            try{
                value = Integer.parseInt(Value);
            }catch(NumberFormatException e){
                //not int
            }
            //check if float
            try{
                fvalue = Float.parseFloat(Value);
            }catch(NumberFormatException e){
                //not float
            }



        }
        int addr= Integer.parseInt(address);
        if (addr> MEMORY_MAX_LENGTH-1){
            throw new Exception("Exceeds the MEMORY_MAX_LENGTH limit");
        }
        else if(value>MaxValue){
            throw new Exception("Value Overflow");
        }
       
        else if(addr>MEMORY_MAX_LENGTH-1 &&addr<MEMORY_MAX_LENGTH*2) {
            memory1[addr]=Value;
        }
        else if(addr<MEMORY_MAX_LENGTH)
        {
            memory[addr]=Value;
        }
    }

 /*   public static void initializeMemory(){
        File file = new File("memory.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 5; //available memory address starts from 5
            while ((tempString = reader.readLine()) != null) {
                memory.set(line,tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }*/

  
}
