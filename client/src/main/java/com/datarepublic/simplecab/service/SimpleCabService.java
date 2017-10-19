package com.datarepublic.simplecab.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

public interface SimpleCabService {

  void deleteCache() throws IOException;

  void getMedallionsSummary(String pickupDate, Set<String> medallion) throws UnsupportedEncodingException;

  void getMedallionsSummary(String pickupDate,  boolean ignoreCache, Set<String> medallion) throws UnsupportedEncodingException;

}
