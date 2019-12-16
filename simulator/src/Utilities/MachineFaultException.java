package Utilities;

public class MachineFaultException extends Exception{
    //We use this class in three places,
    // Effective address calculating, running instructions and checking Trap code

    private int faultCode;
    private String message;


    public MachineFaultException(int faultCode, String message){
        this.faultCode = faultCode;
        this.message = message;
    }

    public MachineFaultException(int faultCode){
        this.faultCode = faultCode;
    }


    public int getFaultCode(){
        return this.faultCode;
    }

    public String getMessage(){
        return this.message;
    }

    public enum FaultCode {
        ILL_MEM_RSV(0, "Illegal Memory Address to Reserved Locations"),
        ILL_TRPC(1, "Illegal TRAP code"),
        ILL_OPRC(2, "Illegal Operation Code"),
        ILL_MEM_BYD(3, "Illegal Memory Address beyond 2048 (memory installed)");
        int value;
        String messsage;

        private FaultCode(int value, String message) {
            this.value = value;
            this.messsage = message;
        }

        public int getValue() {
            return this.value;
        }

        public String getMessage() {
            return this.messsage;
        }
    }
}
