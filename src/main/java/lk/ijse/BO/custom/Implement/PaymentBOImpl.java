package lk.ijse.BO.custom.Implement;

import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.PaymentDAO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payments;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {
        List<PaymentDTO> alaPayment = new ArrayList<>();
        for (Payments payments : paymentDAO.getAll()) {
            alaPayment.add(new PaymentDTO(
                    payments.getPaymentID(),
                    payments.getStudentId(),
                    payments.getProgramId(),
                    payments.getPaymentDate(),
                    payments.getAmount(),
                    payments.getBalance(),
                    payments.getPaidAmount()
            ));
        }
        return alaPayment;
    }

    @Override
    public boolean addPayment(PaymentDTO dto) throws Exception {
        return paymentDAO.add(new Payments(
                dto.getPaymentID(),
                dto.getStudentId(),
                dto.getProgramId(),
                dto.getPaymentDate(),
                dto.getAmount(),
                dto.getBalance(),
                dto.getPaidAmount()
        ));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        return paymentDAO.update(new Payments(
                dto.getPaymentID(),
                dto.getStudentId(),
                dto.getProgramId(),
                dto.getPaymentDate(),
                dto.getAmount(),
                dto.getBalance(),
                dto.getPaidAmount()
        ));
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO searchPayment(String id) throws Exception {
        Payments payments = paymentDAO.search(id);
        if (payments != null) {
            return new PaymentDTO(
                    payments.getPaymentID(),
                    payments.getStudentId(),
                    payments.getProgramId(),
                    payments.getPaymentDate(),
                    payments.getAmount(),
                    payments.getBalance(),
                    payments.getPaidAmount()
            );
        }

        return null;
    }

    @Override
    public String generateNew_PaymentID() throws Exception {
        return paymentDAO.generateNewID();
    }

    @Override
    public boolean existPayment(String payId) throws Exception {
        return paymentDAO.exist(payId);
    }

    @Override
    public String getCurrentId() {
        return paymentDAO.getCurrentId();
    }

    @Override
    public List<String> getIds() {
        return paymentDAO.getIds();
    }

}
