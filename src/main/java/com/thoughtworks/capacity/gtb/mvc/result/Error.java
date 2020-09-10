package com.thoughtworks.capacity.gtb.mvc.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by wzw on 2020/9/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    int code;
    String message;
}
