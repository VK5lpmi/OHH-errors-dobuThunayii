package infinite.BusBooking;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class BookingDAO {

	SessionFactory sessionFactory;
	Session session;
	

	public String  generateUserID() {
		
		sessionFactory = SessionHelper.getConnection();
		session =sessionFactory.openSession();
		Criteria cr = session.createCriteria(Booking.class);
		List<Booking> bk = cr.list();
		if(bk.size()==0){
			return "U001";
		}
		String userId = bk.get(bk.size() -1).getUserId();
		userId = String.format("%03d", Integer.parseInt(userId.substring(1))+1);
		userId = "U" + userId;
		return userId;
	}
	

	public String generateScheduleID() {
		
		sessionFactory = SessionHelper.getConnection();
		session =sessionFactory.openSession();
		Criteria cr = session.createCriteria(Booking.class);
		List<Booking> bk = cr.list();
		if(bk.size()==0){
			return "S001";
		}
		String scheduleId = bk.get(bk.size()-1).getScheduleId();
		scheduleId = String.format("%03d", Integer.parseInt(scheduleId.substring(1))+1);
		scheduleId = "S" + scheduleId;
		return scheduleId;
	}
	
public java.sql.Date convertDate(String dt) throws ParseException  {
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	Date dl = sdf.parse(dt);
	return new java.sql.Date(dl.getTime());
	
}
	
	public String addBooking(Booking booking) {
		sessionFactory = SessionHelper.getConnection();
		session =sessionFactory.openSession();
		String userId = generateUserID();
		booking.setBookingStatus(BookingStatus.APPROVED);
		java.sql.Date sqlDate = new java.sql.Date(booking.getDateOfBooking().getTime());
		booking.setDateOfBooking(sqlDate);
		booking.setUserId(userId);
		
		Criteria cr = session.createCriteria(Booking.class);
		Transaction tr = session.beginTransaction();
		session.save(booking);
		tr.commit();
		return "Booking Done";
//		String bookingId = generateBookingId();
//		String scheduleId = generateScheduleID();
//		booking.setBookingId(bookingId);
//		booking.setScheduleId(scheduleId);
	}

}
//public String generateScheduleID(){
//sessionFactory = SessionHelper.getConnection();
//Session session = sessionFactory.openSession();
//Criteria cr = session.createCriteria(Bus.class);
//List<Bus> busList = cr.list();
//if(busList.size()==0){
//	 return "B001";
//}
//session.close();
//String id = busList.get(busList.size()-1).getBusId();
//int id1 = Integer.parseInt(id.substring(1));
//id1++;
//String id2 = String.format("B%03d", id1);
//return id2;
//
//}
           //-------------------------//
//public String generateBookingId() {
//
//sessionFactory = SessionHelper.getConnection();
//Session session = sessionFactory.openSession();
//Criteria cr = session.createCriteria(Booking.class);
//List<Booking> bk = cr.list();
//if (bk.size()==0) {
//	return "B001";
//}
//String bookingId = bk.get(bk.size() -1).getBookingId();
//
//bookingId = String.format("%03d", Integer.parseInt(bookingId.substring(1))+1);
//bookingId = "B" + bookingId;
//return bookingId;
//
//}