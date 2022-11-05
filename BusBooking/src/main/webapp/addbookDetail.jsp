<%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addbookDetail.jsp" method="post">
	
		Seat NO:
		<input type="number" name="seatNo"/> <br/> <br/>
		
		Fare Amount:
		<input type="number" name="fareAmount"/> <br/><br/>
		
		Total Amount:
		<input type="number" name = "totalAmount"/> <br/><br/>
		
		DateOfBooking:
		<input type="date" name="dateOfBooking"/> <br/><br/>
		<input type="submit" value="Booked..."/>
	</form>
	<c:if test="${param.seatNo!=null}">
	<jsp:useBean id="dao" class="infinite.BusBooking.BookingDAO"/>
	<jsp:useBean id="book" class="infinite.BusBooking.Booking"/>
	<c:set var="dob" value="${dao.convertDate(param.dateOfBooking) }"/>
	<jsp:setProperty property="seatNo" name="book" value="${param.seatNo }"/>
	<jsp:setProperty property="fareAmount" name="book" value="${param.fareAmount }"/>
	<jsp:setProperty property="totalAmount" name="book" value="${param.totalAmount }"/>
	<jsp:setProperty property="dateOfBooking" name="book" value="${dob}"/>
	<c:out value="${dao.addBooking(book)}"/>
	</c:if>
</body>
</html>