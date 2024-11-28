package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {

    public List<PaymentDTO> getAllPayments() throws Exception;

    public boolean addPayment(PaymentDTO dto) throws Exception;

    public boolean updatePayment(PaymentDTO dto) throws Exception;

    public boolean deletePayment(String id) throws Exception;

    public PaymentDTO searchPayment(String id) throws Exception;

    public String generateNew_PaymentID() throws Exception;

    public boolean existPayment(String payId) throws Exception;

    String getCurrentId();
    List<String> getIds();
}
