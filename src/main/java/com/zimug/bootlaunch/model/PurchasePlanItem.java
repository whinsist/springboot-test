package com.zimug.bootlaunch.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "purchase_plan_item")
public class PurchasePlanItem implements Serializable {

    @Id
    private Long userId;
    private String username;
    private String userpwd;



}