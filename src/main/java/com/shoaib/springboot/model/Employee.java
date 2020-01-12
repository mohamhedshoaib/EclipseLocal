package com.shoaib.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
	public class Employee {
	
	
		@Id
		private String id;
		private String employee_name;
		private String employee_salary;
		private String employee_age;
		private String profile_image;

		// Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
		// Can not construct instance of com.in28minutes.springboot.model.Question:
		// no suitable constructor found, can not deserialize from Object value
		// (missing default constructor or creator, or perhaps need to add/enable
		// type information?)
		public Employee() {

		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getEmployee_name() {
			return employee_name;
		}

		public void setEmployee_name(String employee_name) {
			this.employee_name = employee_name;
		}

		public String getEmployee_salary() {
			return employee_salary;
		}

		public void setEmployee_salary(String employee_salary) {
			this.employee_salary = employee_salary;
		}

		public String getEmployee_age() {
			return employee_age;
		}

		public void setEmployee_age(String employee_age) {
			this.employee_age = employee_age;
		}

		public String getProfile_image() {
			return profile_image;
		}

		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", employee_name=" + employee_name + ", employee_salary=" + employee_salary
					+ ", employee_age=" + employee_age + ", profile_image=" + profile_image + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Employee other = (Employee) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		
		
		
		
	}
