package org.example.spu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
public class Department {

    @Id
    // @GeneratedValue   
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "department_generator")
	@SequenceGenerator(name = "department_generator", sequenceName = "department_id_seq", allocationSize = 1)
    private Long id;

	
    private String name;
    
	@Column(name = "parent_id")
    private Long parentId;

	@OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Department> children = new ArrayList<>();
    
	@Override
	public String toString() {
		return String.format(
				"Department[id=%d, name='%s', parentId='%s']",
				id, name, parentId);
	}
}