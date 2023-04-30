package com.nhnacademy.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "authority")
public class Authority {

  @Id
  private Integer id;

  @Column(name = "authority")
  private String authority;

  @MapsId
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "resident_serial_number", referencedColumnName = "resident_serial_number")
  private Resident resident;


//  @OneToOne(mappedBy = "resident_serial_number")
//  private Resident resident;

}
/*
create table if not exists authority(
  resident_serial_number int(11) not null,
  authority varchar(100) not null,
  primary key(resident_serial_number),
  foreign key(resident_serial_number) references resident(resident_serial_number)
);
 */