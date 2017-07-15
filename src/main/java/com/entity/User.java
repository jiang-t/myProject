/**
 * @author Administrator
 *
 */
package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ≤‚ ‘ µÃÂ¿‡
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
@Entity(name="user")
public class User{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="valuess")
	private String valuess;
}