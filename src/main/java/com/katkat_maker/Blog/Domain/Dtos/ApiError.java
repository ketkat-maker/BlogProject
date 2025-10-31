package com.katkat_maker.Blog.Domain.Dtos;

import lombok.Builder;




@Builder
public class ApiError {
    String message;
     int status;
      String timeStamp;
      String path;


}
