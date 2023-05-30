package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contract.Payment;
import contract.PaymentList;
import util.Constants.PayWay;

public class PaymentDao implements PaymentList{
	
	private Dao dao;

    public PaymentDao() {
        try {
            dao = new Dao();
            dao.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void add(Payment payment) {
		String query = "INSERT into Payment (contractID, duration, contractDuration, expireDate, content, amount, accidentCount, payway, result) values("
	            + payment.getContractID() + ", " 
	            + payment.getDuration() + ", " 
	            + payment.getContractDuration() + ", " 
	            + payment.getExpireDate() + ", '" 
	            + payment.getContent() + "', " 
	            + payment.getAmount() + ", "
	            + payment.getAccidentCount() + ", '" 
	            + payment.getPayway().getString() + "', '"
	            + payment.getResult() + "');";
	        dao.create(query);		
	}

	@Override
	public void delete(int paymentID) {
		String query = "DELETE FROM Payment WHERE paymentID = " + paymentID + ";";
        dao.delete(query);		
	}

	@Override
	public Payment retrieve(int paymentID) {
		String query = "SELECT * FROM Payment WHERE paymentID =" + paymentID + ";";
		ResultSet resultSet = dao.retrieve( query );
		Payment payment = new Payment();
		try {
			payment.setPaymentID(resultSet.getInt("paymentID"));
        	payment.setContractID( resultSet.getInt("contractID"));
        	payment.setDuration( resultSet.getInt("duration"));
        	payment.setContractDuration( resultSet.getInt("contractDuration"));
        	Date expireDate = resultSet.getDate("expireDate");
        	payment.setExpireDate( dao.dateNullCheck(expireDate ) );
        	payment.setContent( resultSet.getString("content") );
        	payment.setAmount( resultSet.getInt("amount") );
        	payment.setAccidentCount( resultSet.getInt("accidentCount"));
        	String payway = resultSet.getString("payway");
        	payment.setPayway(dao.enumNullCheck(payway, () -> PayWay.valueOf(payway)));
        	if( resultSet.getBoolean("result") ) payment.setResult(true);
        	else payment.setResult(false);
		} catch (SQLException e) {throw new RuntimeException(e);}
		return payment;
	}

	@Override
	public List<Payment> retrieveAll() {
		String query = "SELECT * FROM Payment;";
        ResultSet resultSet = dao.retrieve(query);
        List<Payment> paymentList = new ArrayList<Payment>();
        try {
            while (resultSet.next()){
            	Payment payment = new Payment();
            	payment.setPaymentID(resultSet.getInt("paymentID"));
            	payment.setContractID( resultSet.getInt("contractID"));
            	payment.setDuration( resultSet.getInt("duration"));
            	payment.setContractDuration( resultSet.getInt("contractDuration"));
            	Date expireDate = resultSet.getDate("expireDate");
            	payment.setExpireDate( dao.dateNullCheck(expireDate ) );
            	payment.setContent( resultSet.getString("content") );
            	payment.setAmount( resultSet.getInt("amount") );
            	payment.setAccidentCount( resultSet.getInt("accidentCount"));
            	String payway = resultSet.getString("payway");
            	payment.setPayway(dao.enumNullCheck(payway, () -> PayWay.valueOf(payway)));
            	if( resultSet.getBoolean("result") ) payment.setResult(true);
            	else payment.setResult(false);
            }
        } catch (SQLException e) {throw new RuntimeException(e);}
        return paymentList;
	}

	@Override
	public void update(Payment payment) {
		String query = "UPDATE Payment SET contractID = " +	payment.getContractID() + ", "
				+ "duration = " + payment.getDuration() + ", " 
				+ "contractDuration = " + payment.getContractDuration() + ", " 
				+ "expireDate = " + payment.getExpireDate() + ", "
				+ "content = '" + payment.getContent() + "', "
				+ "amount = " + payment.getAmount() + ", " 
				+ "accidentCount = " + payment.getAccidentCount() + ", " 
				+ "payway = '" + payment.getPayway().getString() + "', "
				+ "result = '" + payment.getResult()
				+ "' WHERE paymentID = " + payment.getPaymentID() + ";";
		dao.update( query );
	}

}
