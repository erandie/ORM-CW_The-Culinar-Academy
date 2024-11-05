package lk.ijse.BO;

import lk.ijse.BO.custom.Implement.PaymentBOImpl;
import lk.ijse.BO.custom.Implement.ProgramBOImpl;
import lk.ijse.BO.custom.Implement.StudentBoImpl;
import lk.ijse.BO.custom.Implement.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        STUDENT, PROGRAM, PAYMENT, USER
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBoImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case USER:
                return new UserBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;

        }
    }

}
