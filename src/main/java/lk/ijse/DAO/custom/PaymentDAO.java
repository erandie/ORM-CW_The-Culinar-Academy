package lk.ijse.DAO.custom;

import lk.ijse.DAO.CrudDAO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payments;

import java.util.List;

public interface PaymentDAO extends CrudDAO<Payments> {
    List<String> getIds();
    String getCurrentId();
}
