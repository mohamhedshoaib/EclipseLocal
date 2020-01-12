<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	<div class="container">
		<table class="table table-striped">
			<caption>Your todos are</caption>
			<thead>
				<tr>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is it Done?</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${emps}" var="emps">
					<tr>
						<td>${emps.employee_name}</td>
						<td>${emps.employee_salary}</td>
						<td>${emps.employee_age}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
<%@ include file="common/footer.jspf" %>