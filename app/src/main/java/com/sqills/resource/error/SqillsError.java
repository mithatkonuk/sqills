package com.sqills.resource.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * General Error Response
 *  code : which can be define as special for business , NOT HTTP
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SqillsError implements Serializable
{
    private int code;
    private String message;
}
