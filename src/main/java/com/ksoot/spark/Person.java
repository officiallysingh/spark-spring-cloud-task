package com.ksoot.spark;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
  private String name;
  private int age;

  // Constructors
  //    public Person() {}
  //    public Person(String name, int age) {
  //        this.name = name;
  //        this.age = age;
  //    }

  // Getters and Setters
  //    public String getName() { return name; }
  //    public void setName(String name) { this.name = name; }
  //    public int getAge() { return age; }
  //    public void setAge(int age) { this.age = age; }
}
