package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Orderes {

	@Id
	@GeneratedValue
	private int orderId;
	private LocalDate date;

	@ManyToOne
	private Customer customer;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinTable
	private List<OrderLine> orderLines = new ArrayList<>();

	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", orderLines=" + orderLines + "]";
	}

}
