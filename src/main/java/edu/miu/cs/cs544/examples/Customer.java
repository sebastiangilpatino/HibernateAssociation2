package edu.miu.cs.cs544.examples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private List<Orderes> orders = new ArrayList<>();

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void addOrder(Orderes order) {
		order.setCustomer(this);
		orders.add(order);
	}

}
