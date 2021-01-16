package com.sqills.resource.response;

import com.sqills.resource.error.SqillsError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SqillsResponse implements Serializable
{

    private Object data;
    private SqillsError error;

    public SqillsResponse( Object data )
    {
        this.data = data;
        this.error = null;
    }
}
