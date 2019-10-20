Parental Control Service
===

Movie Service
---
The Movie Meta Data team is currently developing the MovieService getParentalControlLevel call that accepts the *movie 
id* as an input and returns the parental control level for that movie as a string. 

This is a simple diagram of the interaction between the services:

```
Client                      ParentalControlService                      MovieService
------                      ----------------------                      ------------
  |                                   |                                       |
  | customer parental control level,  |                                       |
  |          movie id                 |                                       |
  |---------------------------------->|                                       |
  |                                   |      getParentalControlLevel(...)     |
  |                                   |-------------------------------------->|
  |                                   |                                       |
  |                                   |      movie parental control level     |
  |                                   |<--------------------------------------|
  |            boolean                |                                       |
  |<----------------------------------|                                       |
  |                                   |                                       |
```

MovieService Interface
---
```java
package com.thirdparty.movie;

public interface MovieService {
    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}
```

This is a third party interface so you should not change it. This service throws checked exceptions.
