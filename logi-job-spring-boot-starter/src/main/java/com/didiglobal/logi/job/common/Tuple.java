package com.didiglobal.logi.job.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tuple<T, V> {
    private T v1;
    private V v2;
}
