package com.staxrt.tutorial.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    @Column(name = "suburb_name", nullable = false)
    private String suburbName;

    @Column(name = "postcodes", nullable = false)
    private Long postCodes;



  public long getId() {
        return id;
    }

  public void setId(long id) {
        this.id = id;
    }


  public String getSuburbName() {
        return suburbName;
    }

  public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

  public long getPostCodes() {
      return postCodes;
  }

  public void setostCodes(long postCodes) {
      this.postCodes = postCodes;
  }



    @Override
    public String toString() {
        return "City{" +
                "postCodes=" + postCodes +
                ", suburbName='" + suburbName + '\'' +
                '}';
    }

	


}
