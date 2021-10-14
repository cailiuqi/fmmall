package com.onepeice.fmmall.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cache {
    Map<String,String> token = new HashMap<String,String>();



}
